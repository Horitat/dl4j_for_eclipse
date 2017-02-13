package deeplearning4j_core.src.test.java.org.deeplearning4j.nn.layers;

//import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
//import org.deeplearning4j.nn.api.Layer;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.layers.AutoEncoder;
import static org.junit.Assert.*;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.Layer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.AutoEncoder;

/**
 */

public class SeedTest {

    private DataSetIterator irisIter = new IrisDataSetIterator(50,50);
    private DataSet data = irisIter.next();


    @Test
    public void testAutoEncoderSeed() {
        AutoEncoder layerType = new AutoEncoder.Builder()
                .nIn(4)
                .nOut(3).corruptionLevel(0.0)
                .activation("sigmoid")
                .build();

        NeuralNetConfiguration conf = new NeuralNetConfiguration.Builder()
                .iterations(1)
                .layer(layerType)
                .seed(123)
                .build();

        int numParams = conf.getLayer().initializer().numParams(conf);
        INDArray params = Nd4j.create(1, numParams);
        Layer layer =  conf.getLayer().instantiate(conf, null, 0, params, true);
        layer.fit(data.getFeatureMatrix());

        layer.computeGradientAndScore();
        double score = layer.score();
        INDArray parameters = layer.params();
        layer.setParams(parameters);
        layer.computeGradientAndScore();

        double score2 = layer.score();
        assertEquals(parameters, layer.params());
        assertEquals(score, score2, 1e-4);
    }
}
