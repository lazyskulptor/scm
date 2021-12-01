package com.hyeonjun.scm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.hyeonjun.scm.domain.errors.NoEntityException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.repo.ProductRepo;
import com.hyeonjun.scm.service.impl.ProductSvcImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ProductServiceTests {

    ProductService svc;
    
    @Autowired
    ProductRepo repo;

    @BeforeAll
    public void prepareTest() {
        svc = new ProductSvcImpl(repo);
    }
    
    @Test
    public void testRegisterProduct() {
        Product product = new Product("client1", 100);

        Integer pk = svc.saveProduct(product);

        Product saved = repo.getById(pk);
        assertEquals(product.getName(), saved.getName());
        assertNotNull(saved.getId());
    }

    @Test
    public void testReadSingleProduct() {
        Product product = new Product("client1", 100);

        Integer pk = svc.saveProduct(product);
        Product persisted = svc.getProduct(pk);

        assertEquals(product.getName(), persisted.getName());
        assertNotNull(persisted.getId());
    }

    @Test
    public void testReadProductList() {
        svc.saveProduct(new Product("client1", 100));
        svc.saveProduct(new Product("client2", 100));
        svc.saveProduct(new Product("client3", 100));
        svc.saveProduct(new Product("client4", 100));
        svc.saveProduct(new Product("client5", 100));

        Page<Product> page = svc.getProductList(PageRequest.of(0, 20));
        assertTrue(page.getSize() > 5);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("client1", 100);
        svc.saveProduct(product);
        product.setPrice(1000);
        svc.saveProduct(product);

        Product saved = svc.getProduct(product.getId());

        assertEquals(1000, saved.getPrice());
        assertEquals(1000, product.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("client1", 100);
        svc.saveProduct(product);
        Product saved = svc.getProduct(product.getId());
        svc.deleteProduct(product.getId());
        Executable getDeleted = () -> svc.getProduct(product.getId());

        assertNotNull(saved.getId());
        assertThrows(NoEntityException.class, getDeleted);
    }
}
