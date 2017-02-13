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

package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.tokenization.tokenizer.tokenprepreprocessor;
//package org.deeplearning4j.text.tokenization.tokenizer.tokenprepreprocessor;

//import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
//import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import static org.junit.Assert.*;

import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;

/**
 * Created by agibsonccc on 10/18/14.
 */
public class EndingPreProcessorTest {
    @Test
    public void testPreProcessor() {
        TokenPreProcess preProcess = new EndingPreProcessor();
        String endingTest = "ending";
        assertEquals("end",preProcess.preProcess(endingTest));

    }

}