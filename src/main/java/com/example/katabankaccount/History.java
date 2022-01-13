package com.example.katabankaccount;

import java.util.Date;
import java.util.Objects;

public class History {
    private final OperationType operationType;
    private final Date date;
    private final Money amount;
    private final Money balance;

    public History(OperationType operationType, Date date, Money amount, Money balance) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return operationType == history.operationType && Objects.equals(date, history.date) && Objects.equals(amount, history.amount) && Objects.equals(balance, history.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, date, amount, balance);
    }
}
