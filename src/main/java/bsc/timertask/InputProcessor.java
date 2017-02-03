package bsc.timertask;

import bsc.dao.PaymentDao;
import bsc.exception.PaymentParseException;
import bsc.model.Payment;
import bsc.util.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputProcessor {

    public static final String QUIT_COMMAND = "quit";

    private PaymentDao paymentDao;

    public InputProcessor(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void run() {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = reader.readLine();
                if (input.equals(QUIT_COMMAND)) {
                    break;
                }
                Payment payment = CommandParser.parse(input);
                paymentDao.apply(payment);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PaymentParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("bye");
        System.exit(0);
    }
}
