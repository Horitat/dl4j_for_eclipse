package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.conversion.ndarray;

import java.util.Collection;

import org.datavec.api.writable.Writable;
import org.datavec.common.data.NDArrayWritable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Assumes all records in the given batch are
 * of type @link{NDArrayWritable}
 *
 * It extracts the underlying arrays and returns a
 * concatneated array.
 *
 * @author Adam Gibson
 */
public class NDArrayRecordToNDArray implements RecordToNDArray {

    @Override
    public INDArray convert(Collection<Collection<Writable>> records) {
        INDArray[] concat = new INDArray[records.size()];
        int count = 0;
        for(Collection<Writable> record : records) {
            NDArrayWritable writable = (NDArrayWritable) record.iterator().next();
            concat[count++] = writable.get();
        }
        return Nd4j.concat(0,concat);
    }
}
