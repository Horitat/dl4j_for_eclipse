package deeplearning4j_ui_parent.deeplearning4j_ui.src.test.java.org.deeplearning4j.ui.weights;

//import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.junit.Ignore;
import org.junit.Test;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.OptimizationAlgorithm;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.DenseLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.OutputLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import deeplearning4j_ui_parent.deeplearning4j_ui.src.main.java.org.deeplearning4j.ui.weights.HistogramIterationListener;

/**
 * Created by Alex on 08/10/2016.
 */
@Ignore
public class TestHistogramListener {

    @Test
    public void testUI() throws Exception {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(1)
                .list()
                .layer(0, new DenseLayer.Builder().activation("tanh").nIn(4).nOut(4).build())
                .layer(1, new OutputLayer.Builder().lossFunction(LossFunctions.LossFunction.MCXENT).activation("softmax").nIn(4).nOut(3).build())
                .pretrain(false).backprop(true).build();


        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new HistogramIterationListener(1), new ScoreIterationListener(1));

        DataSetIterator iter = new IrisDataSetIterator(150,150);

        for( int i=0; i<100; i++ ){
            net.fit(iter);
            Thread.sleep(1000);
        }




        Thread.sleep(100000);
    }

}