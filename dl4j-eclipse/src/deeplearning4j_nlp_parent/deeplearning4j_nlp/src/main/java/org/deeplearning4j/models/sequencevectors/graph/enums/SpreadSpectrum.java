package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.enums;

/**
 * This enum describes nodes selection for PopularityWalker.
 * PLAIN: all nodes within initial spread have equal chances to be picked.
 * PROPORTIONAL: each node will have chance to be picked equal to it's popularity proportion within spread.
 *
 * @author raver119@gmail.com
 */
public enum  SpreadSpectrum {
    PLAIN,
    PROPORTIONAL,
}
