package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.paramavg.aggregator;
//package org.deeplearning4j.spark.impl.paramavg.aggregator;

import java.io.Serializable;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

//import org.deeplearning4j.api.storage.Persistable;
//import org.deeplearning4j.api.storage.StorageMetaData;
//import org.deeplearning4j.spark.api.stats.SparkTrainingStats;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.Persistable;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StorageMetaData;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.stats.SparkTrainingStats;

/**
 * Simple helper tuple used to execute parameter averaging
 *
 * @author Alex Black
 */
@AllArgsConstructor @Data
public class ParameterAveragingAggregationTuple implements Serializable {
    private final INDArray parametersSum;
    private final INDArray updaterStateSum;
    private final double scoreSum;
    private final int aggregationsCount;
    private final SparkTrainingStats sparkTrainingStats;
    private final Collection<StorageMetaData> listenerMetaData;
    private final Collection<Persistable> listenerStaticInfo;
    private final Collection<Persistable> listenerUpdates;
}
