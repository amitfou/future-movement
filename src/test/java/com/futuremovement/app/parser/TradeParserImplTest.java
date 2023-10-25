package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TradeParserImplTest {

    @InjectMocks
    private TradeParserImpl parser;

    @Spy
    private FlatFileTradeAssembler assembler;

    @Test
    void testParse() {
        List<Trade> trades = parser.parse(new ClassPathResource("static/input.txt"));
        System.out.println(trades);
        assertEquals(4, trades.size());
    }
}
