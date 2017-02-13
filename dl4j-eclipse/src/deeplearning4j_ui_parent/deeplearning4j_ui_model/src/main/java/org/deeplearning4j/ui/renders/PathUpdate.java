package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.renders;
//package org.deeplearning4j.ui.renders;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Adam Gibson
 */
public @Data @NoArgsConstructor class PathUpdate implements Serializable {
    private String path;


}
