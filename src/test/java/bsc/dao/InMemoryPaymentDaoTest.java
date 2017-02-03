package bsc.dao;

import bsc.model.Payment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class InMemoryPaymentDaoTest {

    private PaymentDao paymentDao;

    @Before
    public void before() {
        paymentDao = new InMemoryPaymentDao();
    }

    @Test
    public void test() {
        Map<String, BigDecimal> state = paymentDao.getAll();
        Assert.assertEquals(0, state.size());
    }

    @Test
    public void severalCurrenciesTest() {
        paymentDao.apply(new Payment("USD", new BigDecimal(1000)));
        paymentDao.apply(new Payment("RMB", new BigDecimal(100)));
        paymentDao.apply(new Payment("HKD", new BigDecimal(200)));

        Map<String, BigDecimal> state = paymentDao.getAll();
        Assert.assertEquals(3, state.size());
    }

    @Test
    public void oneCurrencySubAndTest() {
        paymentDao.apply(new Payment("USD", new BigDecimal(1000)));
        paymentDao.apply(new Payment("USD", new BigDecimal(100)));
        paymentDao.apply(new Payment("USD", new BigDecimal(-200)));

        Map<String, BigDecimal> state = paymentDao.getAll();
        Assert.assertEquals(1, state.size());

        Assert.assertEquals(new BigDecimal(900), state.get("USD"));
    }

    @Test
    public void negativeStateResult() {
        paymentDao.apply(new Payment("USD", new BigDecimal(0)));
        paymentDao.apply(new Payment("USD", new BigDecimal(-200)));
        paymentDao.apply(new Payment("USD", new BigDecimal(100)));
        paymentDao.apply(new Payment("USD", new BigDecimal(-500)));

        Map<String, BigDecimal> state = paymentDao.getAll();
        Assert.assertEquals(1, state.size());

        Assert.assertEquals(new BigDecimal(-600), state.get("USD"));
    }
}
