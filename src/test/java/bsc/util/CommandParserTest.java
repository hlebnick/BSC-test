package bsc.util;

import bsc.exception.PaymentParseException;
import bsc.model.Payment;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CommandParserTest {

    @Test
    public void correctCommandTest() {
        String input = "USD 1000";
        Payment parse = CommandParser.parseCommand(input);
        Assert.assertEquals("USD", parse.getCurrency());
        Assert.assertEquals(new BigDecimal(1000), parse.getAmount());
    }

    @Test
    public void correctNegativeCommandTest() {
        String input = "USD -1000";
        Payment parse = CommandParser.parseCommand(input);
        Assert.assertEquals("USD", parse.getCurrency());
        Assert.assertEquals(new BigDecimal(-1000), parse.getAmount());
    }

    @Test(expected = PaymentParseException.class)
    public void tooMuchSymbolsTest() {
        String input = "USD 10000000000";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest() {
        String input = "USD --1000";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest2() {
        String input = "USDD 1000";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest3() {
        String input = "US 1000";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest4() {
        String input = "USD";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest5() {
        String input = "USD abc";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest6() {
        String input = "USDD 100-";
        CommandParser.parseCommand(input);
    }

    @Test(expected = PaymentParseException.class)
    public void wrongFormatTest7() {
        String input = "USDD1000";
        CommandParser.parseCommand(input);
    }
}
