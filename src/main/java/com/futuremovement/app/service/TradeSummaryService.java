package com.futuremovement.app.service;

import com.futuremovement.app.aggregate.TradeAggregator;
import com.futuremovement.app.domain.Trade;
import com.futuremovement.app.domain.TradeSummary;
import com.futuremovement.app.parser.TradeParser;
import com.futuremovement.app.report.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class TradeSummaryService {

    private static final String TRADE_INPUT_FILE_PATH = "/static/input.txt";

    @Autowired
    private TradeParser<Resource> parser;

    @Autowired
    private TradeAggregator aggregator;

    @Autowired
    private ReportGenerator reportGenerator;

    public void tradeSummaryReport(Writer writer) throws IOException {
        List<Trade> trades = parser.parse(new ClassPathResource(TRADE_INPUT_FILE_PATH));
        List<TradeSummary> tradeSummaries = aggregator.aggregate(trades);
        reportGenerator.generate(tradeSummaries, writer);
    }


}

