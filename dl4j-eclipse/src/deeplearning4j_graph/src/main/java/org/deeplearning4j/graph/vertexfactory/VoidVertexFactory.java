package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.vertexfactory;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Vertex;

//import org.deeplearning4j.graph.api.Vertex;

public class VoidVertexFactory implements VertexFactory<Void> {
    @Override
    public Vertex<Void> create(int vertexIdx) {
        return new Vertex<>(vertexIdx,null);
    }
}
