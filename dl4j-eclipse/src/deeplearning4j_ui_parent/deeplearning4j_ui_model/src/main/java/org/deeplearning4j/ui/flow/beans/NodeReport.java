package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.flow.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * Unified node state report: weights/gradients as distribution
 *
 * @author raver119@gmail.com
 */
@Data
public class NodeReport implements Serializable {
    private final static long serialVersionUID = 119L;
    /*
        TODO: to be implemented

        Basic idea: categorized distribution for weights/gradients built from INDArray, suitable for concurrent generation
     */
}
