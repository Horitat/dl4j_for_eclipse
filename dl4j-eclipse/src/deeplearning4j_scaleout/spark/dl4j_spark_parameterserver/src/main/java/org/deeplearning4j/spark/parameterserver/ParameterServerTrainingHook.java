package deeplearning4j_scaleout.spark.dl4j_spark_parameterserver.src.main.java.org.deeplearning4j.spark.parameterserver;

//import org.deeplearning4j.nn.api.Model;
//import org.deeplearning4j.spark.api.TrainingHook;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.MultiDataSet;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.Model;
import deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.api.TrainingHook;

/**
 * Training hook for the
 * parameter server
 * @author Adam Gibson
 */
public class ParameterServerTrainingHook implements TrainingHook {
    /**
     * A hook method for pre update.
     *
     * @param minibatch the inibatch
     *                  that was used for the update
     * @param model     themodel that was update
     */
    @Override
    public void preUpdate(DataSet minibatch, Model model) {
          //pull
    }

    /**
     * A hook method for post update
     *
     * @param minibatch the minibatch
     *                  that was usd for the update
     * @param model     the model that was updated
     */
    @Override
    public void postUpdate(DataSet minibatch, Model model) {
        //push
    }

    /**
     * A hook method for pre update.
     *
     * @param minibatch the inibatch
     *                  that was used for the update
     * @param model     themodel that was update
     */
    @Override
    public void preUpdate(MultiDataSet minibatch, Model model) {
          //pull
    }

    /**
     * A hook method for post update
     *
     * @param minibatch the minibatch
     *                  that was usd for the update
     * @param model     the model that was updated
     */
    @Override
    public void postUpdate(MultiDataSet minibatch, Model model) {
       //push
    }
}
