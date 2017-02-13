package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.models.embeddings.word2vec;

import java.util.Map;

import org.apache.spark.api.java.function.Function;
//import org.deeplearning4j.berkeley.Pair;
//import org.deeplearning4j.models.word2vec.VocabWord;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.VocabWord;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Pair;

/**
 * @author jeffreytang
 */
public class MapToPairFunction implements Function< Map.Entry<VocabWord, INDArray>, Pair<VocabWord, INDArray> > {

    @Override
    public Pair<VocabWord, INDArray> call(Map.Entry<VocabWord, INDArray> pair) {
        return new Pair<>(pair.getKey(), pair.getValue());
    }
}
