package deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.layers.feedforward.dense;
//package org.deeplearning4j.nn.layers.feedforward.dense;

//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.layers.BaseLayer;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.layers.BaseLayer;

/**
 * @author Adam Gibson
 */
public class DenseLayer extends BaseLayer<deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.DenseLayer> {
    public DenseLayer(NeuralNetConfiguration conf) {
        super(conf);
    }

    public DenseLayer(NeuralNetConfiguration conf, INDArray input) {
        super(conf, input);
    }

    @Override
    public void fit(INDArray input) {}

    @Override
    public boolean isPretrainLayer() {
        return false;
    }
}
