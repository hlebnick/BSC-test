package bsc.timertask;

import bsc.dao.PaymentDao;
import bsc.service.ExchangeService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TimerTask;

import static java.text.MessageFormat.format;

public class OutputStateTimerTask extends TimerTask {

    private PaymentDao paymentDao;
    private ExchangeService exchangeService;

    public OutputStateTimerTask(PaymentDao paymentDao, ExchangeService exchangeService) {
        this.paymentDao = paymentDao;
        this.exchangeService = exchangeService;
    }

    @Override
    public void run() {
        Map<String, BigDecimal> state = paymentDao.getAll();
        for (Map.Entry<String, BigDecimal> entry : state.entrySet()) {
            BigDecimal rate = exchangeService.getRate(entry.getKey());
            if (rate != null) {
                String output = format("{0} {1} (USD {2})", entry.getKey(), entry.getValue(),
                        rate.multiply(entry.getValue()).setScale(2, BigDecimal.ROUND_FLOOR));
                System.out.println(output);
            } else {
                System.out.println(format("{0} {1}", entry.getKey(), entry.getValue().toString()));
            }
        }
    }

}
