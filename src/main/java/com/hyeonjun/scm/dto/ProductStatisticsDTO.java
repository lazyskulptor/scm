package com.hyeonjun.scm.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductStatisticsDTO {

    List<ProductStatDTO> products;

    public ProductStatisticsDTO(List<ProductStatDTO> products) {
        this.products = products;
    }
}
