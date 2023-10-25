package com.futuremovement.app.report;

import com.futuremovement.app.domain.TradeSummary;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CSVReportGenerator implements ReportGenerator {
    @Override
    public void generate(List<TradeSummary> tradeSummaries, Writer streamWriter) throws IOException {
        String[] headers = { "Client_Information","Product_Information","Total_Transaction_Amount"};
        CSVWriter writer = new CSVWriter(streamWriter, ',');
        writer.writeNext(headers);
        tradeSummaries.forEach(summary -> {
            writer.writeNext(new String[] {getClientInformation(summary), getProductInformation(summary), summary.getQuantity().toString()});
        });
        streamWriter.close();
    }

    private static String getProductInformation(TradeSummary summary) {
        return summary.getExchangeCode() + summary.getProductGroupCode() + summary.getSymbol() + summary.getExpirationDate();
    }

    private static String getClientInformation(TradeSummary summary) {
        return summary.getClientType() + summary.getClientNumber() + summary.getAccountNumber() + summary.getSubAccountNumber();
    }
}
