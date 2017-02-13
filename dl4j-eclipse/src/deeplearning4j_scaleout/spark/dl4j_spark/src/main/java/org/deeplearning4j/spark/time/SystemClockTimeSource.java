package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.time;


/**
 * A {@link TimeSource} implementation that is identical to calling {@link System#currentTimeMillis()}
 *
 * @author Alex Black
 */
public class SystemClockTimeSource implements TimeSource {

    public static TimeSource getInstance(){
        return new SystemClockTimeSource();
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
