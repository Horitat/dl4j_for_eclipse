package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.huffman;
//package org.deeplearning4j.models.sequencevectors.graph.huffman;

/** Binary tree interface, used in DeepWalk */
public interface BinaryTree {

    long getCode(int element);

    int getCodeLength(int element);

    String getCodeString(int element);

    int[] getPathInnerNodes(int element);
}
