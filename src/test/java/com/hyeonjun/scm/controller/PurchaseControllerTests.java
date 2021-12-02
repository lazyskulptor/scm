package com.hyeonjun.scm.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import com.hyeonjun.scm.domain.models.Product;
import com.hyeonjun.scm.dto.ProductDTO;
import com.hyeonjun.scm.dto.PurchaseDTO;
import com.hyeonjun.scm.dto.SingleProductDTO;
import com.hyeonjun.scm.service.ProductService;
import com.hyeonjun.scm.service.PurchaseService;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PurchaseControllerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PurchaseService svc;
    
    @Test
    public void testPostPurchase() {
        PurchaseDTO pdto = new PurchaseDTO();
    }
    
    @Test
    public void testStatistics() {
        String response1 = this.restTemplate.getForObject("http://localhost:" + port + "/purchase/product", String.class);

        System.out.println();
        System.out.println(response1);
    }
}
