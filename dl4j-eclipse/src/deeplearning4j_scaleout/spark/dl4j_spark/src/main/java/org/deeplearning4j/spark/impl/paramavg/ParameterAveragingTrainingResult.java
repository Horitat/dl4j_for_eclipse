package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.paramavg;

import java.util.Collection;

import lombok.Data;

//import org.deeplearning4j.api.storage.Persistable;
//import org.deeplearning4j.api.storage.StorageMetaData;
//import org.deeplearning4j.spark.api.TrainingResult;
//import org.deeplearning4j.spark.api.stats.SparkTrainingStats;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.Persistable;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StorageMetaData;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.TrainingResult;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.stats.SparkTrainingStats;

/**
 * The results (parameters, optional updaters) returned by a {@link ParameterAveragingTrainingWorker} to the
 * {@link ParameterAveragingTrainingMaster}
 *
 * @author Alex Black
 */
@Data
public class ParameterAveragingTrainingResult implements TrainingResult {

    private final INDArray parameters;
    private final INDArray updaterState;
    private final double score;
    private SparkTrainingStats sparkTrainingStats;

    private final Collection<StorageMetaData> listenerMetaData;
    private final Collection<Persistable> listenerStaticInfo;
    private final Collection<Persistable> listenerUpdates;


    public ParameterAveragingTrainingResult(INDArray parameters, INDArray updaterState, double score, Collection<StorageMetaData> listenerMetaData,
                                            Collection<Persistable> listenerStaticInfo, Collection<Persistable> listenerUpdates){
        this(parameters, updaterState, score, null, listenerMetaData, listenerStaticInfo, listenerUpdates);
    }

    public ParameterAveragingTrainingResult(INDArray parameters, INDArray updaterState, double score, SparkTrainingStats sparkTrainingStats,
                                            Collection<StorageMetaData> listenerMetaData, Collection<Persistable> listenerStaticInfo,
                                            Collection<Persistable> listenerUpdates){
        this.parameters = parameters;
        this.updaterState = updaterState;
        this.score = score;
        this.sparkTrainingStats = sparkTrainingStats;

        this.listenerMetaData = listenerMetaData;
        this.listenerStaticInfo = listenerStaticInfo;
        this.listenerUpdates = listenerUpdates;
    }

    @Override
    public void setStats(SparkTrainingStats sparkTrainingStats) {
        this.sparkTrainingStats = sparkTrainingStats;
    }
}
