package com.hyeonjun.scm.dto;

import com.hyeonjun.scm.domain.models.Purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDTO {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private int price;

    public PurchaseDTO(Purchase entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.productId = entity.getProduct().getId();
        this.price = entity.getPrice();
    }
}
