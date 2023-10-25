package com.futuremovement.app.aggregate;

import com.futuremovement.app.domain.Trade;
import com.futuremovement.app.domain.TradeSummary;

import java.util.List;

public interface TradeAggregator {
    List<TradeSummary> aggregate(List<Trade> trades);
}
