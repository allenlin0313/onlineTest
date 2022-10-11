package com.example.coindesk.controller;

import com.example.coindesk.bean.CoinBean;
import com.example.coindesk.entity.CoinDeskEntity;
import com.example.coindesk.repository.CoinDeskRepository;
import com.example.coindesk.trans.CoinDeskTrans;
import com.example.coindesk.vo.CoinVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import com.example.coindesk.service.CoinDeskAPIService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class CoinDeskAPIController {
    private static final String CURRENT_PRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice";
    @Autowired
    private CoinDeskAPIService coindeskAPIService = new CoinDeskAPIService();

    @Autowired
    private CoinDeskTrans coinDeskTrans;
    @Autowired
    private CoinDeskRepository coinDeskRepository;

    @RequestMapping(value = "/getCoinDeskApi", method = RequestMethod.POST)
    @ResponseBody
    public List<CoinBean> getCoinDesk(){
        String url = CURRENT_PRICE_URL+".json";
        System.out.println(url);
        List<CoinBean> list = coindeskAPIService.getApi(url);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/tranData", method = RequestMethod.POST)
    @ResponseBody
    public List<CoinVo> tranData(){
        List<CoinVo> voList = new ArrayList<>();
       for (CoinDeskEntity coinDeskEntity :  coinDeskRepository.findAll()){
           String url = CURRENT_PRICE_URL+"/"+coinDeskEntity.getCode();
           System.out.println(url);
           List<CoinBean> list = coindeskAPIService.getApi(url);
           System.out.println(list);
           for (CoinVo coinVo :coinDeskTrans.beanToVoList(list)){
               if(coinVo.getCode().equals(coinDeskEntity.getCode())){
                   coinVo.setChartName(coinDeskEntity.getChartName());
                   coinVo.setCodeNameZh(coinDeskEntity.getCodeNameZh());
                   voList.add(coinVo);
               }
           }
       };
        return voList;
    }

}
