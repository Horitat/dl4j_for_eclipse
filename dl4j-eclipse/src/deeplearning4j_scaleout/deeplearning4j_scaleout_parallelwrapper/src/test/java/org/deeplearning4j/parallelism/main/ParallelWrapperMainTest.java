package deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.test.java.org.deeplearning4j.parallelism.main;

import java.io.File;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

//import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
//import org.deeplearning4j.nn.conf.layers.setup.ConvolutionLayerSetup;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.parallelism.main.ParallelWrapperMain;
//import org.deeplearning4j.ui.api.UIServer;
//import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
//import org.deeplearning4j.util.ModelSerializer;
import org.junit.Test;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import deeplearning4j_core.src.main.java.org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.OptimizationAlgorithm;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.Updater;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.DenseLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.OutputLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.setup.ConvolutionLayerSetup;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.weights.WeightInit;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.util.ModelSerializer;
import deeplearning4j_scaleout.deeplearning4j_scaleout_parallelwrapper.src.main.java.org.deeplearning4j.parallelism.main.ParallelWrapperMain;
import deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.api.UIServer;
import deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.storage.InMemoryStatsStorage;

/**
 * Created by agibsonccc on 12/29/16.
 */
@Slf4j
public class ParallelWrapperMainTest {

    @Test
    public void runParallelWrapperMain() throws Exception {

        int nChannels = 1;
        int outputNum = 10;

        // for GPU you usually want to have higher batchSize
        int batchSize = 128;
        int nEpochs = 10;
        int iterations = 1;
        int seed = 123;
        int uiPort = new Random().nextInt(1000) + 9000;
        System.setProperty("org.deeplearning4j.ui.port",String.valueOf(uiPort));
        log.info("Load data....");
        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize,true,12345);
        DataSetIterator mnistTest = new MnistDataSetIterator(batchSize,false,12345);

        log.info("Build model....");
        MultiLayerConfiguration.Builder builder = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iterations)
                .regularization(true).l2(0.0005)
                .learningRate(0.01)//.biasLearningRate(0.02)
                //.learningRateDecayPolicy(LearningRatePolicy.Inverse).lrPolicyDecayRate(0.001).lrPolicyPower(0.75)
                .weightInit(WeightInit.XAVIER)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(Updater.NESTEROVS).momentum(0.9)
                .list()
                .layer(0, new ConvolutionLayer.Builder(5, 5)
                        //nIn and nOut specify depth. nIn here is the nChannels and nOut is the number of filters to be applied
                        .nIn(nChannels)
                        .stride(1, 1)
                        .nOut(20)
                        .activation("identity")
                        .build())
                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(2, new ConvolutionLayer.Builder(5, 5)
                        //Note that nIn needed be specified in later layers
                        .stride(1, 1)
                        .nOut(50)
                        .activation("identity")
                        .build())
                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(4, new DenseLayer.Builder().activation("relu")
                        .nOut(500).build())
                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation("softmax")
                        .build())
                .backprop(true).pretrain(false);
        // The builder needs the dimensions of the image along with the number of channels. these are 28x28 images in one channel
        new ConvolutionLayerSetup(builder,28,28,1);
        UIServer uiServer = UIServer.getInstance();
        uiServer.attach(new InMemoryStatsStorage());
        uiServer.enableRemoteListener();
        MultiLayerConfiguration conf = builder.build();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        File tempModel = new File("tmpmodel.zip");
        tempModel.deleteOnExit();
        ModelSerializer.writeModel(model,tempModel,false);
        File tmp = new  File("tmpmodel.bin");
        tmp.deleteOnExit();
        ParallelWrapperMain parallelWrapperMain = new ParallelWrapperMain();
        parallelWrapperMain.runMain(new String[]{
                "--modelPath",tempModel.getAbsolutePath(),
                "--dataSetIteratorFactoryClazz",MnistDataSetIteratorProviderFactory.class.getName(),
                "--modelOutputPath",tmp.getAbsolutePath(),
                "--uiUrl","localhost:" + uiPort
        });


        Thread.sleep(30000);
    }

}