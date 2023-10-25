package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FlatFileTradeAssembler implements TradeAssembler<String> {
    @Override
    public Trade assemble(String row) {
        return Trade.builder()
                .recordCode(row.substring(0, 3).trim())
                .clientType(row.substring(3,7).trim())
                .clientNumber(row.substring(7,11).trim())
                .accountNumber(row.substring(11,15).trim())
                .subAccountNumber(row.substring(15,19).trim())
                .exchangeCode(row.substring(27, 31).trim())
                .productGroupCode(row.substring(25, 27).trim())
                .symbol(row.substring(31,37).trim())
                .expirationDate(row.substring(37,45))
                .buySellCode(row.substring(50,51))
                .price(getPrice(row))
                .quantityLong(getQuantityLong(row))
                .quantityShort(getQuantityShort(row))
                .quantityLongSign(row.substring(51, 52).trim())
                .quantityShortSign(row.substring(62, 63).trim())
                .build();
    }

    private Long getQuantityLong(String trade) {
        String strValue = trade.substring(52, 62);
        return Long.parseLong(strValue);
    }

    private Long getQuantityShort(String trade) {
        String strValue = trade.substring(63, 73);
        return Long.parseLong(strValue);
    }

    private BigDecimal getPrice(String trade) {
        String strValue = trade.substring(147, 162);
        BigDecimal bigDecimal = new BigDecimal(strValue);
        return bigDecimal.divide(BigDecimal.valueOf(10000000));
    }
}
