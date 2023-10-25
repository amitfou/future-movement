package com.futuremovement.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TradeSummary {

    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private String expirationDate;
    private Long quantity;
}
