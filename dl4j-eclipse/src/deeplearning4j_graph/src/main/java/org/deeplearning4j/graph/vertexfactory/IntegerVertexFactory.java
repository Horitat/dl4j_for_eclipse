package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.vertexfactory;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Vertex;
//package org.deeplearning4j.graph.vertexfactory;

//import org.deeplearning4j.graph.api.Vertex;

public class IntegerVertexFactory implements VertexFactory<Integer> {
    @Override
    public Vertex<Integer> create(int vertexIdx) {
        return new Vertex<>(vertexIdx,vertexIdx);
    }
}
