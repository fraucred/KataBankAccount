package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private final List<Transaction> transactions = new ArrayList<>();
    private final DateProvider dateProvider;

    public Account(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }


    public void deposit(Money money) {
        this.transactions.add(new Transaction(OperationType.DEPOSIT, dateProvider.nowDefaultDate(), money));
    }

    public void withdraw(Money money) {
        this.transactions.add(new Transaction(OperationType.WITHDRAW, dateProvider.nowDefaultDate(), money));
    }

    public Money balance() {
        final Money balance = new Money(0);
        if (transactions.isEmpty()) {
            return balance;
        }
        for (Transaction transaction : transactions) {
            if (transaction.isDepositOperation()) {
                balance.add(transaction.getAmount());
            } else {
                balance.subtract(transaction.getAmount());
            }
        }
        return balance;
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public void withdrawAll() {
        withdraw(balance());
    }
}
