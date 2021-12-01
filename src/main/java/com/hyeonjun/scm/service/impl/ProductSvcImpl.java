package com.hyeonjun.scm.service.impl;

import java.util.List;
import java.util.Optional;

import com.hyeonjun.scm.domain.errors.ErrorCode;
import com.hyeonjun.scm.domain.errors.NoEntityException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.repo.ProductRepo;
import com.hyeonjun.scm.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ProductSvcImpl implements ProductService {

    private ProductRepo repo;

    public ProductSvcImpl(@Autowired ProductRepo  repo) {
        this.repo = repo;
    }


    @Override
    public Integer saveProduct(Product product) {
        Product saved = repo.save(product);
        return saved.getId();
    }


    @Override
    public Product getProduct(Integer pk) {
        Optional<Product> persisted =  repo.findById(pk);
        if (persisted.isPresent()) {
            return persisted.get();
        }
        throw new NoEntityException(ErrorCode.ENTITY_NOT_FOUND, "Product(" + pk + ")가 존재하지 않습니다.");
    }


    @Override
    public Page<Product> getProductList(PageRequest pageRequest) {
        return repo.findAll(pageRequest);
    }


    @Override
    public void deleteProduct(Integer id) {
        this.getProduct(id);
        repo.deleteById(id);
    }
}
