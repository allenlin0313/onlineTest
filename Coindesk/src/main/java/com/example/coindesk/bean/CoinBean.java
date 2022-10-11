package com.example.coindesk.bean;

import lombok.Data;

@Data
public class CoinBean {
    private String chartName;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private String rate_float;
    private String updated;
    private String updatedISO;
    private String updateduk;
    private String codeNameZh;
}
