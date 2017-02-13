package deeplearning4j_core.src.test.java.org.deeplearning4j.datasets.iterator;

//import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import static org.junit.Assert.*;

import org.junit.Test;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.datasets.iterator.SamplingDataSetIterator;

/**
 * @author Adam Gibson
 */
public class SamplingTest {

    @Test
    public void testSample() throws Exception {
        DataSetIterator iter = new MnistDataSetIterator(10,10);
        //batch size and total
        DataSetIterator sampling = new SamplingDataSetIterator(iter.next(),10,10);
        assertEquals(sampling.next().numExamples(),10);
    }

}
