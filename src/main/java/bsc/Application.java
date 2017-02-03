package bsc;

import bsc.dao.InMemoryPaymentDao;
import bsc.dao.PaymentDao;
import bsc.exception.PaymentParseException;
import bsc.model.Payment;
import bsc.timertask.OutputStateTimerTask;
import bsc.util.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

public class Application {

    public static final String QUIT_COMMAND = "quit";
    public static final int OUTPUT_STATE_PERIOD = 10000;

    private PaymentDao paymentDao = new InMemoryPaymentDao();

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        OutputStateTimerTask task = new OutputStateTimerTask();
        task.setPaymentDao(paymentDao);
        new Timer().schedule(task, OUTPUT_STATE_PERIOD, OUTPUT_STATE_PERIOD);

        String input = "";
        while (!input.equals(QUIT_COMMAND)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                input = reader.readLine();
                Payment payment = CommandParser.parse(input);
                paymentDao.apply(payment);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PaymentParseException e) {
                e.printStackTrace();
            }

            System.out.println(input);
        }
        System.out.println("bye");
    }
}
