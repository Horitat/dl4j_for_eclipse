package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.vertexfactory;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Vertex;

//import org.deeplearning4j.graph.api.Vertex;

/**Vertex factory, used to create nodes from an integer index (0 to nVertices-1 inclusive)
 */
public interface VertexFactory<T> {

    Vertex<T> create(int vertexIdx);

}
