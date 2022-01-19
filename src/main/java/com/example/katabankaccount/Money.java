package com.example.katabankaccount;

import java.math.BigDecimal;

public class Money extends Amount {

    public Money(BigDecimal amount) {
        super(amount);
        checkPositiveValue(amount);
    }

    public void checkPositiveValue(BigDecimal value) {
        if (value.signum() < 0) {
            throw new RuntimeException("Money needs a positive value higher or equal to 0");
        }
    }

}
