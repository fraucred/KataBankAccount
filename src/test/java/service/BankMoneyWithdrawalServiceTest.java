package service;

import domain.AccountHistory;
import domain.BankClient;
import domain.Transaction;
import domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BankMoneyWithdrawalServiceTest {

    private BankMoneyWithdrawalService bankMoneyWithdrawalService;

    @Mock
    private BankAccountHistoryService bankAccountHistoryService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bankMoneyWithdrawalService = new BankMoneyWithdrawalService();
    }

    @Test
    void bank_client_should_make_withdrawal_from_bank_account_in_order_to_retrieve_some_or_all_money_saved() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";

        final BigDecimal accountBalanceAfterDeposit = BigDecimal.valueOf(250);
        final BigDecimal accountBalanceAfterWithdrawal = BigDecimal.valueOf(125);
        final BigDecimal moneyToBeSaved = BigDecimal.valueOf(25);
        final BigDecimal moneyToBeWithdrawed = BigDecimal.valueOf(125);
        final Date transactionDate = Date.from(Instant.now());

        final BankClient bankClient = new BankClient(bankClientFirstName, bankClientLastName, bankClientBirthday,
                bankClientAccountId);

        final Transaction depositTransaction = new Transaction()
                .setTransactionType(TransactionType.DEPOSIT)
                .setTransactionAmount(moneyToBeSaved)
                .setAccountBalance(accountBalanceAfterDeposit)
                .setDate(transactionDate);
        final Transaction withdrawalTransaction = new Transaction()
                .setTransactionType(TransactionType.WITHDRAWAL)
                .setTransactionAmount(moneyToBeWithdrawed)
                .setAccountBalance(accountBalanceAfterWithdrawal)
                .setDate(transactionDate);
        final List<Transaction> mockTransactionList = Arrays.asList(depositTransaction, withdrawalTransaction);

        final AccountHistory mockAccountHistory = new AccountHistory().setTransactionsHistory(mockTransactionList);
        when(bankAccountHistoryService.getAccountHistory(bankClient)).thenReturn(mockAccountHistory);

        // WHEN
        bankMoneyWithdrawalService.withdraw(bankClient, moneyToBeWithdrawed);
        final AccountHistory accountHistory = bankAccountHistoryService.getAccountHistory(bankClient);

        // THEN
        assertNotNull(accountHistory);
        assertNotNull(accountHistory.getTransactionsHistory());

        final List<Transaction> transactionsHistory = accountHistory.getTransactionsHistory();
        assertEquals(2, transactionsHistory.size());

        final Transaction firstTransaction = transactionsHistory.get(0);
        assertEquals(TransactionType.DEPOSIT, firstTransaction.getTransactionType());
        assertEquals(moneyToBeSaved, firstTransaction.getTransactionAmount());
        assertEquals(accountBalanceAfterDeposit, firstTransaction.getAccountBalance());
        assertEquals(transactionDate, firstTransaction.getDate());

        final Transaction lastTransaction = transactionsHistory.get(1);
        assertEquals(TransactionType.WITHDRAWAL, lastTransaction.getTransactionType());
        assertEquals(moneyToBeWithdrawed, lastTransaction.getTransactionAmount());
        assertEquals(accountBalanceAfterWithdrawal, lastTransaction.getAccountBalance());
        assertEquals(transactionDate, lastTransaction.getDate());
    }
}