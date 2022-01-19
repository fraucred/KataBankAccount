package com.example.katabankaccount;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private final OperationType operationType;
    private final LocalDateTime date;
    private final Money amount;

    public Transaction(OperationType operationType, LocalDateTime date, Money amount) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
    }

    public boolean isDepositOperation() {
        return OperationType.DEPOSIT.equals(operationType);
    }

    public Amount getAmount() {
        return amount;
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
