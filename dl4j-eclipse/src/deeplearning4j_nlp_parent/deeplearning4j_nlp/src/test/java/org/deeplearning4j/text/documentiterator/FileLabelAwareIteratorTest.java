package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.documentiterator;

import static org.junit.Assert.*;

import org.datavec.api.util.ClassPathResource;
import org.junit.Before;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.FileLabelAwareIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.LabelledDocument;

/**
 * Created by raver119 on 03.01.16.
 */
public class FileLabelAwareIteratorTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testExtractLabelFromPath1() throws Exception {
        ClassPathResource resource = new ClassPathResource("/labeled");

        FileLabelAwareIterator iterator = new FileLabelAwareIterator.Builder()
                .addSourceFolder(resource.getFile())
                .build();

        int cnt = 0;
        while (iterator.hasNextDocument()) {
            LabelledDocument document = iterator.nextDocument();
            assertNotEquals(null, document);
            assertNotEquals(null, document.getContent());
            assertNotEquals(null, document.getLabel());
            cnt++;
        }

        assertEquals(3, cnt);


        assertEquals(3, iterator.getLabelsSource().getNumberOfLabelsUsed());

        assertTrue(iterator.getLabelsSource().getLabels().contains("positive"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("negative"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("neutral"));
    }


    @Test
    public void testExtractLabelFromPath2() throws Exception {
        ClassPathResource resource = new ClassPathResource("/labeled");
        ClassPathResource resource2= new ClassPathResource("/rootdir");

        FileLabelAwareIterator iterator = new FileLabelAwareIterator.Builder()
                .addSourceFolder(resource.getFile())
                .addSourceFolder(resource2.getFile())
                .build();

        int cnt = 0;
        while (iterator.hasNextDocument()) {
            LabelledDocument document = iterator.nextDocument();
            assertNotEquals(null, document);
            assertNotEquals(null, document.getContent());
            assertNotEquals(null, document.getLabel());
            cnt++;
        }

        assertEquals(5, cnt);


        assertEquals(5, iterator.getLabelsSource().getNumberOfLabelsUsed());

        assertTrue(iterator.getLabelsSource().getLabels().contains("positive"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("negative"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("neutral"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("label1"));
        assertTrue(iterator.getLabelsSource().getLabels().contains("label2"));
    }
}