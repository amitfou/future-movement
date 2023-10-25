package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;

public interface TradeAssembler<T> {
    Trade assemble(T value);
}
