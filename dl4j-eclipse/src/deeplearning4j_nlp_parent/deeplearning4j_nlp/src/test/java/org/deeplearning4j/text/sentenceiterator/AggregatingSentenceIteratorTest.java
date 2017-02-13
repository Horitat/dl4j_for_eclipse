package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.sentenceiterator;
//package org.deeplearning4j.text.sentenceiterator;

import static org.junit.Assert.*;

import java.io.File;

import org.datavec.api.util.ClassPathResource;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.AggregatingSentenceIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.BasicLineIterator;

/**
 * Created by fartovii on 01.12.15.
 */
public class AggregatingSentenceIteratorTest {

    @Test
    public void testHasNext() throws Exception {
        ClassPathResource resource = new ClassPathResource("/big/raw_sentences.txt");
        File file = resource.getFile();
        BasicLineIterator iterator = new BasicLineIterator(file);
        BasicLineIterator iterator2 = new BasicLineIterator(file);

        AggregatingSentenceIterator aggr = new AggregatingSentenceIterator.Builder()
                .addSentenceIterator(iterator)
                .addSentenceIterator(iterator2)
                .build();

        int cnt = 0;
        while (aggr.hasNext()) {
            String line = aggr.nextSentence();
            cnt++;
        }

        assertEquals((97162 * 2), cnt);

        aggr.reset();

        while (aggr.hasNext()) {
            String line = aggr.nextSentence();
            cnt++;
        }

        assertEquals((97162 * 4), cnt);
    }
}