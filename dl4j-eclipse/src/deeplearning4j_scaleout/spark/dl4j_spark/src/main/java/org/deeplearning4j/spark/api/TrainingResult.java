package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api;

import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.stats.SparkTrainingStats;

//import org.deeplearning4j.spark.api.stats.SparkTrainingStats;

/**
 * TrainingResult: a class used by {@link TrainingMaster} implementations
 *
 * Each TrainingMaster will have its own type of training result.
 *
 * @author Alex Black
 */
public interface TrainingResult {

    /**
     *
     * @param sparkTrainingStats
     */
    void setStats(SparkTrainingStats sparkTrainingStats);
}
