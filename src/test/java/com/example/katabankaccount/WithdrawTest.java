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
public class WithdrawTest {

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        when(dateProvider.now()).thenReturn(LocalDateTime.of(2022, 01, 19, 12, 00, 25, 50));
    }

    @Test
    public void should_make_single_withdraw_from_account_after_deposit() {
        Account account = new Account(dateProvider);
        Money money = new Money(BigDecimal.valueOf(10));

        account.deposit(money);
        account.withdraw(money);

        Amount expectedBalance = new Amount(BigDecimal.ZERO);
        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_two_withdraws_from_account_after_two_deposit() {
        Account account = new Account(dateProvider);
        Money money = new Money(BigDecimal.valueOf(10));
        Money lotsOfMoney = new Money(BigDecimal.valueOf(100));

        account.deposit(lotsOfMoney);
        account.deposit(lotsOfMoney);
        account.withdraw(money);
        account.withdraw(money);

        Amount expectedBalance = new Amount(BigDecimal.valueOf(180));
        assertThat(account.balance()).isEqualTo(expectedBalance);
    }


    @Test
    public void should_withdraw_all_money_from_account_after_deposit() {
        Account account = new Account(dateProvider);
        Money moneySaved = new Money(BigDecimal.valueOf(9999999));

        account.deposit(moneySaved);
        account.withdrawAllMoney();

        Amount expectedBalance = new Amount(BigDecimal.ZERO);
        assertThat(account.balance()).isEqualTo(expectedBalance);
    }
}
