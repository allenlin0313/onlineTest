package com.example.coindesk.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coindesk")
@NoArgsConstructor
@AllArgsConstructor
public class CoinDeskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String chartName;
    @NonNull
    private String code;
//    private String symbol;
//    private String rate;
//    private String description;
//    private String rate_float;
//    private String updated;
//    private String updatedISO;
//    private String updateduk;
    @NonNull
    private String codeNameZh;

}
