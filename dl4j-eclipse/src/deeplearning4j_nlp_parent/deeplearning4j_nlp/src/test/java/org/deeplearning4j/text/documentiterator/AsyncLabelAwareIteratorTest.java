package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.documentiterator;
//package org.deeplearning4j.text.documentiterator;

import static org.junit.Assert.*;

import org.datavec.api.util.ClassPathResource;
//import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
//import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.AsyncLabelAwareIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.BasicLabelAwareIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.SentenceIterator;

/**
 * @author raver119@gmail.com
 */
public class AsyncLabelAwareIteratorTest {
    @Test
    public void nextDocument() throws Exception {
        SentenceIterator sentence = new BasicLineIterator(new ClassPathResource("/big/raw_sentences.txt").getFile());
        BasicLabelAwareIterator backed = new BasicLabelAwareIterator.Builder(sentence).build();

        int cnt = 0;
        while (backed.hasNextDocument()) {
            backed.nextDocument();
            cnt++;
        }
        assertEquals(97162, cnt);

        backed.reset();

        AsyncLabelAwareIterator iterator = new AsyncLabelAwareIterator(backed, 64);
        cnt = 0;
        while (iterator.hasNext()) {
            iterator.next();
            cnt++;

            if (cnt == 10)
                iterator.reset();
        }
        assertEquals(97172, cnt);
    }

}