package com.hyeonjun.scm.repo;

import com.hyeonjun.scm.domain.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
