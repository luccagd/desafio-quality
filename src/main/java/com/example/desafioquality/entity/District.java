package com.example.desafioquality.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class District {

    private Long id;

    private String name;

    private BigDecimal squareMeterPrice;
}
