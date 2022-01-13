package com.example.katabankaccount;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_deposit_single_money_to_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.ONE);

        account.deposit(money);

        assertThat(account.money()).isEqualTo(money);
    }

    @Test
    public void should_deposit_twice_money_to_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.ONE);
        Money expectedMoney = new Money(BigDecimal.valueOf(2));

        account.deposit(money);
        account.deposit(money);

        assertThat(account.money()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_deposit_zero_money_to_account() {
        Account account = new Account();
        Money zeroAmountMoney = new Money(BigDecimal.ZERO);

        account.deposit(zeroAmountMoney);

        assertThat(account.money()).isEqualTo(zeroAmountMoney);
    }

    @Test
    public void should_withdraw_money_from_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.valueOf(10));
        Money expectedMoney = new Money(BigDecimal.valueOf(-10));

        account.withdraw(money);

        assertThat(account.money()).isEqualTo(expectedMoney);
    }
}
