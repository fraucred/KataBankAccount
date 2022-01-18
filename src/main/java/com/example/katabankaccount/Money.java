package com.example.katabankaccount;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;

    public Money(int i) {
        checkPositiveValue(i);
        this.amount = new BigDecimal(i);
    }

    public void add(Money money) {
        this.amount = this.amount.add(money.amount);
    }

    public void subtract(Money money) {
        this.amount = this.amount.subtract(money.amount);
    }

    public void checkPositiveValue(int i) {
        if (i < 0) {
            throw new RuntimeException("Money needs a positive value higher or equal to 0");
        }
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

}
