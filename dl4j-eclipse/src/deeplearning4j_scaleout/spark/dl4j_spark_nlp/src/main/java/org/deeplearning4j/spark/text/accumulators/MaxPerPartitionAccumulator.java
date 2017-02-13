package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.text.accumulators;
//package org.deeplearning4j.spark.text.accumulators;

import org.apache.spark.AccumulatorParam;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Counter;
//import org.deeplearning4j.berkeley.Counter;

/**
 * @author jeffreytang
 */
public class MaxPerPartitionAccumulator implements AccumulatorParam<Counter<Integer>> {

    @Override
    public Counter<Integer> addInPlace(Counter<Integer> c1, Counter<Integer> c2) {
        c1.incrementAll(c2);
        return c1;
    }
    @Override
    public Counter<Integer> zero(Counter<Integer> initialCounter) {
        return new Counter<>();
    }

    @Override
    public Counter<Integer> addAccumulator(Counter<Integer> c1, Counter<Integer> c2) {
        if (c1 == null) {
            return new Counter<>();
        }
        addInPlace(c1, c2);
        return c1;
    }
}
