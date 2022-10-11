package com.example.coindesk.service;

import com.example.coindesk.bean.CoinBean;
import com.example.coindesk.repository.CoinDeskRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CoinDeskAPIService {
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    Gson gson = new Gson();

    @Autowired
    private CoinDeskRepository coinDeskRepository;

    public List<CoinBean> getApi(String url) {
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        JsonObject gsonMap = gson.fromJson(json, JsonObject.class);
        CoinBean timeBean = gson.fromJson(gsonMap.get("time"), CoinBean.class);
        String chartName = gson.fromJson(gsonMap.get("chartName"), String.class);
        JsonObject bpi = gson.fromJson(gsonMap.get("bpi"), JsonObject.class);
        JsonObject bpiJson = bpi;
        Set<String> keySet = bpi.keySet();
        List<CoinBean> coinBeanList = new ArrayList<>();
        for (String key : keySet){
            CoinBean coinBean = gson.fromJson(bpiJson.get(key), CoinBean.class);
            coinBean.setChartName(chartName);
            coinBean.setUpdated(timeBean.getUpdated());
            coinBean.setUpdatedISO(timeBean.getUpdatedISO());
            coinBean.setUpdateduk(timeBean.getUpdateduk());
            coinBean.setCodeNameZh(Currency.getInstance(coinBean.getCode()).getDisplayName(Locale.TAIWAN));
            coinBeanList.add(coinBean);
        }
        return coinBeanList;
        }
}
