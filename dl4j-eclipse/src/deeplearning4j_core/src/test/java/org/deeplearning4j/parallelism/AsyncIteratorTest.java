package deeplearning4j_core.src.test.java.org.deeplearning4j.parallelism;
//package org.deeplearning4j.parallelism;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import deeplearning4j_core.src.main.java.org.deeplearning4j.parallelism.AsyncIterator;

/**
 * @author raver119@gmail.com
 */
public class AsyncIteratorTest {

    @Test
    public void hasNext() throws Exception {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int x = 0; x < 100000; x++ ) {
            integers.add(x);
        }

        AsyncIterator<Integer> iterator = new AsyncIterator<>(integers.iterator(), 512);
        int cnt = 0;
        Integer val= null;
        while (iterator.hasNext()) {
             val = iterator.next();
            assertEquals(cnt, val.intValue());
            cnt++;
        }

        System.out.println("Last val: " + val);

        assertEquals(integers.size(), cnt);
    }

}