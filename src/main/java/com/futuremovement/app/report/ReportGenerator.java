package com.futuremovement.app.report;

import com.futuremovement.app.domain.TradeSummary;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface ReportGenerator {

    void generate(List<TradeSummary> tradeSummaries, Writer writer) throws IOException;

}
