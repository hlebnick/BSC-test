package bsc.model;

import java.math.BigDecimal;

public class Payment {

    private String currency;
    private BigDecimal amount;

    public Payment(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return currency + ' ' + amount;
    }
}
