package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.test.java.org.deeplearning4j.models.sequencevectors.transformers.impl;
//package org.deeplearning4j.models.sequencevectors.transformers.impl;

//import org.deeplearning4j.models.sequencevectors.graph.enums.NoEdgeHandling;
//import org.deeplearning4j.models.sequencevectors.graph.primitives.Graph;
//import org.deeplearning4j.models.sequencevectors.graph.primitives.IGraph;
//import org.deeplearning4j.models.sequencevectors.graph.vertex.AbstractVertexFactory;
//import org.deeplearning4j.models.sequencevectors.graph.walkers.GraphWalker;
//import org.deeplearning4j.models.sequencevectors.graph.walkers.impl.RandomWalker;
//import org.deeplearning4j.models.sequencevectors.sequence.Sequence;
//import org.deeplearning4j.models.word2vec.VocabWord;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.enums.NoEdgeHandling;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.primitives.Graph;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.primitives.IGraph;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.vertex.AbstractVertexFactory;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.walkers.GraphWalker;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.graph.walkers.impl.RandomWalker;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.sequence.Sequence;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.transformers.impl.GraphTransformer;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.VocabWord;

/**
 * @author raver119@gmail.com
 */
public class GraphTransformerTest {

    private static IGraph<VocabWord, Double> graph;

    @Before
    public void setUp() throws Exception {
        if (graph == null) {
            graph = new Graph<>(10, false, new AbstractVertexFactory<VocabWord>());

            for (int i = 0; i < 10; i++) {
                graph.getVertex(i).setValue(new VocabWord(i, String.valueOf(i)));

                int x = i + 3;
                if (x >= 10) x = 0;
                graph.addEdge(i, x, 1.0, false);
            }
        }
    }

    @Test
    public void testGraphTransformer1() throws Exception {
        GraphWalker<VocabWord> walker = new RandomWalker.Builder<>(graph)
                .setNoEdgeHandling(NoEdgeHandling.CUTOFF_ON_DISCONNECTED)
                .build();

        GraphTransformer<VocabWord> transformer = new GraphTransformer.Builder<>(graph)
                .setGraphWalker(walker)
                .build();

        Iterator<Sequence<VocabWord>> iterator = transformer.iterator();
        int cnt = 0;
        while (iterator.hasNext()) {
            Sequence<VocabWord> sequence = iterator.next();
            System.out.println(sequence);
            cnt++;
        }

        assertEquals(10, cnt);
    }
}