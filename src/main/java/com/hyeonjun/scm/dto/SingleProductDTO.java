package com.hyeonjun.scm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SingleProductDTO {

    private ProductDTO product;

    public SingleProductDTO(ProductDTO product) {
        this.product = product;
    }
}
