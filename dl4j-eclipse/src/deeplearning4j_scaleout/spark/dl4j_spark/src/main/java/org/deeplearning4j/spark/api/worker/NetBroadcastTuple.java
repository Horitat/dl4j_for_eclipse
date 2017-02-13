package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.worker;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

//import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.nd4j.linalg.api.ndarray.INDArray;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.MultiLayerConfiguration;

/**
 * A simple class for storing configurations, parameters and updaters in one class (so they can be broadcast together)
 *
 * @author Alex Black
 */
@Data
public class NetBroadcastTuple implements Serializable {

    private final MultiLayerConfiguration configuration;
    private final ComputationGraphConfiguration graphConfiguration;
    private final INDArray parameters;
    private final INDArray updaterState;
    private final AtomicInteger counter;

    public NetBroadcastTuple(MultiLayerConfiguration configuration, INDArray parameters, INDArray updaterState) {
        this(configuration, null, parameters, updaterState);
    }

    public NetBroadcastTuple(ComputationGraphConfiguration graphConfiguration, INDArray parameters, INDArray updaterState) {
        this(null, graphConfiguration, parameters, updaterState);

    }

    public NetBroadcastTuple(MultiLayerConfiguration configuration, ComputationGraphConfiguration graphConfiguration,
                             INDArray parameters, INDArray updaterState) {
        this(configuration, graphConfiguration, parameters, updaterState, new AtomicInteger(0));
    }

    public NetBroadcastTuple(MultiLayerConfiguration configuration, ComputationGraphConfiguration graphConfiguration,
                             INDArray parameters, INDArray updaterState, AtomicInteger counter) {
        this.configuration = configuration;
        this.graphConfiguration = graphConfiguration;
        this.parameters = parameters;
        this.updaterState = updaterState;
        this.counter = counter;
    }
}
