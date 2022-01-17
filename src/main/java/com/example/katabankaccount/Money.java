package com.example.katabankaccount;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;

    private Money(int i) {
        this.amount = new BigDecimal(i);
    }

    public Money add(Money money) {
        this.amount = this.amount.add(money.amount);
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

    public Money opposite() {
        return new Money(-this.amount.intValue());
    }

    public static Money createMoneyFromPositiveValue(int i) {
        if (i < 0) {
            throw new RuntimeException("Money needs a positive value higher or equal to 0");
        }
        return new Money(i);
    }

    public static Money createMoneyFromNegativeValue(int i) {
        if (i > 0) {
            throw new RuntimeException("Money needs a negative value lower or equal to 0");
        }
        return new Money(i);
    }
}
