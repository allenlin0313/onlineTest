package com.example.coindesk.repository;

import com.example.coindesk.entity.CoinDeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinDeskRepository extends JpaRepository<CoinDeskEntity,Long> , JpaSpecificationExecutor<CoinDeskEntity> {

    CoinDeskEntity findCoinDeskEntityByCode(String code);
    boolean existsByCode(String code);
}
