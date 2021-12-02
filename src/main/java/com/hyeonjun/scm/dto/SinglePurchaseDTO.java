package com.hyeonjun.scm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SinglePurchaseDTO {

    private PurchaseDTO purchase;

    public SinglePurchaseDTO(PurchaseDTO purchase) {
        this.purchase = purchase;
    }
}
