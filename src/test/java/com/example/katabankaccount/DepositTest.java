package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepositTest {

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        when(dateProvider.now()).thenReturn(LocalDateTime.of(2022, 01, 19, 12, 00, 25, 50));
    }

    @Test
    public void should_make_single_deposit_to_account() {
        Account account = new Account(dateProvider);
        Money money = new Money(BigDecimal.ONE);

        account.deposit(money);

        assertThat(account.balance()).isEqualTo(money);
    }

    @Test
    public void should_make_two_deposit_to_account() {
        Account account = new Account(dateProvider);
        Money money = new Money(BigDecimal.ONE);

        account.deposit(money);
        account.deposit(money);

        Amount expectedBalance = new Amount(BigDecimal.valueOf(2));
        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_single_deposit_with_no_money_to_account() {
        Account account = new Account(dateProvider);
        Money zeroAmountMoney = new Money(BigDecimal.ZERO);

        account.deposit(zeroAmountMoney);

        assertThat(account.balance()).isEqualTo(zeroAmountMoney);
    }
}
