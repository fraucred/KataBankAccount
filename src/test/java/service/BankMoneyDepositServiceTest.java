package service;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BankMoneyDepositServiceTest {

    private BankMoneyDepositService bankMoneyDepositService;

    @BeforeEach
    void setUp() {
        bankMoneyDepositService = new BankMoneyDepositService();
    }

    @Test
    void bank_client_should_make_deposit_to_bank_account_in_order_to_save_money() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";
        final BigDecimal expectedAccountBalance = BigDecimal.valueOf(100.50);
        final Money moneyToBeSaved = new Money().setAmount(BigDecimal.valueOf(100.50));
        final Date transactionDate = Date.from(Instant.now());

        final BankClient bankClient = new BankClient(bankClientFirstName, bankClientLastName, bankClientBirthday,
                bankClientAccountId);

        // WHEN
        bankMoneyDepositService.deposit(bankClient, moneyToBeSaved);
        final AccountHistory accountHistory = bankClient.getAccountHistory();

        // THEN
        assertNotNull(accountHistory);
        assertNotNull(accountHistory.getTransactionsHistory());

        final List<Transaction> transactionsHistory = accountHistory.getTransactionsHistory();
        assertEquals(1, transactionsHistory.size());

        final Transaction firstTransaction = transactionsHistory.get(0);
        assertEquals(TransactionType.DEPOSIT, firstTransaction.getTransactionType());
        assertEquals(moneyToBeSaved.getAmount(), firstTransaction.getTransactionAmount());
        assertEquals(expectedAccountBalance, firstTransaction.getAccountBalance());
        assertEquals(transactionDate, firstTransaction.getDate());
    }
}