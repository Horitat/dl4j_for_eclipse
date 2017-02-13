package deeplearning4j_nn.src.main.java.org.deeplearning4j.datasets.iterator;

import lombok.NonNull;

//import org.deeplearning4j.berkeley.Pair;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Pair;

/**
 * @author raver119@gmail.com
 */
public class INDArrayDataSetIterator extends AbstractDataSetIterator<INDArray> {

    public INDArrayDataSetIterator(@NonNull Iterable<Pair<INDArray, INDArray>> iterable, int batchSize) {
        super(iterable, batchSize);
    }
}
