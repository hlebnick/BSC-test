package bsc.timertask;

import bsc.dao.PaymentDao;
import bsc.model.Payment;

import java.util.List;
import java.util.TimerTask;

public class OutputStateTimerTask extends TimerTask {

    private PaymentDao paymentDao;

    @Override
    public void run() {
        List<Payment> payments = paymentDao.getAll();
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
