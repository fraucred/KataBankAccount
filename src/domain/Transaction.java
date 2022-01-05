package domain;

import java.util.Date;

public class Transaction {

    private TransactionType type;
    private Date date;

    public TransactionType getTransactionType() {
        return type;
    }

    public Transaction setTransactionType(TransactionType transactionType) {
        type = transactionType;
        return this;
    }
}
