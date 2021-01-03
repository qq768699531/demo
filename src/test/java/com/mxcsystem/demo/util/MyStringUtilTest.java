package com.mxcsystem.demo.util;

import com.mxcsystem.demo.entity.base.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MyStringUtilTest {

    @BeforeEach
    void setUp() {
        System.out.println("begin...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("end...");
    }

    @Test
    void matchAt() {
        Set<User> result = MyStringUtil.getMentionUsers("@周扬(1189051464)今天@周扬(1189051464)没有吃@周扬(1189051464)@周！在想 | @劝劝 ");
    }
}