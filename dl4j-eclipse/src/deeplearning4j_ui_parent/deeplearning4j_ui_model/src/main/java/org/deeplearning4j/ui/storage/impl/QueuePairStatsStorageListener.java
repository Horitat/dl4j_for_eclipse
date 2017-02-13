package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.storage.impl;

import java.util.Queue;

import lombok.AllArgsConstructor;
//import org.deeplearning4j.berkeley.Pair;
//import org.deeplearning4j.api.storage.StatsStorage;
//import org.deeplearning4j.api.storage.StatsStorageEvent;
//import org.deeplearning4j.api.storage.StatsStorageListener;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorage;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageEvent;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageListener;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Pair;

/**
 * A very simple {@link StatsStorageListener}, that adds the {@link StatsStorageEvent} instances and the specified
 * {@link StatsStorage} instance (i.e., the source) to the specified queue for later processing.
 *
 * @author Alex Black
 */
@AllArgsConstructor
public class QueuePairStatsStorageListener implements StatsStorageListener {

    private final StatsStorage statsStorage;
    private final Queue<Pair<StatsStorage,StatsStorageEvent>> queue;

    @Override
    public void notify(StatsStorageEvent event) {
        queue.add(new Pair<>(statsStorage, event));
    }
}
