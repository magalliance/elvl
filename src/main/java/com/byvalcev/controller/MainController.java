package com.byvalcev.controller;

import com.byvalcev.model.Quote;
import com.byvalcev.services.QuoteService;
import com.byvalcev.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class MainController {

    @Autowired
    QuoteService quoteService;

    @Autowired
    MockService mockService;

    @ResponseBody
    @GetMapping("/")
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @ResponseBody
    @GetMapping("/fetch")
    public List<Quote> fetchQuotes() {
        List<Quote> quotes = mockService.fetchData(100);
        List<Quote> validQuotes = quoteService.getValidQuotes(quotes);
        List<Quote> quotesWithsElvl = quoteService.setElvls(validQuotes);
        List<Quote> resQuotes = quoteService.saveQuotes(quotesWithsElvl);
        return resQuotes.stream().limit(10).collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/isin/{isin}")
    public List<Double> findByIsin(@PathVariable String isin) {
        return quoteService.getElvlsByIsin(isin);
    }

    @ResponseBody
    @GetMapping("/elvls")
    public List<Double> getElvls() {
        return quoteService.getElvls();
    }
}
