package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoryTest {

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        when(dateProvider.now()).thenReturn(LocalDateTime.of(2022, 01, 19, 12, 00, 25, 50));
    }

    @Test
    public void should_get_history_of_deposit_and_withdrawal_operations_from_my_account() {
        Account account = new Account(dateProvider);
        Money moneyToBeSaved = new Money(BigDecimal.valueOf(200));
        Money moneyToDraw = new Money(BigDecimal.valueOf(25));

        account.deposit(moneyToBeSaved);
        account.withdraw(moneyToDraw);

        Transaction depositTransaction = new Transaction(OperationType.DEPOSIT, dateProvider.now(), moneyToBeSaved);
        Transaction withdrawTransaction = new Transaction(OperationType.WITHDRAW, dateProvider.now(), moneyToDraw);
        List<Transaction> expectedHistories = Arrays.asList(depositTransaction, withdrawTransaction);
        assertThat(account.transactions()).isEqualTo(expectedHistories);
    }
}
