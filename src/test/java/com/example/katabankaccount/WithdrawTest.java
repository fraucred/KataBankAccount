package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WithdrawTest {

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        when(dateProvider.nowDefaultDate()).thenReturn(new Date(123456));
    }

    @Test
    public void should_make_single_withdraw_from_account_after_deposit() {
        Account account = new Account(dateProvider);
        Money money = new Money(10);
        Money expectedBalance = new Money(0);

        account.deposit(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_two_withdraws_from_account_after_two_deposit() {
        Account account = new Account(dateProvider);
        Money money = new Money(10);
        Money lotsOfMoney = new Money(100);
        Money expectedBalance = new Money(180);

        account.deposit(lotsOfMoney);
        account.deposit(lotsOfMoney);
        account.withdraw(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }


    @Test
    public void should_withdraw_all_money_from_account_after_deposit() {
        Money moneySaved = new Money(9999999);
        Account account = new Account(dateProvider);
        Money expectedMoney = new Money(0);

        account.deposit(moneySaved);
        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }
}
