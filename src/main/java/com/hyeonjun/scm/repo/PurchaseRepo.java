package com.hyeonjun.scm.repo;

import java.util.List;

import com.hyeonjun.scm.domain.models.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

    List<Purchase> findByUserId(Integer userId);

    List<Purchase> findByProductId(Integer id);
}
