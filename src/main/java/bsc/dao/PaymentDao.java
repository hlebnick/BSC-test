package bsc.dao;

import bsc.model.Payment;

import java.math.BigDecimal;
import java.util.Map;

public interface PaymentDao {

    void apply(Payment payment);

    Map<String, BigDecimal> getAll();
}
