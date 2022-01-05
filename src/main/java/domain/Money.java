package domain;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public Money setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}