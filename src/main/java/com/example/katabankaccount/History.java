package com.example.katabankaccount;

import java.math.BigDecimal;
import java.util.Date;

public class History {
    private final OperationType operationType;
    private final Date date;
    private final Money amount;
    private final Money balance;

    public History() {
        this.operationType = null;
        this.date = null;
        this.amount = new Money(BigDecimal.ZERO);
        this.balance = new Money(BigDecimal.ZERO);
    }
    public History(OperationType operationType, Date date, Money amount, Money balance) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }
}
