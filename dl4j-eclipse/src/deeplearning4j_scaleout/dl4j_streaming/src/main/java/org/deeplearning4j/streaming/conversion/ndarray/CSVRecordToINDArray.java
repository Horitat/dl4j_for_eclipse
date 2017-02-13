package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.conversion.ndarray;
//package org.deeplearning4j.streaming.conversion.ndarray;

import java.util.Collection;

import org.datavec.api.writable.Writable;
import org.datavec.common.RecordConverter;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Assumes csv format and converts a batch of records in to a
 * size() x record length matrix.
 *
 * @author Adam Gibson
 */
public class CSVRecordToINDArray implements RecordToNDArray {
    @Override
    public INDArray convert(Collection<Collection<Writable>> records) {
        INDArray ret = Nd4j.create(records.size(),records.iterator().next().size());
        int count = 0;
        for(Collection<Writable> record : records) {
            ret.putRow(count++, RecordConverter.toArray(record));
        }
        return ret;
    }
}
