package com.hyeonjun.scm.domain.models;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hyeonjun.scm.domain.errors.FormSyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class UserTests {
    
    @Test
    public void testBuildUser() {
        new User();
        new User(null, "user1");
    }
    
    @Test
    public void testBuildFailWithInvalidArgs() {
        Executable negativeId = () -> new User(-1, "");
        Executable nullName = () -> new User(null, null);
        Executable emptyName = () -> new User(null, "");

        assertThrows(FormSyntaxException.class, negativeId);
        assertThrows(FormSyntaxException.class, nullName);
        assertThrows(FormSyntaxException.class, emptyName);
    }
}
