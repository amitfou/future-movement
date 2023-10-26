package com.futuremovement.app.report;

import com.futuremovement.app.domain.TradeSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CSVReportGeneratorTest {

    @InjectMocks
    private CSVReportGenerator reportGenerator;

    @Test
    void testGenerate() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        TradeSummary t1 = getTradeSummary("4321", "SGX", "NK", 20L);
        TradeSummary t2 = getTradeSummary("1234", "CME", "N1", 10L);

        reportGenerator.generate(List.of(t1, t2), writer);
        String str = stream.toString("UTF-8");
        assertEquals("Client_Information,Product_Information,Total_Transaction_Amount\n" +
                "CL432100020001,SGXFUNK20100910,20\n" +
                "CL123400020001,CMEFUN120100910,10\n", str);
    }

    private static TradeSummary getTradeSummary(String clientNumber, String exchangeCode, String symbol, Long qty) {
        TradeSummary summary = new TradeSummary();
        summary.setClientType("CL");
        summary.setClientNumber(clientNumber);
        summary.setAccountNumber("0002");
        summary.setSubAccountNumber("0001");
        summary.setSymbol(symbol);
        summary.setProductGroupCode("FU");
        summary.setExpirationDate("20100910");
        summary.setQuantity(qty);
        summary.setExchangeCode(exchangeCode);
        return summary;
    }


}
