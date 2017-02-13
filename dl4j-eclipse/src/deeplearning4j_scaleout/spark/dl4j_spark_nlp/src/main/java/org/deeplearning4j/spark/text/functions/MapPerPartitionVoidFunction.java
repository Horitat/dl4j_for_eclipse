package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.text.functions;

import java.util.Iterator;

import org.apache.spark.api.java.function.VoidFunction;

/**
 * @author jeffreytang
 */
public class MapPerPartitionVoidFunction implements VoidFunction<Iterator<?>> {

    @Override
    public void call(Iterator<?> integerIterator) throws Exception {}
}

