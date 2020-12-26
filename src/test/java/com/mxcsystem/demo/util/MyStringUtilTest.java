package com.mxcsystem.demo.util;

import com.mxcsystem.demo.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        Set<User> result = MyStringUtil.matchAt("@周扬(1189051464)今天@周扬(1189051464)没有吃@周扬(1189051464)@周！在想 | @劝劝 ");
    }
}