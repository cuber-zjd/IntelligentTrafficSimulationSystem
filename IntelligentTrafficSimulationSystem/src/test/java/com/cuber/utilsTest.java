package com.cuber;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author cuber
 * @create 2022/4/6 0:06
 */
public class utilsTest {
    @Test
    public void TestRandom(){
        Random random = new Random();
        double v = random.nextDouble()*100+10;
        System.out.println(v);
    }
}
