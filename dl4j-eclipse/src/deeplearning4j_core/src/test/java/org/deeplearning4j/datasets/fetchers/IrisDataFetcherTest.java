package deeplearning4j_core.src.test.java.org.deeplearning4j.datasets.fetchers;
//package org.deeplearning4j.datasets.fetchers;

import org.junit.Test;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.fetchers.IrisDataFetcher;

/**
 * @author Adam Gibson
 */
public class IrisDataFetcherTest {

    @Test
    public void testIrisDataFetcher() throws Exception {
        IrisDataFetcher irisFetcher = new IrisDataFetcher();
        irisFetcher.fetch(10);

    }

}
