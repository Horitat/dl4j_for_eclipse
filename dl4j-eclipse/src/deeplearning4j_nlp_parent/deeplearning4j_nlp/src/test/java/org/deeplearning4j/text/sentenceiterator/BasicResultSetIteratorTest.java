package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.text.sentenceiterator;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.sentenceiterator.BasicResultSetIterator;

/**
 * @author Brad Heap nzv8fan@gmail.com
 */
public class BasicResultSetIteratorTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testHasMoreLines() throws Exception {

        // Setup a mock ResultSet object
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);

        // when .next() is called, first time true, then false
        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock.getString("line")).thenReturn("The quick brown fox");

        BasicResultSetIterator iterator = new BasicResultSetIterator(resultSetMock, "line");

        int cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(1, cnt);

    }

    @Test
    public void testHasMoreLinesAndReset() throws Exception {

        // Setup a mock ResultSet object
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);

        // when .next() is called, first time true, then false, then after we reset we want the same behaviour
        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock.getString("line")).thenReturn("The quick brown fox");

        BasicResultSetIterator iterator = new BasicResultSetIterator(resultSetMock, "line");

        int cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(1, cnt);

        iterator.reset();

        cnt = 0;
        while (iterator.hasNext()) {
            String line = iterator.nextSentence();
            cnt++;
        }

        assertEquals(1, cnt);
    }
}
