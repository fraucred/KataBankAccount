package com.example.katabankaccount;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Transaction> transactions = new ArrayList<>();
    private Money balance = new Money(0);

    public Account() {
    }

    public Account(Money balance) {
        this.balance = balance;
    }

    public void deposit(Money money) {
        this.balance.add(money);
        this.transactions.add(new Transaction(OperationType.DEPOSIT, Date.from(Instant.now()), money, this.balance.copy()));
    }

    public Money balance() {
        return balance;
    }


    public void withdraw(Money money) {
        this.balance.subtract(money);
        this.transactions.add(new Transaction(OperationType.WITHDRAW, Date.from(Instant.now()), money, this.balance.copy()));
    }

    public void withdrawAll() {
        this.balance.subtract(this.balance);
    }

    public List<Transaction> transactions() {
        return transactions;
    }
}
