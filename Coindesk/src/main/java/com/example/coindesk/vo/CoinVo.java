package com.example.coindesk.vo;

import lombok.Data;

@Data
public class CoinVo {
    private String code;
    private String codeNameZh;
    private String chartName;
    private String rate;
    private String rate_float;
    private String updateTime;
}
