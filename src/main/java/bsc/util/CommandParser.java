package bsc.util;

import bsc.exception.PaymentParseException;
import bsc.model.Payment;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private CommandParser() {}

    private static final Pattern PATTERN = Pattern.compile("^([A-Z]{3}) ([-]?\\d{1,10})$");

    public static Payment parse(String input) throws PaymentParseException {
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new PaymentParseException("Wrong format.");
        }
        if (matcher.groupCount() != 2) {
            throw new PaymentParseException("Wrong format.");
        }
        String currency = input.substring(matcher.start(1), matcher.end(1));
        BigDecimal amount = new BigDecimal(input.substring(matcher.start(2), matcher.end(2)));
        return new Payment(currency, amount);
    }
}
