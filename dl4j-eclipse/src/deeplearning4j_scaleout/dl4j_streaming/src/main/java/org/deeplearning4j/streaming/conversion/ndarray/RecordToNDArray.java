package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.conversion.ndarray;

import java.io.Serializable;
import java.util.Collection;

import org.datavec.api.writable.Writable;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * A function convert from record to ndarrays
 * @author Adam Gibson
 */
public interface RecordToNDArray  extends Serializable {

    /**
     * Converts a list of records in to 1 ndarray
     * @param records the records to convert
     * @return the collection of records
     * to convert
     */
    INDArray convert(Collection<Collection<Writable>> records);

}
