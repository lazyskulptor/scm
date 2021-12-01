package com.hyeonjun.scm.service.impl;

import java.util.Optional;

import com.hyeonjun.scm.domain.errors.ErrorCode;
import com.hyeonjun.scm.domain.errors.NoEntityException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.domain.models.User;
import com.hyeonjun.scm.repo.ProductRepo;
import com.hyeonjun.scm.repo.PurchaseRepo;
import com.hyeonjun.scm.service.ProductService;
import com.hyeonjun.scm.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSvcImpl implements PurchaseService {

    private PurchaseRepo repo;

    public PurchaseSvcImpl(@Autowired PurchaseRepo  repo) {
        this.repo = repo;
    }

    @Override
    public void registerPurchase(Purchase purchase) {
        repo.save(purchase);
        
    }

    @Override
    public Optional<Integer> getSumOfUsers(User user) {
        return repo.findByUserId(user.getId()).stream()
            .map(p -> p.getPrice)
            .reduce((left, right) -> left+right);
    }

    @Override
    public Optional<Integer> getSumOfProduct(Product product) {
        return repo.findByProductId(product.getId()).stream()
            .map(p -> p.getPrice())
            .reduce((left, right) -> left+right);
    }


}
