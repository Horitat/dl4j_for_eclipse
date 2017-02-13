package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.conversion.dataset;

import java.io.Serializable;
import java.util.Collection;

import org.datavec.api.writable.Writable;
import org.nd4j.linalg.dataset.DataSet;

/**
 * Converts a list of records in to a dataset.
 * @author Adam Gibson
 */
public interface RecordToDataSet extends Serializable {

    /**
     * Converts records in to a dataset
     * @param records the records to convert
     * @param numLabels the number of labels for the dataset
     * @return the converted dataset.
     */
    DataSet convert(Collection<Collection<Writable>> records, int numLabels);

}
