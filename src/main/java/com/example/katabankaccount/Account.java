package com.example.katabankaccount;

import java.math.BigDecimal;

public class Account {
    private Money balance = new Money(BigDecimal.ZERO);
    private final History history = new History();

    public Account() {
    }

    public Account(Money balance) {
        this.balance = balance;
    }

    public void deposit(Money money) {
        this.balance.add(money);
    }

    public Money balance() {
        return balance;
    }


    public void withdraw(Money money) {
        this.balance.substract(money);
    }

    public void withdrawAll() {
        this.balance.substract(this.balance);
    }

    public History history() {
        return history;
    }
}
