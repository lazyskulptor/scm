package com.hyeonjun.scm.dto;

import com.hyeonjun.scm.domain.models.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Integer id;

    private String name;

    private int price;

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }

    public Product toEntity() {
        return new Product(this.id, this.name, this.price);
    }
}
