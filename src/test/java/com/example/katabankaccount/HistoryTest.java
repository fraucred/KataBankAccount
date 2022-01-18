package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
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
        when(dateProvider.nowDefaultDate()).thenReturn(new Date(123456));
    }

    @Test
    public void should_get_history_of_deposit_and_withdrawal_operations_from_my_account() {
        Account account = new Account(dateProvider);
        Money moneyToBeSaved = new Money(200);
        Money moneyToDraw = new Money(25);

        Transaction depositTransaction = new Transaction(OperationType.DEPOSIT, dateProvider.nowDefaultDate(), moneyToBeSaved);
        Transaction withdrawTransaction = new Transaction(OperationType.WITHDRAW, dateProvider.nowDefaultDate(), moneyToDraw);
        List<Transaction> expectedHistories = Arrays.asList(depositTransaction, withdrawTransaction);

        account.deposit(moneyToBeSaved);
        account.withdraw(moneyToDraw);

        assertThat(account.transactions()).isEqualTo(expectedHistories);
    }
}
