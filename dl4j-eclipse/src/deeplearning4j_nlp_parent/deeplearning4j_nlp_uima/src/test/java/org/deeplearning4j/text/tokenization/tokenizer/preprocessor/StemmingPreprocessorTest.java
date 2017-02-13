package deeplearning4j_nlp_parent.deeplearning4j_nlp_uima.src.test.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor;
//package org.deeplearning4j.text.tokenization.tokenizer.preprocessor;

import static org.junit.Assert.*;

import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp_uima.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor.StemmingPreprocessor;

/**
 * @author raver119@gmail.com
 */
public class StemmingPreprocessorTest {

    @Test
    public void testPreProcess() throws Exception {
        StemmingPreprocessor preprocessor = new StemmingPreprocessor();

        String test = "TESTING.";

        String output = preprocessor.preProcess(test);

        System.out.println("Output: " + output);
        assertEquals("test", output);
    }
}