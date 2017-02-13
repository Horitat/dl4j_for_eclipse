package deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.updater;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.Model;
//import org.deeplearning4j.nn.api.Model;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.layers.Layer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

/**
 *
 *
 * @author Adam Gibson
 */
public class UpdaterCreator {

    private UpdaterCreator() {
    }

    public static deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.Updater getUpdater(Model layer) {
    	if( layer instanceof MultiLayerNetwork ){
    		return new MultiLayerUpdater((MultiLayerNetwork)layer);
    	} else {
            return new LayerUpdater();
    	}
    }

}
