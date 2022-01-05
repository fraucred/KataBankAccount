package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private TransactionType type;
    private Date date;
    private BigDecimal accountBalance;
    private BigDecimal transactionAmount;

    public TransactionType getTransactionType() {
        return type;
    }

    public Transaction setTransactionType(TransactionType transactionType) {
        this.type = transactionType;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Transaction setDate(Date date) {
        this.date = date;
        return this;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public Transaction setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public Transaction setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }
}
