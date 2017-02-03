package bsc.timertask;

import bsc.service.PaymentService;

import java.util.TimerTask;

public class OutputStateTimerTask extends TimerTask {

    private PaymentService paymentService;

    @Override
    public void run() {
        System.out.println("timer task");
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
