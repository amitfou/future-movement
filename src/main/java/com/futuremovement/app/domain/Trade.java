package com.futuremovement.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@Builder
@Getter
@ToString
public class Trade {

    private String recordCode;
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private String expirationDate;
    private String buySellCode;
    private BigDecimal price;
    private Long quantityLong;
    private Long quantityShort;
    private String quantityLongSign;
    private String quantityShortSign;


}
