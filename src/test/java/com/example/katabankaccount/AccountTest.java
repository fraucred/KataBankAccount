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
public class AccountTest {

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    void setUp() {
        when(dateProvider.nowDefaultDate()).thenReturn(new Date(123456));
    }

    @Test
    public void should_make_single_deposit_to_account() {
        Account account = new Account(dateProvider);
        Money money = Money.createMoneyFromPositiveValue(1);

        account.deposit(money);

        assertThat(account.balance()).isEqualTo(money);
    }

    @Test
    public void should_make_two_deposit_to_account() {
        Account account = new Account(dateProvider);
        Money money = Money.createMoneyFromPositiveValue(1);
        Money expectedBalance = Money.createMoneyFromPositiveValue(2);

        account.deposit(money);
        account.deposit(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_single_deposit_with_no_money_to_account() {
        Account account = new Account(dateProvider);
        Money zeroAmountMoney = Money.createMoneyFromPositiveValue(0);

        account.deposit(zeroAmountMoney);

        assertThat(account.balance()).isEqualTo(zeroAmountMoney);
    }

    @Test
    public void should_make_single_withdraw_from_account_after_deposit() {
        Account account = new Account(dateProvider);
        Money money = Money.createMoneyFromPositiveValue(10);
        Money expectedBalance = Money.createMoneyFromPositiveValue(0);

        account.deposit(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_two_withdraws_from_account_after_two_deposit() {
        Account account = new Account(dateProvider);
        Money money = Money.createMoneyFromPositiveValue(10);
        Money lotsOfMoney = Money.createMoneyFromPositiveValue(100);
        Money expectedBalance = Money.createMoneyFromPositiveValue(180);

        account.deposit(lotsOfMoney);
        account.deposit(lotsOfMoney);
        account.withdraw(money);
        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_make_single_withdraw_from_empty_account() {
        Account account = new Account(dateProvider);
        Money money = Money.createMoneyFromPositiveValue(100);
        Money expectedBalance = Money.createMoneyFromNegativeValue(-100);

        account.withdraw(money);

        assertThat(account.balance()).isEqualTo(expectedBalance);
    }

    @Test
    public void should_withdraw_all_money_from_account_after_deposit() {
        Money moneySaved = Money.createMoneyFromPositiveValue(9999999);
        Account account = new Account(dateProvider);
        Money expectedMoney = Money.createMoneyFromPositiveValue(0);

        account.deposit(moneySaved);
        account.withdrawAll();

        assertThat(account.balance()).isEqualTo(expectedMoney);
    }

    @Test
    public void should_get_history_of_deposit_and_withdrawal_operations_from_my_account() {
        Account account = new Account(dateProvider);
        Money moneyToBeSaved = Money.createMoneyFromPositiveValue(200);
        Money moneyToDraw = Money.createMoneyFromPositiveValue(25);

        Transaction depositTransaction = new Transaction(OperationType.DEPOSIT, dateProvider.nowDefaultDate(), moneyToBeSaved);
        Transaction withdrawTransaction = new Transaction(OperationType.WITHDRAW, dateProvider.nowDefaultDate(), moneyToDraw);
        List<Transaction> expectedHistories = Arrays.asList(depositTransaction, withdrawTransaction);

        account.deposit(moneyToBeSaved);
        account.withdraw(moneyToDraw);

        assertThat(account.transactions()).isEqualTo(expectedHistories);
    }
}
