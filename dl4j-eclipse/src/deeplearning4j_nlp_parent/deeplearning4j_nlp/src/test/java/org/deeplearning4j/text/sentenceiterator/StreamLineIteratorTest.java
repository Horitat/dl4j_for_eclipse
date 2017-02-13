package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.sentenceiterator;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.datavec.api.util.ClassPathResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.StreamLineIterator;

/**
 * Created by fartovii on 09.11.15.
 */
public class StreamLineIteratorTest {

    protected Logger logger = LoggerFactory.getLogger(StreamLineIteratorTest.class);

    @Test
    public void testHasNext() throws Exception {

        ClassPathResource reuters5250 = new ClassPathResource("/reuters/5250");
        File f = reuters5250.getFile();

        StreamLineIterator iterator = new StreamLineIterator.Builder(new FileInputStream(f))
                .setFetchSize(100)
                .build();

        int cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();

            assertNotEquals(null, line);
            logger.info("Line: " + line);
            cnt++;
        }

        assertEquals(24, cnt);
    }
}