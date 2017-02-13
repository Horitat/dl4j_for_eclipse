package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.storage.impl;

import java.util.Queue;

import lombok.AllArgsConstructor;
//import org.deeplearning4j.api.storage.StatsStorageEvent;
//import org.deeplearning4j.api.storage.StatsStorageListener;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageEvent;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageListener;

/**
 * A very simple {@link StatsStorageListener}, that adds the {@link StatsStorageEvent} instances to a provided queue
 * for later processing.
 *
 * @author Alex Black
 */
@AllArgsConstructor
public class QueueStatsStorageListener implements StatsStorageListener {

    private final Queue<StatsStorageEvent> queue;

    @Override
    public void notify(StatsStorageEvent event) {
        queue.add(event);
    }
}
