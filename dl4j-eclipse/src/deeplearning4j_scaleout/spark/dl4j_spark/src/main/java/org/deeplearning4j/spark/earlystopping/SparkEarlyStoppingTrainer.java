/*
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.earlystopping;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
//import org.deeplearning4j.earlystopping.EarlyStoppingConfiguration;
//import org.deeplearning4j.earlystopping.listener.EarlyStoppingListener;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.spark.api.TrainingMaster;
//import org.deeplearning4j.spark.impl.multilayer.SparkDl4jMultiLayer;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.MultiDataSet;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.EarlyStoppingConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.listener.EarlyStoppingListener;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.TrainingMaster;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.multilayer.SparkDl4jMultiLayer;

/**
 * Class for conducting early stopping training via Spark on a {@link MultiLayerNetwork}
 *
 * @author Alex Black
 */
public class SparkEarlyStoppingTrainer extends BaseSparkEarlyStoppingTrainer<MultiLayerNetwork> {

    private SparkDl4jMultiLayer sparkNet;

    public SparkEarlyStoppingTrainer(SparkContext sc, TrainingMaster trainingMaster, EarlyStoppingConfiguration<MultiLayerNetwork> esConfig,
                                     MultiLayerNetwork net, JavaRDD<DataSet> train) {
        this(new JavaSparkContext(sc), trainingMaster, esConfig, net, train, null);
    }

    public SparkEarlyStoppingTrainer(JavaSparkContext sc, TrainingMaster trainingMaster, EarlyStoppingConfiguration<MultiLayerNetwork> esConfig,
                                     MultiLayerNetwork net, JavaRDD<DataSet> train) {
        this(sc, trainingMaster, esConfig, net, train, null);
    }

    public SparkEarlyStoppingTrainer(SparkContext sc, TrainingMaster trainingMaster, EarlyStoppingConfiguration<MultiLayerNetwork> esConfig,
                                     MultiLayerNetwork net, JavaRDD<DataSet> train, EarlyStoppingListener<MultiLayerNetwork> listener) {
        this(new JavaSparkContext(sc), trainingMaster, esConfig, net, train, listener);
    }

    public SparkEarlyStoppingTrainer(JavaSparkContext sc, TrainingMaster trainingMaster, EarlyStoppingConfiguration<MultiLayerNetwork> esConfig, MultiLayerNetwork net,
                                     JavaRDD<DataSet> train, EarlyStoppingListener<MultiLayerNetwork> listener) {
        super(sc, esConfig, net, train, null, listener);
        sparkNet = new SparkDl4jMultiLayer(sc, net, trainingMaster);
    }


    @Override
    protected void fit(JavaRDD<DataSet> data) {
        sparkNet.fit(data);
    }

    @Override
    protected void fitMulti(JavaRDD<MultiDataSet> data) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    protected double getScore() {
        return sparkNet.getScore();
    }
}
