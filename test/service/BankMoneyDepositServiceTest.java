package service;

import domain.AccountHistory;
import domain.BankClient;
import domain.Transaction;
import domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BankMoneyDepositServiceTest {

    //    private BankAccountSecurityService bankAccountSecurityService;
    private BankMoneyDepositService bankMoneyDepositService;

    @Mock
    private BankAccountHistoryService bankAccountHistoryService;

    @BeforeEach
    void setUp() {
        initMocks(this);
//        bankAccountSecurityService = new BankAccountSecurityService();
        bankMoneyDepositService = new BankMoneyDepositService();
    }

    @Test
    void bank_client_should_make_deposit_to_bank_account_in_order_to_save_money() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";
        final BigDecimal moneyToBeSaved = BigDecimal.valueOf(100.50);

        final BankClient bankClient = new BankClient(bankClientFirstName, bankClientLastName, bankClientBirthday,
                bankClientAccountId);

        final Transaction depositTransaction = new Transaction().setTransactionType(TransactionType.DEPOSIT);
        final List<Transaction> mockTransactionList = List.of(depositTransaction);
        final AccountHistory mockAccountHistory = new AccountHistory().setTransactionsHistory(mockTransactionList);
        when(bankAccountHistoryService.getAccountHistory(bankClient)).thenReturn(mockAccountHistory);

        // WHEN
//        bankAccountSecurityService.validateBankClient(bankClient.getAccountId());
        bankMoneyDepositService.deposit(bankClient, moneyToBeSaved);
        final AccountHistory accountHistory = bankAccountHistoryService.getAccountHistory(bankClient);

        // THEN
        assertNotNull(accountHistory);
        assertNotNull(accountHistory.getTransactionsHistory());

        final List<Transaction> transactionsHistory = accountHistory.getTransactionsHistory();
        assertEquals(1, transactionsHistory.size());

        final Transaction firstTransaction = transactionsHistory.get(0);
        assertEquals(TransactionType.DEPOSIT, firstTransaction.getTransactionType());
    }
}