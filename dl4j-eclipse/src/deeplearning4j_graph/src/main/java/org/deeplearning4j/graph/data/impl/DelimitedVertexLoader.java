package deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.data.impl;

//import org.deeplearning4j.graph.api.Vertex;
//import org.deeplearning4j.graph.data.VertexLoader;
//import org.deeplearning4j.graph.exception.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.api.Vertex;
import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.data.VertexLoader;
import deeplearning4j_graph.src.main.java.org.deeplearning4j.graph.exception.ParseException;

/**Load vertex information, one per line of form "0<delim>Some text attribute/label"
 */
public class DelimitedVertexLoader implements VertexLoader<String> {

    private final String delimiter;
    private final String[] ignoreLinesPrefix;

    public DelimitedVertexLoader(String delimiter){
        this(delimiter,null);
    }

    public DelimitedVertexLoader(String delimiter, String... ignoreLinesPrefix){
        this.delimiter = delimiter;
        this.ignoreLinesPrefix = ignoreLinesPrefix;
    }

    @Override
    public List<Vertex<String>> loadVertices(String path) throws IOException {
        List<Vertex<String>> vertices = new ArrayList<>();

        int lineCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            String line;
            while( (line = br.readLine()) != null ) {
                lineCount++;
                if(ignoreLinesPrefix != null){
                    boolean skipLine = false;
                    for(String s : ignoreLinesPrefix){
                        if(line.startsWith(s)){
                            skipLine = true;
                            break;
                        }
                    }
                    if(skipLine) continue;
                }

                int idx = line.indexOf(delimiter);
                if(idx == -1) throw new ParseException("Error parsing line (could not find delimiter): " + line);

                String first = line.substring(0,idx);
                String second = line.substring(idx+1);

                vertices.add(new Vertex<>(Integer.parseInt(first),second));
            }
        }

        return vertices;
    }
}
