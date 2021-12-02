package com.hyeonjun.scm.service.impl;

import java.util.List;

import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.repo.PurchaseRepo;
import com.hyeonjun.scm.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSvcImpl implements PurchaseService {

    private PurchaseRepo repo;

    public PurchaseSvcImpl(@Autowired PurchaseRepo  repo) {
        this.repo = repo;
    }

    @Override
    public Integer registerPurchase(Purchase purchase) {
        return repo.save(purchase).getId();
    }

    @Override
    public List<Purchase> getPurchaseList() {
        return repo.findAll();
    }


    // @Override
    // public Optional<Integer> getSumOfUsers(User user) {
    //     List<Purchase> purs = repo.findByUserId(user.getId());
    //     return repo.findByUserId(user.getId()).stream()
    //         .map(p -> p.getPrice())
    //         .reduce((left, right) -> left+right);
    // }

    // @Override
    // public Optional<Integer> getSumOfProduct(Product product) {
    //     return repo.findByProductId(product.getId()).stream()
    //         .map(p -> p.getPrice())
    //         .reduce((left, right) -> left+right);
    // }


}
