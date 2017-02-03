package bsc;

import bsc.dao.InMemoryPaymentDao;
import bsc.dao.PaymentDao;
import bsc.timertask.InputProcessor;
import bsc.timertask.OutputStateTimerTask;

import java.util.Timer;

public class Application {

    public static final int OUTPUT_STATE_PERIOD = 10000;

    private PaymentDao paymentDao = new InMemoryPaymentDao();

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        OutputStateTimerTask task = new OutputStateTimerTask();
        task.setPaymentDao(paymentDao);
        new Timer().schedule(task, OUTPUT_STATE_PERIOD, OUTPUT_STATE_PERIOD);

        new InputProcessor(paymentDao).run();
    }
}
