package com.example.katabankaccount;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private final OperationType operationType;
    private final Date date;
    private final Money amount;

    public Transaction(OperationType operationType, Date date, Money amount) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
    }

    public Money getAmountByOperationType() {
        if (isDepositOperation(operationType)) {
            return this.amount;
        }
        return this.amount.opposite();
    }

    private boolean isDepositOperation(OperationType operationType) {
        return operationType.equals(OperationType.DEPOSIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return operationType == transaction.operationType && Objects.equals(date, transaction.date) && Objects.equals(amount, transaction.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, date, amount);
    }

}
