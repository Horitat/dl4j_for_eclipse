package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.text.functions;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.spark.api.java.function.Function2;

/**
 * @author jeffreytang
 */
public class ReduceSentenceCount implements Function2<AtomicLong, AtomicLong, AtomicLong> {
    public AtomicLong call(AtomicLong a, AtomicLong b) {
        return new AtomicLong(a.get() + b.get());
    }
}
