package bsc.timertask;

import bsc.dao.PaymentDao;
import bsc.exception.PaymentParseException;
import bsc.model.Payment;
import bsc.util.CommandParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
                String line = reader.readLine();
                if (line != null) {
                    if (line.equals(QUIT_COMMAND)) {
                        break;
                    }
                    processLine(line);
                }
            } catch (IOException | PaymentParseException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("bye");
        System.exit(0);
    }

    private void processLine(String line) {
        Payment payment = CommandParser.parse(line);
        paymentDao.apply(payment);
    }

    public void processFile(String file) {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Can't read file.");
        } catch (PaymentParseException e) {
            System.out.println("Wrong format: [" + line + "]");
        }
    }
}
