package bsc.dao;

import bsc.model.Payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPaymentDao implements PaymentDao {

    private Map<String, BigDecimal> inMemoryStore = new HashMap<>();

    @Override
    public synchronized void apply(Payment payment) {
        if (!inMemoryStore.containsKey(payment.getCurrency())) {
            inMemoryStore.put(payment.getCurrency(), payment.getAmount());
        } else {
            BigDecimal original = inMemoryStore.get(payment.getCurrency());
            BigDecimal newValue = original.add(payment.getAmount());
            if (newValue.equals(new BigDecimal(0))) {
                inMemoryStore.remove(payment.getCurrency());
            } else {
                inMemoryStore.put(payment.getCurrency(), newValue);
            }
        }
    }

    @Override
    public Map<String, BigDecimal> getAll() {
        return inMemoryStore;
    }
}
