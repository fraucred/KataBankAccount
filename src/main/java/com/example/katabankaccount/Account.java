package com.example.katabankaccount;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Account {
    private final List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    private Optional<Transaction> latestTransaction() {
        if (transactions.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(transactions.get(transactions.size() - 1));
    }

    public void deposit(Money money) {
        this.transactions.add(new Transaction(OperationType.DEPOSIT, Date.from(Instant.now()), money, latestTransaction()));
    }

    public void withdraw(Money money) {
        this.transactions.add(new Transaction(OperationType.WITHDRAW, Date.from(Instant.now()), money, latestTransaction()));
    }

    public Money balance() {
        if (latestTransaction().isPresent()) {
            Transaction latestTransaction = latestTransaction().get();
            return latestTransaction.balance();
        }
        return new Money(0);
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public void withdrawAll() {
        withdraw(balance());
    }
}
