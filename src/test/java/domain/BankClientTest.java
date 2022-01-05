package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

class BankClientTest {

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void bank_client_should_see_history_of_operations_from_account_in_order_to_check_its_operations() {
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
        final AccountHistory accountHistory = bankClient.getAccountHistory();

        // THEN
        assertNotNull(accountHistory);
        assertNotNull(accountHistory.getTransactionsHistory());
    }
}