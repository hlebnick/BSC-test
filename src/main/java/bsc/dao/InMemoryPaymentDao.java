package bsc.dao;

import bsc.model.Payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPaymentDao implements PaymentDao {

    private Map<String, BigDecimal> inMemoryStore = new HashMap<>();

    @Override
    public void apply(Payment payment) {
        if (!inMemoryStore.containsKey(payment.getCurrency())) {
            inMemoryStore.put(payment.getCurrency(), payment.getAmount());
        } else {
            BigDecimal original = inMemoryStore.get(payment.getCurrency());
            inMemoryStore.put(payment.getCurrency(), original.add(payment.getAmount()));
        }
    }

    @Override
    public Map<String, BigDecimal> getAll() {
        return inMemoryStore;
    }
}
