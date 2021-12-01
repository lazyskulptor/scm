package com.hyeonjun.scm.domain.models;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hyeonjun.scm.domain.errors.FormSyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ProductTests {
    
    @Test
    public void testBuildProduct() {
        new Product();
        new Product(null, "test1", 1);
        new Product(1, "test2", 0);
    }
    
    @Test
    public void testBuildFailWithInvalidArgs() {
        Executable negativeId = () -> new Product(-1, "", 0);
        Executable negativePrice = () -> new Product(null, "", -1);
        Executable nullName = () -> new Product(null, null, 0);
        Executable emptyName = () -> new Product(null, "", 0);

        assertThrows(FormSyntaxException.class, negativeId);
        assertThrows(FormSyntaxException.class, negativePrice);
        assertThrows(FormSyntaxException.class, nullName);
        assertThrows(FormSyntaxException.class, emptyName);
    }
}
