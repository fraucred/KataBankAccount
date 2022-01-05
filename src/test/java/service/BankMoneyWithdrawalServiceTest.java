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

class BankMoneyWithdrawalServiceTest {

    private BankMoneyWithdrawalService bankMoneyWithdrawalService;

    @BeforeEach
    void setUp() {
        bankMoneyWithdrawalService = new BankMoneyWithdrawalService();
    }

    @Test
    void bank_client_should_make_withdrawal_from_bank_account_in_order_to_retrieve_some_or_all_money_saved() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";

        final BigDecimal accountBalanceAfterWithdrawal = BigDecimal.valueOf(-125);
        final Money moneyToBeWithdrawed = new Money().setAmount(BigDecimal.valueOf(125));
        final Date transactionDate = Date.from(Instant.now());

        final BankClient bankClient = new BankClient(bankClientFirstName, bankClientLastName, bankClientBirthday,
                bankClientAccountId);

        // WHEN
        bankMoneyWithdrawalService.withdraw(bankClient, moneyToBeWithdrawed);
        final AccountHistory accountHistory = bankClient.getAccountHistory();

        // THEN
        assertNotNull(accountHistory);
        assertNotNull(accountHistory.getTransactionsHistory());

        final List<Transaction> transactionsHistory = accountHistory.getTransactionsHistory();
        assertEquals(1, transactionsHistory.size());

        final Transaction firstTransaction = transactionsHistory.get(0);
        assertEquals(TransactionType.WITHDRAWAL, firstTransaction.getTransactionType());
        assertEquals(moneyToBeWithdrawed.getAmount(), firstTransaction.getTransactionAmount());
        assertEquals(accountBalanceAfterWithdrawal, firstTransaction.getAccountBalance());
        assertEquals(transactionDate, firstTransaction.getDate());
    }
}