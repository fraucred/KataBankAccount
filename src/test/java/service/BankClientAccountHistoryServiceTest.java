package service;

import domain.BankClient;
import domain.Money;
import domain.Transaction;
import domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankClientAccountHistoryServiceTest {

    private BankClientAccountHistoryService bankClientAccountHistoryService;

    @BeforeEach
    void setUp() {
        bankClientAccountHistoryService = new BankClientAccountHistoryService();
    }

    @Test
    void bank_client_should_see_history_of_bank_account_operations_in_order_to_check_all_operations() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";
        final BigDecimal accountBalanceAfterDeposit = BigDecimal.valueOf(250);
        final BigDecimal accountBalanceAfterWithdrawal = BigDecimal.valueOf(125);
        final Money moneyToBeSaved = new Money().setAmount(BigDecimal.valueOf(250));
        final Money moneyToBeWithdrawed = new Money().setAmount(BigDecimal.valueOf(125));
        final Date transactionDate = Date.from(Instant.now());

        final BankClient bankClient = new BankClient(bankClientFirstName, bankClientLastName, bankClientBirthday,
                bankClientAccountId);

        final Transaction depositTransaction = new Transaction()
                .setTransactionType(TransactionType.DEPOSIT)
                .setTransactionAmount(moneyToBeSaved.getAmount())
                .setAccountBalance(accountBalanceAfterDeposit)
                .setDate(transactionDate);
        final Transaction withdrawalTransaction = new Transaction()
                .setTransactionType(TransactionType.WITHDRAWAL)
                .setTransactionAmount(moneyToBeWithdrawed.getAmount())
                .setAccountBalance(accountBalanceAfterWithdrawal)
                .setDate(transactionDate);

        final List<Transaction> mockTransactionList = Arrays.asList(depositTransaction, withdrawalTransaction);
        bankClient.getAccountHistory().setTransactionsHistory(mockTransactionList);

        // WHEN
        final List<Transaction> bankClientAccountTransactionHistory = bankClientAccountHistoryService
                .checkAccountHistoryOperations(bankClient);

        // THEN
        assertEquals(2, bankClientAccountTransactionHistory.size());

        final Transaction firstTransaction = bankClientAccountTransactionHistory.get(0);
        assertEquals(TransactionType.DEPOSIT, firstTransaction.getTransactionType());
        assertEquals(moneyToBeSaved.getAmount(), firstTransaction.getTransactionAmount());
        assertEquals(accountBalanceAfterDeposit, firstTransaction.getAccountBalance());
        assertEquals(transactionDate, firstTransaction.getDate());

        final Transaction lastTransaction = bankClientAccountTransactionHistory.get(1);
        assertEquals(TransactionType.WITHDRAWAL, lastTransaction.getTransactionType());
        assertEquals(moneyToBeWithdrawed.getAmount(), lastTransaction.getTransactionAmount());
        assertEquals(accountBalanceAfterWithdrawal, lastTransaction.getAccountBalance());
        assertEquals(transactionDate, lastTransaction.getDate());
    }
}