package deeplearning4j_keras.src.main.java.org.deeplearning4j.keras;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
//import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
//import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
//import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_modelimport.src.main.java.org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import deeplearning4j_modelimport.src.main.java.org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import deeplearning4j_modelimport.src.main.java.org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

/**
 * Reads the neural network model from Keras, specified by the parameters. Reuses the -modelimport code.
 *
 * @author pkoperek@gmail.com
 */
@Slf4j
public class NeuralNetworkReader {

    public MultiLayerNetwork readNeuralNetwork(EntryPointFitParameters entryPointFitParameters)
            throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {

        MultiLayerNetwork multiLayerNetwork;
        if (KerasModelType.SEQUENTIAL.equals(entryPointFitParameters.getType())) {
            multiLayerNetwork = KerasModelImport.importKerasSequentialModelAndWeights(entryPointFitParameters.getModelFilePath());
            multiLayerNetwork.init();
        } else {
            throw new RuntimeException("Model type unsupported! (" + entryPointFitParameters.getType() + ")");
        }

        return multiLayerNetwork;
    }
}
