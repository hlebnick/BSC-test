package bsc.dao;

import bsc.model.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InMemoryPaymenDaoTest {

    private PaymentDao paymentDao = new InMemoryPaymentDao();

    @Test
    public void test() {
        List<Payment> all = paymentDao.getAll();
        Assert.assertEquals(0, all.size());
    }
}
