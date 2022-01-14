package com.example.katabankaccount;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;

    public Money(int i) {
        this.amount = new BigDecimal(i);
    }

    public Money add(Money money) {
        this.amount = this.amount.add(money.amount);
        return this;
    }

    public Money subtract(Money money) {
        this.amount = this.amount.subtract(money.amount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public Money copy() {
        return new Money(this.amount.intValue());
    }

    public Money opposite() {
        return new Money(-this.amount.intValue());
    }
}
