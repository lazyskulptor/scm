package com.hyeonjun.scm.service;

import java.util.Optional;

import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.domain.models.User;

public interface PurchaseService {

    Integer registerPurchase(Purchase purchase);

    Optional<Integer> getSumOfUsers(User user);
    
    Optional<Integer> getSumOfProduct(Product product);
}
