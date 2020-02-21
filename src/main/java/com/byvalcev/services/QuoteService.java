package com.byvalcev.services;

import com.byvalcev.model.Quote;
import com.byvalcev.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    @Autowired
    QuoteRepository quoteRepository;

    private final String isinRegex = "[a-zA-Z0-9]{12}";

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public List<Double> getElvlsByIsin(String isin) {
        return quoteRepository
                .findByIsin(isin)
                .stream()
                .map(Quote::getElvl)
                .collect(Collectors.toList());
    }

    public Quote setElvl(Quote quote) {
        Double bid = quote.getBid();
        Double ask = quote.getAsk();
        if (bid == null) {
            quote.setElvl(ask);
        } else {
            if (quote.getElvl() == null) {
                quote.setElvl(bid);
            }
            if (bid > quote.getElvl()) {
                quote.setElvl(bid);
            }
            if (ask < quote.getElvl()) {
                quote.setElvl(ask);
            }
        }
        return quote;
    }

    public List<Double> getElvls() {
        return quoteRepository.selectElvls(100);
    }

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public List<Quote> saveQuotes(List<Quote> quotes) {
        return quoteRepository.saveAll(quotes);
    }

    public List<Quote> getValidQuotes(List<Quote> quotes) {
        return quotes
                .stream()
                .filter(quote -> quote.getBid() < quote.getAsk())
                .filter(quote -> {
                    Pattern pattern = Pattern.compile(isinRegex, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(quote.getIsin());
                    return matcher.find();
                }).collect(Collectors.toList());
    }

    public List<Quote> setElvls(List<Quote> quotes) {
        return quotes
                .stream()
                .map(this::setElvl)
                .collect(Collectors.toList());
    }
}
