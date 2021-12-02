package com.hyeonjun.scm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.domain.models.Purchase;
import com.hyeonjun.scm.domain.models.PurchaseId;
import com.hyeonjun.scm.domain.models.User;
import com.hyeonjun.scm.repo.ProductRepo;
import com.hyeonjun.scm.repo.PurchaseRepo;
import com.hyeonjun.scm.repo.UserRepo;
import com.hyeonjun.scm.service.impl.PurchaseSvcImpl;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class PurchaseServiceTests {

    PurchaseService svc;
    
    @Autowired
    PurchaseRepo repo;

    @Autowired
    ProductRepo prRepo;

    @Autowired
    UserRepo userRepo;

    User client;
    Product product;

    @BeforeAll
    public void prepareTest() {
        svc = new PurchaseSvcImpl(repo);
    }

    @BeforeEach
    public void prepareFixtures() {
        client = new User(null, RandomString.make(10));
        userRepo.save(client);
        product = new Product(null, "p1", new Random().nextInt(10000000));
        prRepo.save(product);
    }
    
    @Test
    public void testRegisterProduct() {
        Purchase purchase = new Purchase(new Random().nextInt(100000), client, product, new Random().nextInt(100000));

        Integer pk = svc.registerPurchase(purchase);

        Purchase saved = repo.getById(new PurchaseId(pk, client.getId(), product.getId()));
        assertEquals(purchase.getProduct(), saved.getProduct());
        assertEquals(purchase.getId(), saved.getId());
    }
    
    @Test
    public void testGetPurchaseSumOfUser() {
        Purchase purchase0 = new Purchase(new Random().nextInt(100000), client, product, product.getPrice());
        repo.save(purchase0);
        Product product1 = prRepo.save(new Product(new Random().nextInt(10000000), "p1", new Random().nextInt(10000000)));
        User u1 = userRepo.save(new User(null, "u1"));
        Purchase purchase1 = new Purchase(new Random().nextInt(100000), u1, product1, product1.getPrice());
        repo.save(purchase1);
        Product product2 = prRepo.save(new Product(new Random().nextInt(10000000), "p2", new Random().nextInt(10000000)));
        User u2 = userRepo.save(new User(null, "u2"));
        Purchase purchase2 = new Purchase(new Random().nextInt(100000), u2, product2, product2.getPrice());
        repo.save(purchase2);
        Product product3 = prRepo.save(new Product(new Random().nextInt(10000000), "p3", new Random().nextInt(10000000)));
        User u3 = userRepo.save(new User(null, "u3"));
        Purchase purchase3 = new Purchase(new Random().nextInt(100000), u3, product3, product3.getPrice());
        repo.save(purchase3);

        List<Purchase> list = svc.getPurchaseList();

        assertTrue(list.size() > 2);
    }
}
