package com.example.coindesk.controller;

import com.example.coindesk.bean.CoinBean;
import com.example.coindesk.entity.CoinDeskEntity;
import com.example.coindesk.repository.CoinDeskRepository;
import com.example.coindesk.service.CoinDeskAPIService;
import com.example.coindesk.service.CoinDeskService;
import com.example.coindesk.service.impl.CoinDeskServiceImpl;
import com.example.coindesk.trans.CoinDeskTrans;
import com.example.coindesk.vo.CoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/api/v2")
public class CoinDeskController {

    private static final String CURRENT_PRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    @Autowired
    private CoinDeskServiceImpl coinDeskService;
    @Autowired
    private CoinDeskRepository coinDeskRepository;
    @Autowired
    private CoinDeskAPIService coinDeskAPIService;

    @Autowired
    private CoinDeskTrans coinDeskTrans;

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public List<CoinDeskEntity> getList(){
        return coinDeskService.coinDeskEntityList();
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody CoinDeskEntity coinDeskEntityIn){
        if(coinDeskEntityIn.getCode() == null || coinDeskEntityIn.getCode().equals("")){
            return "Code Can not be empty";
        }
        if(coinDeskEntityIn.getChartName() == null || coinDeskEntityIn.getChartName().equals("")){
            return "ChartName Can not be empty";
        }
        if(coinDeskEntityIn.getCodeNameZh() == null || coinDeskEntityIn.getCodeNameZh().equals("")){
            return "CodeNameZh Can not be empty";
        }
        if(coinDeskRepository.existsByCode(coinDeskEntityIn.getCode())){
            return "error add code already exists";
        };
        coinDeskRepository.save(coinDeskEntityIn);
        return "success add";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public List<CoinDeskEntity> update(@RequestBody CoinDeskEntity coinDeskEntityIn){
        if(coinDeskEntityIn.getCode() == null || coinDeskEntityIn.getCode().equals("")){
            return null;
        }
        CoinDeskEntity coinDeskEntity = coinDeskRepository.findCoinDeskEntityByCode(coinDeskEntityIn.getCode());
        coinDeskEntity.setChartName(coinDeskEntityIn.getChartName());
        coinDeskEntity.setCodeNameZh(coinDeskEntityIn.getCodeNameZh());
        coinDeskRepository.save(coinDeskEntity);
        return coinDeskService.coinDeskEntityList();
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody CoinDeskEntity coinDeskEntityIn){
        if(coinDeskEntityIn.getCode() == null || coinDeskEntityIn.getCode().equals("")){
            return "delete error Can not be empty ";
        }
        if(!coinDeskRepository.existsByCode(coinDeskEntityIn.getCode())){
            return "error add code does not exist";
        };
        coinDeskRepository.delete(coinDeskEntityIn);
        return "success delete";
    }
}
