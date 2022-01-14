package com.example.katabankaccount;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_make_single_deposit_to_account() {
        Account account = new Account();
        Money money = new Money(1);

        account.deposit(money);

        assertThat(account.balance()).isEqualTo(money);
    }

    @Test
    public void should_make_two_deposit_to_account() {
        Account account = new Account();
        Money money = new Money(1);
        Money expectedBalance = new Money(2);

        account.deposit(money);
        account.deposit(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_single_deposit_with_no_money_to_account() {
        Account account = new Account();
        Money zeroAmountMoney = new Money(0);

        account.deposit(zeroAmountMoney);

        assertThat(account.balance()).isEqualTo(zeroAmountMoney);
    }

    @Test
    public void should_make_single_withdraw_from_account() {
        Money oldBalance = new Money(20);
        Account account = new Account(oldBalance);
        Money money = new Money(10);
        Money expectedBalance = new Money(10);

        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_withdraw_twice_money_from_account() {
        Money oldBalance = new Money(20);
        Account account = new Account(oldBalance);
        Money money = new Money(10);
        Money expectedBalance = new Money(0);

        account.withdraw(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_withdraw_more_money_from_account() {
        Account account = new Account();
        Money money = new Money(100);
        Money expectedBalance = new Money(-100);

        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_withdraw_all_money_from_account_with_money_in_balance() {
        Money oldBalance = new Money(9999999);
        Account account = new Account(oldBalance);
        Money expectedMoney = new Money(0);

        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_withdraw_all_money_from_new_account() {
        Account account = new Account();
        Money expectedMoney = new Money(0);

        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_get_empty_history_of_operations_from_my_account() {
        Account account = new Account();

        assertThat(account.transactions()).isEqualTo(emptyList());
    }

    @Test
    public void should_get_history_of_deposit_and_withdrawal_operations_from_my_account() {
        Account account = new Account();
        Money moneyToBeSaved = new Money(200);
        Money moneyToDraw = new Money(25);

        Transaction depositTransaction = new Transaction(OperationType.DEPOSIT, Date.from(Instant.now()), moneyToBeSaved, new Money(200));
        Transaction withdrawTransaction = new Transaction(OperationType.WITHDRAW, Date.from(Instant.now()), moneyToDraw, new Money(175));
        List<Transaction> expectedHistories = Arrays.asList(depositTransaction, withdrawTransaction);

        account.deposit(moneyToBeSaved);
        account.withdraw(moneyToDraw);

        assertThat(account.transactions()).isEqualTo(expectedHistories);
    }
}
