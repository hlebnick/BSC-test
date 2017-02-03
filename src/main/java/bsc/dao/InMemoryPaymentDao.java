package bsc.dao;

import bsc.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPaymentDao implements PaymentDao {

    private List<Payment> inMemoryStore = new ArrayList<Payment>();

    @Override
    public void apply(Payment payment) {

    }

    @Override
    public List<Payment> getAll() {
        return inMemoryStore;
    }
}
