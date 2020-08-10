package com.jinlong.uploadmodel;

import org.junit.jupiter.api.Test;

import java.util.Random;

class Solution {
    @Test
    void randomTest() {
        Random random = new Random();
        for (int i = 1; i <= 3; i++)
            System.out.println(random.nextInt(2));
    }
}
