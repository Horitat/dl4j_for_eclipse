package deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.test.java.org.deeplearning4j.text.tokenization.tokenizer;
//package org.deeplearning4j.text.tokenization.tokenizer;

import static org.junit.Assert.*;

//import org.deeplearning4j.text.tokenization.tokenizer.JapaneseTokenizer;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.JapaneseTokenizerFactory;
//import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory.JapaneseTokenizerFactory;

public class JapaneseTokenizerTest {

    @Test
    public void testJapaneseTokenizer() throws Exception {
        String toTokenize = "黒い瞳の綺麗な女の子";
        TokenizerFactory t = new JapaneseTokenizerFactory();
        Tokenizer tokenizer = t.create(toTokenize);
        String[] expect = { "黒い", "瞳", "の", "綺麗", "な", "女の子" };

        assertEquals(expect.length, tokenizer.countTokens());
        for (int i = 0; i < tokenizer.countTokens(); ++i) {
            assertEquals(tokenizer.nextToken(), expect[i]);
        }
    }
}
