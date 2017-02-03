package bsc.timertask;

import bsc.dao.PaymentDao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TimerTask;

public class OutputStateTimerTask extends TimerTask {

    private PaymentDao paymentDao;

    @Override
    public void run() {
        Map<String, BigDecimal> state = paymentDao.getAll();
        for (Map.Entry<String, BigDecimal> entry : state.entrySet()) {
            System.out.println(entry.getKey() + ' ' + entry.getValue());
        }
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
