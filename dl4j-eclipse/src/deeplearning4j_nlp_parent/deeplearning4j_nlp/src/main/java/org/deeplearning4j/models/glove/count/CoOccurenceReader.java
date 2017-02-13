package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.glove.count;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

//import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

/**
 * Created by raver on 24.12.2015.
 */
public interface CoOccurenceReader<T extends SequenceElement> {
    /*
        Storage->Memory merging part
     */
    boolean hasMoreObjects();


    CoOccurrenceWeight<T> nextObject();

    void finish();
}
