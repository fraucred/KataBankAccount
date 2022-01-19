package com.example.katabankaccount;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {
    private final BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public Amount add(Amount amount) {
        return new Amount(this.value.add(amount.value));
    }

    public Amount opposite() {
        return new Amount(value.negate());
    }

    public int sign() {
        return value.signum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
