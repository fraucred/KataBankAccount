package com.example.katabankaccount;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class Transaction {
    private final OperationType operationType;
    private final Date date;
    private final Money amount;
    private final Money balance;

    public Transaction(OperationType operationType, Date date, Money amount, Optional<Transaction> optionalTransaction) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
        this.balance = addOrSubtractMoney(operationType, amount, optionalTransaction);
    }

    private Money addOrSubtractMoney(OperationType operationType, Money amount, Optional<Transaction> optionalTransaction) {
        if (optionalTransaction.isEmpty() && isDepositOperation(operationType)) {
            return amount.copy();
        }
        if (optionalTransaction.isEmpty() && !isDepositOperation(operationType)) {
            return amount.opposite();
        }
        if (isDepositOperation(operationType)) {
            return optionalTransaction.get().balance.copy().add(amount);
        }
        return optionalTransaction.get().balance.copy().subtract(amount);
    }

    private boolean isDepositOperation(OperationType operationType) {
        return operationType.equals(OperationType.DEPOSIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return operationType == transaction.operationType && Objects.equals(date, transaction.date) && Objects.equals(amount, transaction.amount) && Objects.equals(balance, transaction.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, date, amount, balance);
    }

    public Money balance() {
        return balance;
    }
}
