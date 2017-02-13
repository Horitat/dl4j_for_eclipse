package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A simple configuration object (common settings for workers)
 *
 * @author Alex Black
 */
@AllArgsConstructor @Data
public class WorkerConfiguration implements Serializable {

    protected final boolean isGraphNetwork;
    protected final int dataSetObjectSizeExamples;  //Number of examples in each DataSet object
    protected final int batchSizePerWorker;
    protected final int maxBatchesPerWorker;
    protected final int prefetchNumBatches;
    protected final boolean collectTrainingStats;

}
