package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.preprocessor;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;

//import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;

/**
 * @author raver119@gmail.com
 */
public class LowCasePreProcessor implements TokenPreProcess {
    /**
     * Pre process a token
     *
     * @param token the token to pre process
     * @return the preprocessed token
     */
    @Override
    public String preProcess(String token) {
        return token.toLowerCase();
    }
}
