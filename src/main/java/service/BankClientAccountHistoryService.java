package service;

import domain.AccountHistory;
import domain.BankClient;
import domain.Transaction;

import java.util.List;

public class BankClientAccountHistoryService {
    public List<Transaction> checkAccountHistoryOperations(BankClient bankClient) {
        final AccountHistory accountHistory = bankClient.getAccountHistory();
        final List<Transaction> transactionsHistory = accountHistory.getTransactionsHistory();

        if (transactionsHistory.isEmpty()) {
            System.out.println("There are none transactions in the history");
            return null;
        }

        transactionsHistory.forEach(transaction -> System.out.printf("Transaction of type %s on date %s with the following amount %s. Balance is now %s%n", transaction.getTransactionType(), transaction.getDate(),
                transaction.getTransactionAmount(), transaction.getAccountBalance()));

        return transactionsHistory;
    }
}
