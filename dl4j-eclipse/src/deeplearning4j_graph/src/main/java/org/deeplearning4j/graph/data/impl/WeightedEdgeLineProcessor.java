package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.data.impl;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Edge;
import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.data.EdgeLineProcessor;

//import org.deeplearning4j.graph.api.Edge;
//import org.deeplearning4j.graph.data.EdgeLineProcessor;

/**A simple line processor, for data in the format {@code 0<delim>1<delim>weight}. Order per line is nodeFrom -> nodeTo, in
 * the case of directed edges, and the weights are assumed to be doubles
 * i.e., one edge per line without any additional edge information
 */
public class WeightedEdgeLineProcessor implements EdgeLineProcessor<Double> {
    private final String delimiter;
    private final String[] skipLinesStartingWith;
    private final boolean directed;

    public WeightedEdgeLineProcessor(String delimiter, boolean directed){
        this(delimiter,directed,null);
    }

    public WeightedEdgeLineProcessor(String delimiter, boolean directed, String... skipLinesStartingWith){
        this.delimiter = delimiter;
        this.skipLinesStartingWith = skipLinesStartingWith;
        this.directed = directed;
    }

    @Override
    public Edge<Double> processLine(String line) {
        if(skipLinesStartingWith != null){
            for (String s : skipLinesStartingWith) {
                if (line.startsWith(s)) return null;
            }
        }

        String[] split = line.split(delimiter);
        if(split.length != 3) throw new IllegalArgumentException("Invalid line: expected format \"" + 0 + delimiter +
            1 + delimiter + "weight\"; received \"" + line + "\"");

        int from = Integer.parseInt(split[0]);
        int to = Integer.parseInt(split[1]);
        double weight = Double.parseDouble(split[2]);
        return new Edge<>(from,to,weight,directed);
    }
}
