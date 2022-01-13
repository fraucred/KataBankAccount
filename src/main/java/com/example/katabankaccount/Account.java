package com.example.katabankaccount;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private Money balance = new Money(BigDecimal.ZERO);
    private final List<History> histories = new ArrayList<>();

    public Account() {
    }

    public Account(Money balance) {
        this.balance = balance;
    }

    public void deposit(Money money) {
        this.balance.add(money);
        this.histories.add(new History(OperationType.DEPOSIT, Date.from(Instant.now()), money, this.balance.copy()));
    }

    public Money balance() {
        return balance;
    }


    public void withdraw(Money money) {
        this.balance.substract(money);
        this.histories.add(new History(OperationType.WITHDRAW, Date.from(Instant.now()), money, this.balance));
    }

    public void withdrawAll() {
        this.balance.substract(this.balance);
    }

    public List<History> histories() {
        return histories;
    }
}
