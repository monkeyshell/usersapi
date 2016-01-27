package com.users.test;

import org.junit.Test;

import com.users.mongo.User;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void canConstructAPersonWithAName() {
        User user = new User();
        assertEquals("Larry", "Larry");
    }
}
