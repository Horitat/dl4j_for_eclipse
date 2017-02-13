package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.models.sequencevectors.serialization;

//import org.deeplearning4j.models.word2vec.VocabWord;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.serialization.AbstractElementFactory;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.VocabWord;

/**
 * @author raver119@gmail.com
 */
public class VocabWordFactoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDeserialize() throws Exception {
        VocabWord word = new VocabWord(1, "word");
        AbstractElementFactory<VocabWord> factory = new AbstractElementFactory<>(VocabWord.class);

        System.out.println("VocabWord JSON: " + word.toJSON());

        VocabWord word2 = factory.deserialize(word.toJSON());


        assertEquals(word, word2);
    }

    @Test
    public void testSerialize() throws Exception {
        VocabWord word = new VocabWord(1, "word");
        AbstractElementFactory<VocabWord> factory = new AbstractElementFactory<>(VocabWord.class);

        System.out.println("VocabWord JSON: " + factory.serialize(word));

        VocabWord word2 = factory.deserialize(factory.serialize(word));


        assertEquals(word, word2);
    }
}