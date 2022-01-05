package service;

import domain.BankClient;
import domain.Money;
import domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

import static helper.TransactionBuilder.buildDepositTransaction;

public class BankMoneyDepositService {
    public void deposit(BankClient bankClient, Money moneyToBeSaved) {
        final List<Transaction> transactionsHistory = bankClient.getAccountHistory().getTransactionsHistory();
        final BigDecimal previousAccountBalance;

        if (transactionsHistory.isEmpty()) {
            previousAccountBalance = BigDecimal.ZERO;
        } else {
            int lastTransactionIndex = transactionsHistory.size() - 1;
            final Transaction latestTransaction = transactionsHistory.get(lastTransactionIndex);
            previousAccountBalance = latestTransaction.getAccountBalance();
        }

        transactionsHistory.add(buildDepositTransaction(moneyToBeSaved, previousAccountBalance));
    }
}
