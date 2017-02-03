package bsc.service;

import bsc.util.CommandParser;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExchangeService {

    private Map<String, BigDecimal> rates = new HashMap<>();

    public void loadData(String file) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = br.readLine()) != null) {
                Pair<String, BigDecimal> rate = CommandParser.parseExchangeRate(line);
                rates.put(rate.getKey(), rate.getValue());
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Can't read exchange rates file.");
        }
    }

    public BigDecimal getRate(String currency) {
        return rates.get(currency);
    }
}
