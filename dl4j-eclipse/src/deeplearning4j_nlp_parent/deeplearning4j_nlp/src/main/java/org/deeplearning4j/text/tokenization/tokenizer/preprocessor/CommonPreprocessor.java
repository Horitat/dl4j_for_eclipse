package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
//package org.deeplearning4j.text.tokenization.tokenizer.preprocessor;

//import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;

/**
 * @author jeffreytang
 */
public class CommonPreprocessor implements TokenPreProcess {
    @Override
    public String preProcess(String token) {
        return StringCleaning.stripPunct(token).toLowerCase();
    }
}
