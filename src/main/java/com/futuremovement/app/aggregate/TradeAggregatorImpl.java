package com.futuremovement.app.aggregate;

import com.futuremovement.app.domain.Trade;
import com.futuremovement.app.domain.TradeSummary;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Component
public class TradeAggregatorImpl implements TradeAggregator {
    @Override
    public List<TradeSummary> aggregate(List<Trade> trades) {
        Collection<TradeSummary> values = trades
                .parallelStream()
                .collect(Collectors.groupingBy(
                        trade -> groupBy(trade), Collector.of(
                                TradeSummary::new,
                                (tradeSummary, trade) -> accumulate(tradeSummary, trade),
                                (tradeSummary1, tradeSummary2) -> {
                                    tradeSummary1.setQuantity(tradeSummary1.getQuantity() + tradeSummary2.getQuantity());
                                    return tradeSummary1;
                                }
                        )
                )).values();
        return values.stream().toList();
    }

    private static void accumulate(TradeSummary tradeSummary, Trade trade) {
        if (tradeSummary.getQuantity() == null) {
            tradeSummary.setClientNumber(trade.getClientNumber());
            tradeSummary.setAccountNumber(trade.getAccountNumber());
            tradeSummary.setClientType(trade.getClientType());
            tradeSummary.setQuantity(0L);
            tradeSummary.setSymbol(trade.getSymbol());
            tradeSummary.setExchangeCode(trade.getExchangeCode());
            tradeSummary.setExpirationDate(trade.getExpirationDate());
            tradeSummary.setProductGroupCode(trade.getProductGroupCode());
            tradeSummary.setSubAccountNumber(trade.getSubAccountNumber());
        }
        tradeSummary.setQuantity(tradeSummary.getQuantity() + trade.getQuantityLong() - trade.getQuantityShort());
    }

    private static String groupBy(Trade trade) {
        return trade.getClientNumber() + trade.getProductGroupCode() + trade.getAccountNumber() +
                trade.getSubAccountNumber() + trade.getSymbol();
    }
}
