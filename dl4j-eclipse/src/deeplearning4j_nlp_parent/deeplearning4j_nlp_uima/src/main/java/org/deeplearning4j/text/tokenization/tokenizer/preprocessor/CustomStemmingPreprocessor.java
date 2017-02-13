package deeplearning4j_nlp_parent.deeplearning4j_nlp_uima.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor;
//package org.deeplearning4j.text.tokenization.tokenizer.preprocessor;

import lombok.NonNull;

import org.tartarus.snowball.SnowballProgram;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;

/**
 * This is StemmingPreprocessor compatible with different StemmingProcessors defined as lucene/tartarus SnowballProgram
 * Like, but not limited to: RussianStemmer, DutchStemmer, FrenchStemmer etc
 *
 * PLEASE NOTE: This preprocessor is thread-safe by using synchronized method
 *
 * @author raver119@gmail.com
 */
public class CustomStemmingPreprocessor extends CommonPreprocessor {
    private SnowballProgram stemmer;
    public CustomStemmingPreprocessor(@NonNull SnowballProgram stemmer) {
        this.stemmer = stemmer;
    }

    @Override
    public synchronized String preProcess(String token) {
        String prep = super.preProcess(token);
        stemmer.setCurrent(prep);
        stemmer.stem();
        return stemmer.getCurrent();
    }
}
