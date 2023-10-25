package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.springframework.core.io.Resource;

import java.util.List;

public interface TradeParser<T extends Resource> {
    List<Trade> parse(T resource);
}
