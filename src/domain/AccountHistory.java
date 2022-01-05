package domain;

import java.util.ArrayList;
import java.util.List;

public class AccountHistory {
    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactionsHistory() {
        return transactionList;
    }

    public AccountHistory setTransactionsHistory(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        return this;
    }
}
