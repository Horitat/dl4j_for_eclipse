package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.flow.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * Description bean holds few lines worth text description for any layer
 *
 * @author raver119@gmail.com
 */
@Data
public class Description implements Serializable {
    private final static long serialVersionUID = 119L;
    private String mainLine = "";
    private String subLine = "";
    private String text = "";
}
