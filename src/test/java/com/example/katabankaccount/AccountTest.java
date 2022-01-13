package com.example.katabankaccount;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_deposit_single_money_to_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.ONE);

        account.deposit(money);

        assertThat(account.balance()).isEqualTo(money);
    }

    @Test
    public void should_deposit_twice_money_to_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.ONE);
        Money expectedMoney = new Money(BigDecimal.valueOf(2));

        account.deposit(money);
        account.deposit(money);

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_deposit_zero_money_to_account() {
        Account account = new Account();
        Money zeroAmountMoney = new Money(BigDecimal.ZERO);

        account.deposit(zeroAmountMoney);

        assertThat(account.balance()).isEqualTo(zeroAmountMoney);
    }

    @Test
    public void should_withdraw_money_from_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.valueOf(10));
        Money expectedMoney = new Money(BigDecimal.valueOf(-10));

        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_withdraw_twice_money_from_account() {
        Account account = new Account();
        Money money = new Money(BigDecimal.valueOf(10));
        Money expectedMoney = new Money(BigDecimal.valueOf(-20));

        account.withdraw(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_withdraw_all_money_from_account_with_money_in_balance() {
        Money oldBalance = new Money(BigDecimal.valueOf(9999999));
        Account account = new Account(oldBalance);
        Money expectedMoney = new Money(BigDecimal.ZERO);

        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_withdraw_all_money_from_account() {
        Account account = new Account();
        Money expectedMoney = new Money(BigDecimal.ZERO);

        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_get_empty_history_of_operations_from_my_account() {
        Account account = new Account();

        assertThat(account.histories()).isEqualTo(emptyList());
    }

    @Test
    public void should_get_history_of_deposit_and_withdrawal_operations_from_my_account() {
        Account account = new Account();
        Money moneyToBeSaved = new Money(BigDecimal.valueOf(200));
        Money moneyToDraw = new Money(BigDecimal.valueOf(25));

        History depositHistory = new History(OperationType.DEPOSIT, Date.from(InstantHelper.now()), moneyToBeSaved, new Money(BigDecimal.valueOf(200)));
        History withdrawHistory = new History(OperationType.WITHDRAW, Date.from(InstantHelper.now()), moneyToDraw, new Money(BigDecimal.valueOf(175)));
        List<History> expectedHistories = Arrays.asList(depositHistory, withdrawHistory);

        account.deposit(moneyToBeSaved);
        account.withdraw(moneyToDraw);

        assertThat(account.histories()).isEqualTo(expectedHistories);
    }
}
