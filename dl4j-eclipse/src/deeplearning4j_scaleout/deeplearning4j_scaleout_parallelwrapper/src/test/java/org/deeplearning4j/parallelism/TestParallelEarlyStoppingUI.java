package deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.test.java.org.deeplearning4j.parallelism;

//import org.deeplearning4j.api.storage.StatsStorage;
//import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
//import org.deeplearning4j.earlystopping.EarlyStoppingConfiguration;
//import org.deeplearning4j.earlystopping.EarlyStoppingModelSaver;
//import org.deeplearning4j.earlystopping.EarlyStoppingResult;
//import org.deeplearning4j.earlystopping.saver.InMemoryModelSaver;
//import org.deeplearning4j.earlystopping.scorecalc.DataSetLossCalculator;
//import org.deeplearning4j.earlystopping.termination.MaxEpochsTerminationCondition;
//import org.deeplearning4j.earlystopping.trainer.IEarlyStoppingTrainer;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.ui.api.UIServer;
//import org.deeplearning4j.ui.stats.StatsListener;
//import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorage;
import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.EarlyStoppingConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.EarlyStoppingModelSaver;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.EarlyStoppingResult;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.saver.InMemoryModelSaver;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.scorecalc.DataSetLossCalculator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.termination.MaxEpochsTerminationCondition;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.trainer.IEarlyStoppingTrainer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.OptimizationAlgorithm;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.Updater;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.DenseLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.OutputLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.weights.WeightInit;
import deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.main.java.org.deeplearning4j.parallelism.EarlyStoppingParallelTrainer;
import deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.api.UIServer;
import deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.stats.StatsListener;
import deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.storage.InMemoryStatsStorage;


public class TestParallelEarlyStoppingUI {

    @Test @Ignore   //To be run manually
    public void testParallelStatsListenerCompatibility() throws Exception {
        UIServer uiServer = UIServer.getInstance();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(1)
            .updater(Updater.SGD)
            .weightInit(WeightInit.XAVIER)
            .list()
            .layer(0, new DenseLayer.Builder().nIn(4).nOut(3).build())
            .layer(1,new OutputLayer.Builder().nIn(3).nOut(3).lossFunction(LossFunctions.LossFunction.MCXENT).build())
            .pretrain(false).backprop(true)
            .build();
        MultiLayerNetwork net = new MultiLayerNetwork(conf);

        // it's important that the UI can report results from parallel training
        // there's potential for StatsListener to fail if certain properties aren't set in the model
        StatsStorage statsStorage = new InMemoryStatsStorage();
        net.setListeners(new StatsListener(statsStorage));
        uiServer.attach(statsStorage);

        DataSetIterator irisIter = new IrisDataSetIterator(50,500);
        EarlyStoppingModelSaver<MultiLayerNetwork> saver = new InMemoryModelSaver<>();
        EarlyStoppingConfiguration<MultiLayerNetwork> esConf = new EarlyStoppingConfiguration.Builder<MultiLayerNetwork>()
            .epochTerminationConditions(new MaxEpochsTerminationCondition(500))
            .scoreCalculator(new DataSetLossCalculator(irisIter,true))
            .evaluateEveryNEpochs(2)
            .modelSaver(saver)
            .build();

        IEarlyStoppingTrainer<MultiLayerNetwork> trainer = new EarlyStoppingParallelTrainer<>(esConf,net,irisIter,null,3,6,2);

        EarlyStoppingResult<MultiLayerNetwork> result = trainer.fit();
        System.out.println(result);

        assertEquals(EarlyStoppingResult.TerminationReason.EpochTerminationCondition, result.getTerminationReason());
    }
}
