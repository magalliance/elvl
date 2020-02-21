package com.byvalcev.services;

import com.byvalcev.model.Quote;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class MockService {
    private String[] isinList = new String[]{"RU000A0JX0J2", "ILLEGAL_ISIN", "RU000A0JX0J3", "RU000A0JX0J4", "RU000A0JX0J5"};
    public List<Quote> fetchData(int maxItems) {
        List<Quote> quotes = new ArrayList<>();
        IntStream.range(0, maxItems).forEach(i -> {
            String isinRandom = isinList[(int) (Math.random() * isinList.length)];
            Random r = new Random();
            double bid = BigDecimal.valueOf(100 + r.nextDouble()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            double ask = BigDecimal.valueOf(100 + r.nextDouble()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            quotes.add(new Quote().setIsin(isinRandom).setBid(bid).setAsk(ask));
        });
        return quotes;
    }
}
