package bsc.util;

import bsc.exception.PaymentParseException;
import bsc.model.Payment;

public class CommandParser {

    private CommandParser() {}

    public static Payment parse(String input) throws PaymentParseException {
        throw new PaymentParseException("exc");
    }
}
