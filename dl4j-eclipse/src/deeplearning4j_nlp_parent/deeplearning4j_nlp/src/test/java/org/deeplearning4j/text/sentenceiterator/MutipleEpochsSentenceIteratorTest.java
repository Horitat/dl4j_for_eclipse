package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.sentenceiterator;

import static org.junit.Assert.*;

import org.datavec.api.util.ClassPathResource;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.MutipleEpochsSentenceIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.SentenceIterator;

/**
 * @author raver119@gmail.com
 */
public class MutipleEpochsSentenceIteratorTest {
    @Test
    public void hasNext() throws Exception {
        SentenceIterator iterator = new MutipleEpochsSentenceIterator(new BasicLineIterator(new ClassPathResource("/big/raw_sentences.txt").getFile()), 100);

        int cnt = 0;
        while (iterator.hasNext()) {
            iterator.nextSentence();
            cnt++;
        }

        assertEquals(9716200, cnt);
    }

}