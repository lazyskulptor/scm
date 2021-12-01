package com.hyeonjun.scm.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListProductDTO {

    private List<ProductDTO> products;

    public ListProductDTO(ProductDTO product) {
        this.products = new ArrayList<>();
        this.products.add(product);
    }

    public ListProductDTO(List<ProductDTO> product) {
        this.products = new ArrayList<>();
        this.products.addAll(product);
    }
}
