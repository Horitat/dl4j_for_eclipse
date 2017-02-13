package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.enums;

/**
 * This enum describes walker behavior when choosing next hop.
 *
 * @author raver119@gmail.com
 */
public enum WalkDirection {
    FORWARD_ONLY,
    FORWARD_PREFERRED,
    FORWARD_UNIQUE,
    RANDOM
}
