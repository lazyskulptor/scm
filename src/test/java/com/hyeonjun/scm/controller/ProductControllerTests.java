package com.hyeonjun.scm.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import com.hyeonjun.scm.domain.errors.NoEntityException;
import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.dto.ProductDTO;
import com.hyeonjun.scm.dto.SingleProductDTO;
import com.hyeonjun.scm.service.ProductService;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductService svc;
    
    @Test
    public void testPostProduct() {
        ProductDTO pdto = new ProductDTO(new Product(null, RandomString.make(10), new Random().nextInt(1000)));
        SingleProductDTO dto = new SingleProductDTO(pdto);

        String response1 = this.restTemplate.postForObject("http://localhost:" + port + "/products", dto, String.class);

        assertThat(response1).contains("id");
        assertThat(response1).contains("name");
        assertThat(response1).contains("price");
    }
    
    @Test
    public void testGetProductList() {
        Product product1 = new Product(RandomString.make(10), new Random().nextInt(1000));
        Product product2 = new Product(RandomString.make(10), new Random().nextInt(1000));
        svc.saveProduct(product1);
        svc.saveProduct(new Product(RandomString.make(10), new Random().nextInt(1000)));
        svc.saveProduct(new Product(RandomString.make(10), new Random().nextInt(1000)));
        svc.saveProduct(new Product(RandomString.make(10), new Random().nextInt(1000)));
        svc.saveProduct(new Product(RandomString.make(10), new Random().nextInt(1000)));
        svc.saveProduct(product2);

        String response1 = this.restTemplate.getForObject("http://localhost:" + port + "/products", String.class);

        assertThat(response1).contains("products");
        assertThat(response1).contains(product1.getName());
        assertThat(response1).contains(product2.getName());
    }
    
    @Test
    public void testGetProduct() {
        Product product1 = new Product(RandomString.make(10), new Random().nextInt(1000));
        svc.saveProduct(product1);

        String response1 = this.restTemplate.getForObject("http://localhost:" + port + "/products/" + product1.getId(), String.class);

        assertThat(response1).contains("product");
        assertThat(response1).contains(product1.getName());
    }
    
    @Test
    public void testDelProduct() {
        Product product = new Product(RandomString.make(10), new Random().nextInt(1000));
        svc.saveProduct(product);

        this.restTemplate.delete("http://localhost:" + port + "/products/" + product.getId());

        Executable getDeleted = () -> svc.getProduct(product.getId());
        assertThrows(NoEntityException.class, getDeleted);
    }
}
