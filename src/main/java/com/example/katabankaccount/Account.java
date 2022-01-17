package com.example.katabankaccount;

import com.example.katabankaccount.provider.DateProvider;

import java.util.ArrayList;
import java.util.List;

import static com.example.katabankaccount.Money.createMoneyFromPositiveValue;

public class Account {
    private final List<Transaction> transactions = new ArrayList<>();
    private final DateProvider dateProvider;

    public Account(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }


    public void deposit(Money money) {
        this.transactions.add(new Transaction(OperationType.DEPOSIT, dateProvider.nowDefaultDate(), money));
    }

    public void withdraw(Money money) {
        this.transactions.add(new Transaction(OperationType.WITHDRAW, dateProvider.nowDefaultDate(), money));
    }

    public Money balance() {
        if (transactions.isEmpty()) {
            return createMoneyFromPositiveValue(0);
        }
        return transactions.stream()
                .map(Transaction::getAmountByOperationType)
                .reduce(Money::add)
                .orElse(createMoneyFromPositiveValue(0));
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public void withdrawAll() {
        withdraw(balance());
    }
}
