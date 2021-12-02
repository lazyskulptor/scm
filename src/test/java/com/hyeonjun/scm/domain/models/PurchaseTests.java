package com.hyeonjun.scm.domain.models;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import com.hyeonjun.scm.domain.errors.FormSyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.function.Executable;

@TestInstance(Lifecycle.PER_CLASS)
public class PurchaseTests {
    User client;
    Product product;

    @BeforeAll
    public void prepareObjs() {
        client = new User(1, "client1");
        product = new Product(1, "p1", 100);
    }
    
    @Test
    public void testBuildPurchase() {
        new Purchase(); // this is protected
        new Purchase(new Random().nextInt(10000), client, product, 0);
    }
    
    @Test
    public void testBuildFailWithInvalidArgs() {
        Executable nullId = () -> new Purchase(null, client, product, 0);
        Executable negativeId = () -> new Purchase(-1, client, product, 0);
        Executable negativePrice = () -> new Purchase(null, client, product, -1);
        Executable nullClient = () -> new Purchase(null, null, product, 0);
        Executable clientWithoutId = () -> new Purchase(null, new User(), product, 0);
        Executable nullProdcut = () -> new Purchase(null, client, null, 0);
        Executable productWithoutId = () -> new Purchase(null, client, new Product(), 0);

        assertThrows(FormSyntaxException.class, nullId);
        assertThrows(FormSyntaxException.class, negativeId);
        assertThrows(FormSyntaxException.class, negativePrice);
        assertThrows(FormSyntaxException.class, nullClient);
        assertThrows(FormSyntaxException.class, clientWithoutId);
        assertThrows(FormSyntaxException.class, nullProdcut);
        assertThrows(FormSyntaxException.class, productWithoutId);
    }
}
