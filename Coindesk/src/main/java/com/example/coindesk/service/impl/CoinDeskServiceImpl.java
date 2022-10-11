package com.example.coindesk.service.impl;

import com.example.coindesk.entity.CoinDeskEntity;
import com.example.coindesk.repository.CoinDeskRepository;
import com.example.coindesk.service.CoinDeskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoinDeskServiceImpl implements CoinDeskService {
    @Resource
    private CoinDeskRepository coinDeskRepository;

    @Override
    public List<CoinDeskEntity> coinDeskEntityList(){
        return coinDeskRepository.findAll();
    }
}
