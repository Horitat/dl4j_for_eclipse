package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.sentenceiterator;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.datavec.api.util.ClassPathResource;
import org.junit.Before;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.BasicLineIterator;

/**
 * @author raver119@gmail.com
 */
public class BasicLineIteratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testHasMoreLinesFile() throws Exception {
        ClassPathResource resource = new ClassPathResource("/big/raw_sentences.txt");
        File file = resource.getFile();
        BasicLineIterator iterator = new BasicLineIterator(file);

        int cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(97162, cnt);

        iterator.reset();

        cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(97162, cnt);
    }

    @Test
    public void testHasMoreLinesStream() throws Exception {
        ClassPathResource resource = new ClassPathResource("/big/raw_sentences.txt");
        File file = resource.getFile(); //.getParentFile();
        BasicLineIterator iterator = new BasicLineIterator(new FileInputStream(file));

        int cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(97162, cnt);

        iterator.reset();

        cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(97162, cnt);
    }
}