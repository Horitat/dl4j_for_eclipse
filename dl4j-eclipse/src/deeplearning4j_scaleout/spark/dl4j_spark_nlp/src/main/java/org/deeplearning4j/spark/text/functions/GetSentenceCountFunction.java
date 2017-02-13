package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.text.functions;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.spark.api.java.function.Function;
//import org.deeplearning4j.berkeley.Pair;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Pair;

/**
 * @author jeffreytang
 */
public class GetSentenceCountFunction implements Function<Pair<List<String>, AtomicLong>, AtomicLong> {

    @Override
    public AtomicLong call(Pair<List<String>, AtomicLong> pair) throws Exception {
        return pair.getSecond();
    }
}
