package deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.test.java.org.deeplearning4j.parallelism.main;
//package org.deeplearning4j.parallelism.main;

//import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import java.io.IOException;

import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.main.java.org.deeplearning4j.parallelism.main.DataSetIteratorProviderFactory;

/**
 * Created by agibsonccc on 12/29/16.
 */
public class MnistDataSetIteratorProviderFactory implements DataSetIteratorProviderFactory {
    /**
     * Create an {@link DataSetIterator}
     *
     * @return
     */
    @Override
    public DataSetIterator create() {
        try {
            return new MnistDataSetIterator(100,1000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
