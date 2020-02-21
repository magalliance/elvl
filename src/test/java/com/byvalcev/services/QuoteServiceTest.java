package com.byvalcev.services;

import com.byvalcev.model.Quote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuoteServiceTest {

    @Autowired
    private QuoteService quoteService;

    @DisplayName("Test energy level")
    @Test
    void testElvl() {
        Quote quote = new Quote().setIsin("RU000A0JX0J2").setBid(100.7).setAsk(101.1);
        assertEquals(100.7, quoteService.setElvl(quote).getElvl());
    }
}