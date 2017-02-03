package bsc.dao;

import bsc.model.Payment;

import java.util.List;

public interface PaymentDao {

    void apply(Payment payment);

    List<Payment> getAll();
}
