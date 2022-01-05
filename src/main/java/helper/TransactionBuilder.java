package helper;

import domain.Money;
import domain.Transaction;
import domain.TransactionType;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

public class TransactionBuilder {
    public static Transaction buildDepositTransaction(Money money, BigDecimal previousAccountBalance) {
        return new Transaction()
                .setTransactionType(TransactionType.DEPOSIT)
                .setTransactionAmount(money.getAmount())
                .setDate(Date.from(Instant.now()))
                .setAccountBalance(previousAccountBalance.add(money.getAmount()));
    }

    public static Transaction buildWithdrawalTransaction(Money money, BigDecimal previousAccountBalance) {
        return new Transaction()
                .setTransactionType(TransactionType.WITHDRAWAL)
                .setTransactionAmount(money.getAmount())
                .setDate(Date.from(Instant.now()))
                .setAccountBalance(previousAccountBalance.subtract(money.getAmount()));
    }
}
