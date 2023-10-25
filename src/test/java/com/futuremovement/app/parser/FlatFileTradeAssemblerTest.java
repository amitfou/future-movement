package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FlatFileTradeAssemblerTest {

    String SAMPLE_TRADE_ROW = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
    @InjectMocks
    private FlatFileTradeAssembler assembler;


    @Test
    void testAssemble() {
        Trade trade = assembler.assemble(SAMPLE_TRADE_ROW);
        assertEquals("CL", trade.getClientType());
        assertEquals("4321", trade.getClientNumber());
        assertEquals("0002", trade.getAccountNumber());
        assertEquals("0001", trade.getSubAccountNumber());
        assertEquals("SGX", trade.getExchangeCode());
        assertEquals("FU", trade.getProductGroupCode());
        assertEquals("NK", trade.getSymbol());
        assertEquals("20100910", trade.getExpirationDate());
        assertEquals(BigDecimal.valueOf(9250), trade.getPrice());
        assertEquals(1, trade.getQuantityLong());
        assertEquals(0, trade.getQuantityShort());
        assertEquals("B", trade.getBuySellCode());
        assertEquals("315", trade.getRecordCode());
        assertEquals("", trade.getQuantityLongSign());
        assertEquals("", trade.getQuantityShortSign());

    }
}
