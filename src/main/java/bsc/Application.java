package bsc;

import bsc.dao.InMemoryPaymentDao;
import bsc.dao.PaymentDao;
import bsc.service.ExchangeService;
import bsc.timertask.InputProcessor;
import bsc.timertask.OutputStateTimerTask;

import java.util.Timer;

public class Application {

    public static final int OUTPUT_STATE_PERIOD = 60000;
    public static final String USD_EXCHANGE_RATES_FILE = "usdExchangeRates.txt";

    private PaymentDao paymentDao = new InMemoryPaymentDao();
    private ExchangeService exchangeService = new ExchangeService();

    public static void main(String[] args) {
        new Application().run(args);
    }

    private void run(String[] args) {
        exchangeService.loadData(USD_EXCHANGE_RATES_FILE);

        OutputStateTimerTask task = new OutputStateTimerTask(paymentDao, exchangeService);
        new Timer().schedule(task, OUTPUT_STATE_PERIOD, OUTPUT_STATE_PERIOD);

        InputProcessor inputProcessor = new InputProcessor(paymentDao);
        if (args.length != 0) {
            inputProcessor.processFile(args[0]);
        }
        inputProcessor.run();
    }
}
