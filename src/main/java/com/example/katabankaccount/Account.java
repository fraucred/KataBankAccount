package com.example.katabankaccount;

import java.math.BigDecimal;

public class Account {
    private final Money balance = new Money(BigDecimal.ZERO);

    public void deposit(Money money) {
        this.balance.add(money);
    }

    public Money money() {
        return balance;
    }


    public void withdraw(Money money) {

    }
}
