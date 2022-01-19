package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Account {
    private static final Amount DEFAULT_INITIAL_BALANCE = new Amount(BigDecimal.valueOf(0.0));
    private final List<Transaction> transactions;
    private final DateProvider dateProvider;

    public Account(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        transactions = new ArrayList<>();
    }

    public void deposit(Money money) {
        this.transactions.add(new Transaction(OperationType.DEPOSIT, dateProvider.now(), money));
    }

    public void withdraw(Money money) {
        this.transactions.add(new Transaction(OperationType.WITHDRAW, dateProvider.now(), money));
    }

    public void withdrawAllMoney() {
        if (hasSavings()) {
            withdraw((Money) balance());
        }
    }

    public boolean hasSavings() {
        return balance().sign() > 0;
    }

    public Amount balance() {
        return transactions.stream()
                .map(transaction -> transaction.isDepositOperation() ? transaction.getAmount() : transaction.getAmount().opposite())
                .reduce(Amount::add)
                .orElse(DEFAULT_INITIAL_BALANCE);
    }

    public List<Transaction> transactions() {
        return transactions;
    }
}
