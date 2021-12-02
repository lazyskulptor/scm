package com.hyeonjun.scm.service;

import java.util.List;

import com.hyeonjun.scm.domain.models.Purchase;

public interface PurchaseService {

    Integer registerPurchase(Purchase purchase);

    List<Purchase> getPurchaseList();
}
