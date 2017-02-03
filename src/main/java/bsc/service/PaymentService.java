package bsc.service;

import bsc.dao.InMemoryPaymentDao;
import bsc.dao.PaymentDao;
import bsc.model.Payment;

public class PaymentService {

    private PaymentDao paymentDao = new InMemoryPaymentDao();

    public void apply(Payment payment) {

    }
}
