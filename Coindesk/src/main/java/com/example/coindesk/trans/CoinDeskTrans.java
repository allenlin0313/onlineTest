package com.example.coindesk.trans;

import com.example.coindesk.bean.CoinBean;
import com.example.coindesk.entity.CoinDeskEntity;
import com.example.coindesk.vo.CoinVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class CoinDeskTrans {

    public List<CoinDeskEntity> beanToEntityList(List<CoinBean> list){
        List<CoinDeskEntity> coinDeskEntityList = new ArrayList<>();
        for (CoinBean coinBean : list){
            CoinDeskEntity coinDeskEntity = new CoinDeskEntity();
            coinDeskEntity.setCode(coinBean.getCode());
            coinDeskEntity.setChartName(coinBean.getChartName());
            coinDeskEntity.setCodeNameZh(coinBean.getCodeNameZh());
            coinDeskEntityList.add(coinDeskEntity);
        }
        return coinDeskEntityList;
    }

    public List<CoinBean> entityToBeanList(List<CoinDeskEntity> list){
        List<CoinBean> coinBeans = new ArrayList<>();
        for (CoinDeskEntity coinDeskEntity : list){
            CoinBean coinBean = new CoinBean();
            coinBean.setCode(coinDeskEntity.getCode());
            coinBean.setChartName(coinDeskEntity.getChartName());
            coinBean.setCodeNameZh(coinDeskEntity.getCodeNameZh());
            coinBeans.add(coinBean);
        }
        return coinBeans;
    }
    public List<CoinVo> entityToVoList(List<CoinDeskEntity> list){
        List<CoinVo> coinVos = new ArrayList<>();
        for (CoinDeskEntity coinDeskEntity : list){
            CoinVo coinVo = new CoinVo();
            coinVo.setCode(coinDeskEntity.getCode());
            coinVo.setChartName(coinDeskEntity.getChartName());
            coinVo.setCodeNameZh(coinDeskEntity.getCodeNameZh());
            coinVos.add(coinVo);
        }
        return coinVos;
    }
    public List<CoinVo> beanToVoList(List<CoinBean> list){
        List<CoinVo> coinVos = new ArrayList<>();
        for (CoinBean coinBean : list){
            CoinVo coinVo = new CoinVo();
            coinVo.setCode(coinBean.getCode());
            coinVo.setChartName(coinBean.getChartName());
            coinVo.setRate(coinBean.getRate());
            coinVo.setCodeNameZh(coinBean.getCodeNameZh());
            coinVo.setRate_float(coinBean.getRate_float());
            coinVo.setUpdateTime(changeTime(coinBean.getUpdatedISO()).replaceAll("00:00",""));
            coinVos.add(coinVo);
        }
        return coinVos;
    }
    public String changeTime(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00").withZone(ZoneOffset.UTC);
        LocalDateTime localDateTime = LocalDateTime.parse(time,formatter);
        System.out.println(localDateTime);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime.atZone(ZoneId.of("UTC")));
    }
}
