package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.data;

//import org.deeplearning4j.graph.api.Vertex;

import java.io.IOException;
import java.util.List;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Vertex;

/** Interface defines a method of leading vertices from a file. */
public interface VertexLoader<V> {

    List<Vertex<V>> loadVertices(String path) throws IOException;

}
