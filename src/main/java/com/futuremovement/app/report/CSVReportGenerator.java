package com.futuremovement.app.report;

import com.futuremovement.app.domain.TradeSummary;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static com.opencsv.CSVWriter.NO_QUOTE_CHARACTER;

@Component
public class CSVReportGenerator implements ReportGenerator {
    private static Logger LOG = LoggerFactory.getLogger(CSVReportGenerator.class);

    @Override
    public void generate(List<TradeSummary> tradeSummaries, Writer streamWriter) throws IOException {
        LOG.info("Generating csv report for {} records", tradeSummaries.size());
        String[] headers = { "Client_Information","Product_Information","Total_Transaction_Amount"};
        try {
            CSVWriter writer = new CSVWriter(streamWriter, ',', NO_QUOTE_CHARACTER);
            writer.writeNext(headers);
            tradeSummaries.forEach(summary -> {
                writer.writeNext(new String[]{getClientInformation(summary), getProductInformation(summary), summary.getQuantity().toString()});
            });
        } catch (Exception e) {
            LOG.error("CSV report generation failed", e);
        } finally {
            streamWriter.close();
        }
        LOG.info("CSV report generation completed.");
    }

    private static String getProductInformation(TradeSummary summary) {
        return summary.getExchangeCode() + summary.getProductGroupCode() + summary.getSymbol() + summary.getExpirationDate();
    }

    private static String getClientInformation(TradeSummary summary) {
        return summary.getClientType() + summary.getClientNumber() + summary.getAccountNumber() + summary.getSubAccountNumber();
    }
}
