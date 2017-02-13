package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.vertex;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.primitives.Vertex;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

//import org.deeplearning4j.models.sequencevectors.graph.primitives.Vertex;
//import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;

/**
 * Vertex factory, used to create nodes from an integer index (0 to nVertices-1 inclusive)
 *
 * @author AlexDBlack
 */
public interface VertexFactory<T extends SequenceElement> {

    Vertex<T> create(int vertexIdx);

    Vertex<T> create(int vertexIdx, T element);
}
