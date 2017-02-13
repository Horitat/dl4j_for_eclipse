package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.stats.api;

import deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.stats.StatsListener;

//import org.deeplearning4j.ui.stats.StatsListener;

/**
 * Stats type, for use in {@link StatsListener}
 *
 * Note: Gradients are pre-update (i.e., raw gradients - pre-LR/momentum/rmsprop etc), Updates are post update
 *
 * @author Alex Black
 */
public enum StatsType {

    Parameters, Gradients, Updates, Activations

}
