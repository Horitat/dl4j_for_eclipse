package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.interfaces;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.sequence.Sequence;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

//import org.deeplearning4j.models.sequencevectors.sequence.Sequence;
//import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

/**
 * SequenceIterator is basic interface for learning abstract data that can be represented as sequence of some elements.
 *
 * @author raver119@gmail.com
 */
public interface SequenceIterator<T extends SequenceElement> {

    boolean hasMoreSequences();

    Sequence<T> nextSequence();

    void reset();
}
