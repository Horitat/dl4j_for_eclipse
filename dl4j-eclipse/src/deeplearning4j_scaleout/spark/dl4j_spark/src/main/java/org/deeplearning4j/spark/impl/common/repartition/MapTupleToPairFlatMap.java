package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.common.repartition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.function.PairFlatMapFunction;

import scala.Tuple2;

/**
 * This is a simple function used to convert a {@code JavaRDD<Tuple2<T,U>>} to a {@code JavaPairRDD<T,U>} via a
 * {JavaRDD.mappartitionsToPair()} call.
 *
 * @author Alex Black
 */
public class MapTupleToPairFlatMap<T,U> implements PairFlatMapFunction<Iterator<Tuple2<T,U>>,T,U> {

    @Override
    public Iterable<Tuple2<T, U>> call(Iterator<Tuple2<T, U>> tuple2Iterator) throws Exception {
        List<Tuple2<T,U>> list = new ArrayList<>();
        while(tuple2Iterator.hasNext()){
            list.add(tuple2Iterator.next());
        }
        return list;
    }
}
