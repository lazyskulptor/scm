package com.hyeonjun.scm.service;

import com.hyeonjun.scm.domain.models.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    Integer saveProduct(Product product);

    Product getProduct(Integer pk);

    Page<Product> getProductList(PageRequest pageRequest);

    void deleteProduct(Integer id);
    
}
