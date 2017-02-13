package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.flow.beans;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 *
 * @author raver119@gmail.com
 */
@Data
public class LayerParams implements Serializable {
    private Map W;
    private Map RW;
    private Map RWF;
    private Map B;
}
