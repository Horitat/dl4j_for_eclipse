package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.listeners;

import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageRouter;
import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.StatsStorageRouterProvider;

//import org.deeplearning4j.api.storage.StatsStorageRouter;
//import org.deeplearning4j.api.storage.StatsStorageRouterProvider;

/**
 * Returns a new instance of a {@link VanillaStatsStorageRouter}
 *
 * @author Alex Black
 */
public class VanillaStatsStorageRouterProvider implements StatsStorageRouterProvider {

    private StatsStorageRouter router = null;

    @Override
    public synchronized StatsStorageRouter getRouter() {
        if(router == null) router = new VanillaStatsStorageRouter();
        return router;
    }
}
