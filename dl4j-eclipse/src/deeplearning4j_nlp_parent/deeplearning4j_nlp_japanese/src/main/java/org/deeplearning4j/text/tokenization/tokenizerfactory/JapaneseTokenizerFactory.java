package deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory;
//package org.deeplearning4j.text.tokenization.tokenizerfactory;

//import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
//import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
//import org.deeplearning4j.text.tokenization.tokenizer.JapaneseTokenizer;

import java.io.InputStream;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.JapaneseTokenizer;

public class JapaneseTokenizerFactory implements TokenizerFactory {
  private TokenPreProcess preProcess;
  private boolean useBaseForm;

  public JapaneseTokenizerFactory() {
  }

  @Override
  public Tokenizer create(String toTokenize) {
    if (toTokenize.isEmpty()) {
      throw new IllegalArgumentException("Unable to proceed; no sentence to tokenize");
    }
    JapaneseTokenizer t = new JapaneseTokenizer(toTokenize);
    return t;
  }

  @Override
  public Tokenizer create(InputStream toTokenize) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTokenPreProcessor(TokenPreProcess preProcessor) {
    this.preProcess = preProcess;
  }

  @Override
  public TokenPreProcess getTokenPreProcessor() {
    return this.preProcess;
  }

}

