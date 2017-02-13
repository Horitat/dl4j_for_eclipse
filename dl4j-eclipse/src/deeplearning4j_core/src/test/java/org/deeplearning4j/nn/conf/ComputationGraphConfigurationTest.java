package deeplearning4j_core.src.test.java.org.deeplearning4j.nn.conf;
//package org.deeplearning4j.nn.conf;


import static org.junit.Assert.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//import org.deeplearning4j.nn.conf.misc.TestGraphVertex;
//import org.deeplearning4j.nn.conf.preprocessor.CnnToFeedForwardPreProcessor;
//import org.deeplearning4j.nn.conf.preprocessor.FeedForwardToCnnPreProcessor;
//import org.deeplearning4j.nn.graph.ComputationGraph;
//import org.deeplearning4j.nn.weights.WeightInit;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import deeplearning4j_core.src.test.java.org.deeplearning4j.nn.conf.misc.TestGraphVertex;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.OptimizationAlgorithm;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.Updater;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.distribution.NormalDistribution;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.graph.ElementWiseVertex;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.graph.GraphVertex;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.graph.MergeVertex;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.graph.SubsetVertex;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.inputs.InputType;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.inputs.InvalidInputTypeException;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.distribution.NormalDistribution;
//import org.deeplearning4j.nn.conf.graph.ElementWiseVertex;
//import org.deeplearning4j.nn.conf.graph.GraphVertex;
//import org.deeplearning4j.nn.conf.graph.MergeVertex;
//import org.deeplearning4j.nn.conf.graph.SubsetVertex;
//import org.deeplearning4j.nn.conf.inputs.InputType;
//import org.deeplearning4j.nn.conf.inputs.InvalidInputTypeException;
//import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
//import org.deeplearning4j.nn.conf.layers.DenseLayer;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.DenseLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.OutputLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.preprocessor.CnnToFeedForwardPreProcessor;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.preprocessor.FeedForwardToCnnPreProcessor;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.graph.ComputationGraph;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.weights.WeightInit;

public class ComputationGraphConfigurationTest {

    @Test
    public void testJSONBasic(){
        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .weightInit(WeightInit.DISTRIBUTION).dist(new NormalDistribution(0,1))
                .updater(Updater.NONE).learningRate(1.0)
                .graphBuilder()
                .addInputs("input")
                .addLayer("firstLayer", new DenseLayer.Builder().nIn(4).nOut(5).activation("tanh").build(), "input")
                .addLayer("outputLayer", new OutputLayer.Builder().lossFunction(LossFunctions.LossFunction.MCXENT)
                        .activation("softmax").nIn(5).nOut(3).build(), "firstLayer")
                .setOutputs("outputLayer")
                .pretrain(false).backprop(true)
                .build();

        String json = conf.toJson();
        ComputationGraphConfiguration conf2 = ComputationGraphConfiguration.fromJson(json);

        assertEquals(json,conf2.toJson());
        assertEquals(conf, conf2);
    }

    @Test
    public void testJSONBasic2(){
        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .graphBuilder()
                .addInputs("input")
                .addLayer("cnn1", new ConvolutionLayer.Builder(2, 2).stride(2, 2).nIn(1).nOut(5).build(), "input")
                .addLayer("cnn2", new ConvolutionLayer.Builder(2, 2).stride(2, 2).nIn(1).nOut(5).build(), "input")
                .addLayer("max1", new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX).kernelSize(2, 2).build(), "cnn1", "cnn2")
                .addLayer("dnn1", new DenseLayer.Builder().nOut(7).build(), "max1")
                .addLayer("max2", new SubsamplingLayer.Builder().build(), "max1")
                .addLayer("output", new OutputLayer.Builder().nIn(7).nOut(10).build(), "dnn1", "max2")
                .setOutputs("output")
                .inputPreProcessor("cnn1", new FeedForwardToCnnPreProcessor(32, 32, 3))
                .inputPreProcessor("cnn2", new FeedForwardToCnnPreProcessor(32, 32, 3))
                .inputPreProcessor("dnn1", new CnnToFeedForwardPreProcessor(8, 8, 5))
                .pretrain(false).backprop(true)
                .build();

        String json = conf.toJson();
        ComputationGraphConfiguration conf2 = ComputationGraphConfiguration.fromJson(json);

        assertEquals(json,conf2.toJson());
        assertEquals(conf, conf2);
    }

    @Test
    public void testJSONWithGraphNodes(){

        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .graphBuilder()
                .addInputs("input1", "input2")
                .addLayer("cnn1", new ConvolutionLayer.Builder(2, 2).stride(2, 2).nIn(1).nOut(5).build(), "input1")
                .addLayer("cnn2", new ConvolutionLayer.Builder(2, 2).stride(2, 2).nIn(1).nOut(5).build(), "input2")
                .addVertex("merge1", new MergeVertex(), "cnn1", "cnn2")
                .addVertex("subset1", new SubsetVertex(0, 1), "merge1")
                .addLayer("dense1", new DenseLayer.Builder().nIn(20).nOut(5).build(), "subset1")
                .addLayer("dense2", new DenseLayer.Builder().nIn(20).nOut(5).build(), "subset1")
                .addVertex("add", new ElementWiseVertex(ElementWiseVertex.Op.Add), "dense1", "dense2")
                .addLayer("out", new OutputLayer.Builder().nIn(1).nOut(1).build(), "add")
                .setOutputs("out")
                .build();

        String json = conf.toJson();
        System.out.println(json);

        ComputationGraphConfiguration conf2 = ComputationGraphConfiguration.fromJson(json);

        assertEquals(json,conf2.toJson());
        assertEquals(conf, conf2);
    }

    @Test
    public void testInvalidConfigurations(){

        //Test no inputs for a layer:
        try{
            new NeuralNetConfiguration.Builder()
                    .graphBuilder()
                    .addInputs("input1")
                    .addLayer("dense1", new DenseLayer.Builder().nIn(2).nOut(2).build(), "input1")
                    .addLayer("out", new OutputLayer.Builder().nIn(2).nOut(2).build())
                    .setOutputs("out")
                    .build();
            fail("No exception thrown for invalid configuration");
        }catch(IllegalStateException e){
            //OK - exception is good
            //e.printStackTrace();
        }

        //Test no network inputs
        try{
            new NeuralNetConfiguration.Builder()
                    .graphBuilder()
                    .addLayer("dense1", new DenseLayer.Builder().nIn(2).nOut(2).build(), "input1")
                    .addLayer("out", new OutputLayer.Builder().nIn(2).nOut(2).build(), "dense1")
                    .setOutputs("out")
                    .build();
            fail("No exception thrown for invalid configuration");
        }catch(IllegalStateException e){
            //OK - exception is good
            //e.printStackTrace();
        }

        //Test no network outputs
        try{
            new NeuralNetConfiguration.Builder()
                    .graphBuilder()
                    .addInputs("input1")
                    .addLayer("dense1", new DenseLayer.Builder().nIn(2).nOut(2).build(), "input1")
                    .addLayer("out", new OutputLayer.Builder().nIn(2).nOut(2).build(), "dense1")
                    .build();
            fail("No exception thrown for invalid configuration");
        }catch(IllegalStateException e){
            //OK - exception is good
            //e.printStackTrace();
        }

        //Test: invalid input
        try{
            new NeuralNetConfiguration.Builder()
                    .graphBuilder()
                    .addInputs("input1")
                    .addLayer("dense1", new DenseLayer.Builder().nIn(2).nOut(2).build(), "input1")
                    .addLayer("out", new OutputLayer.Builder().nIn(2).nOut(2).build(), "thisDoesntExist")
                    .setOutputs("out")
                    .build();
            fail("No exception thrown for invalid configuration");
        }catch(IllegalStateException e){
            //OK - exception is good
            //e.printStackTrace();
        }

        //Test: graph with cycles
        try{
            ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                    .graphBuilder()
                    .addInputs("input1")
                    .addLayer("dense1", new DenseLayer.Builder().nIn(2).nOut(2).build(), "input1", "dense3")
                    .addLayer("dense2", new DenseLayer.Builder().nIn(2).nOut(2).build(), "dense1")
                    .addLayer("dense3", new DenseLayer.Builder().nIn(2).nOut(2).build(), "dense2")
                    .addLayer("out", new OutputLayer.Builder().nIn(2).nOut(2).build(), "dense1")
                    .setOutputs("out")
                    .build();
            //Cycle detection happens in ComputationGraph.init()
            ComputationGraph graph = new ComputationGraph(conf);
            graph.init();

            fail("No exception thrown for invalid configuration");
        }catch(IllegalStateException e){
            //OK - exception is good
            //e.printStackTrace();
        }
    }


    @Test
    public void testConfigurationWithRuntimeJSONSubtypes(){
        //Idea: suppose someone wants to use a ComputationGraph with a custom GraphVertex
        // (i.e., one not built into DL4J). Check that this works for JSON serialization
        // using runtime/reflection subtype mechanism in ComputationGraphConfiguration.fromJson()
        //Check a standard GraphVertex implementation, plus a static inner graph vertex

        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                .graphBuilder()
                .addInputs("in")
                .addVertex("test", new TestGraphVertex(3, 7), "in")
                .addVertex("test2", new StaticInnerGraphVertex(4, 5), "in")
                .setOutputs("test", "test2")
                .build();

        String json = conf.toJson();
        System.out.println(json);

        ComputationGraphConfiguration conf2 = ComputationGraphConfiguration.fromJson(json);

        assertEquals(conf,conf2);
        assertEquals(json, conf2.toJson());

        TestGraphVertex tgv = (TestGraphVertex)conf2.getVertices().get("test");
        assertEquals(3,tgv.getFirstVal());
        assertEquals(7,tgv.getSecondVal());

        StaticInnerGraphVertex sigv = (StaticInnerGraphVertex)conf.getVertices().get("test2");
        assertEquals(4,sigv.getFirstVal());
        assertEquals(5,sigv.getSecondVal());
    }

    @Test
    public void testOutputOrderDoesntChangeWhenCloning() {
        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
            .graphBuilder()
            .addInputs("in")
            .addLayer("out1", new OutputLayer.Builder().nIn(1).nOut(1).build(), "in")
            .addLayer("out2", new OutputLayer.Builder().nIn(1).nOut(1).build(), "in")
            .addLayer("out3", new OutputLayer.Builder().nIn(1).nOut(1).build(), "in")
            .setOutputs("out1", "out2", "out3")
            .build();

        ComputationGraphConfiguration cloned = conf.clone();

        String json = conf.toJson();
        String jsonCloned = cloned.toJson();

        assertEquals(json, jsonCloned);
    }

    @AllArgsConstructor @NoArgsConstructor @Data @EqualsAndHashCode(callSuper=false)
    public static class StaticInnerGraphVertex extends GraphVertex {

        private int firstVal;
        private int secondVal;

        @Override
        public GraphVertex clone() {
            return new TestGraphVertex(firstVal,secondVal);
        }

        @Override
        public int numParams(boolean backprop) {
            return 0;
        }

        @Override
        public deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.graph.vertex.GraphVertex instantiate(ComputationGraph graph, String name, int idx,
                                                                          INDArray paramsView, boolean initializeParams) {
            throw new UnsupportedOperationException("Not supported");
        }

        @Override
        public InputType getOutputType(int layerIndex, InputType... vertexInputs) throws InvalidInputTypeException {
            throw new UnsupportedOperationException();
        }
    }
}
