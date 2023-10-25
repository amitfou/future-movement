package com.futuremovement.app.aggregate;

import com.futuremovement.app.domain.Trade;
import com.futuremovement.app.domain.TradeSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TradeAggregatorImplTest {

    @InjectMocks
    private TradeAggregatorImpl aggregator;

    @Test
    void testAggregate() {
        Trade t1 = getTrade("4321", "SGX", "NK", 1L, 0L);
        Trade t2 = getTrade("4321", "SGX", "N1", 0L, 1L);
        Trade t3 = getTrade("4321", "SGX", "NK", 1L, 0L);
        Trade t4 = getTrade("1234", "SGX", "NK", 0L, 1L);

        List<TradeSummary> aggregate = aggregator.aggregate(List.of(t1, t2, t3, t4));
        assertEquals(3, aggregate.size());
        List<TradeSummary> summary1 = aggregate.stream().filter(s -> s.getClientNumber().equals("4321") && s.getSymbol().equals("NK")).collect(Collectors.toList());
        assertEquals(1, summary1.size());
        assertEquals(2, summary1.get(0).getQuantity());
    }

    private static Trade getTrade(String clientNumber, String exchangeCode, String symbol, Long longQty, Long shortQty) {
        return Trade.builder()
                .recordCode("315")
                .clientType("CL")
                .clientNumber(clientNumber)
                .accountNumber("0002")
                .subAccountNumber("0001")
                .symbol(symbol)
                .productGroupCode("FU")
                .expirationDate("20100910")
                .quantityShort(shortQty)
                .quantityLong(longQty)
                .exchangeCode(exchangeCode)
                .build();
    }
}
