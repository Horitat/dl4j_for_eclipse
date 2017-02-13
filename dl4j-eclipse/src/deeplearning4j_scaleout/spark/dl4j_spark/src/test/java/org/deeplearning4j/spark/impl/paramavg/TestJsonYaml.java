package deeplearning4j_scaleout.spark.dl4j_spark.src.test.java.org.deeplearning4j.spark.impl.paramavg;

import static org.junit.Assert.*;

import org.apache.spark.storage.StorageLevel;
//import org.deeplearning4j.spark.api.TrainingMaster;
import org.junit.Test;

import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.TrainingMaster;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.paramavg.ParameterAveragingTrainingMaster;

/**
 * Created by Alex on 20/09/2016.
 */
public class TestJsonYaml {

    @Test
    public void testJsonYaml(){
        TrainingMaster tm = new ParameterAveragingTrainingMaster.Builder(2)
                .batchSizePerWorker(32)
                .exportDirectory("hdfs://SomeDirectory/")
                .saveUpdater(false)
                .averagingFrequency(3)
                .storageLevel(StorageLevel.MEMORY_ONLY_SER_2())
                .storageLevelStreams(StorageLevel.DISK_ONLY())
                .build();

        String json = tm.toJson();
        String yaml = tm.toYaml();

        System.out.println(json);

        TrainingMaster fromJson = ParameterAveragingTrainingMaster.fromJson(json);
        TrainingMaster fromYaml = ParameterAveragingTrainingMaster.fromYaml(yaml);


        assertEquals(tm, fromJson);
        assertEquals(tm, fromYaml);

    }

}
