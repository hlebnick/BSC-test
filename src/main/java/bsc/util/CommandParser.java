package bsc.util;

import bsc.exception.PaymentParseException;
import bsc.model.Payment;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private CommandParser() {}

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^([A-Z]{3}) ([-]?\\d{1,10})$");
    private static final Pattern EXCHANGE_RATE_PATTERN = Pattern.compile("^([A-Z]{3}) ((0.|)\\d{1,8})$");

    public static Payment parseCommand(String input) throws PaymentParseException {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new PaymentParseException("Wrong format.");
        }
        String currency = input.substring(matcher.start(1), matcher.end(1));
        BigDecimal amount = new BigDecimal(input.substring(matcher.start(2), matcher.end(2)));
        return new Payment(currency, amount);
    }

    public static Pair<String, BigDecimal> parseExchangeRate(String input) throws PaymentParseException {
        Matcher matcher = EXCHANGE_RATE_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong exchange rate format.");
        }
        String currency = input.substring(matcher.start(1), matcher.end(1));
        BigDecimal rate = new BigDecimal(input.substring(matcher.start(2), matcher.end(2)));
        return new Pair<>(currency, rate);
    }
}
