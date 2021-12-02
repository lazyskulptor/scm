package com.hyeonjun.scm.dto;

import com.hyeonjun.scm.domain.models.Purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatDTO {
    private Integer id;

    private String name;

    private int sum;

    public ProductStatDTO(Purchase purchase) {
        this.id = purchase.getProduct().getId();
        this.name = purchase.getProduct().getName();
        this.sum = purchase.getPrice();
    }
}
