/*
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package deeplearning4j_nn.src.main.java.org.deeplearning4j.datasets.iterator;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import lombok.Getter;

import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;


/**
 * A dataset iterator for doing multiple passes over a dataset
 */
public class MultipleEpochsIterator implements DataSetIterator {
    @VisibleForTesting
	private int epochs = 0;
    protected int numEpochs;
    private int batch=0;
    protected int lastBatch = getBatch();
    protected DataSetIterator iter;
    protected DataSet ds;
    protected List<DataSet> batchedDS = Lists.newArrayList();
    protected static final Logger log = LoggerFactory.getLogger(MultipleEpochsIterator.class);
    @Getter protected DataSetPreProcessor preProcessor;
    protected boolean newEpoch = false;
    protected int queueSize = 1;
    protected boolean async = false;
    protected AtomicLong iterationsCounter = new AtomicLong(0);
    protected long totalIterations = Long.MAX_VALUE;

    public MultipleEpochsIterator(int numEpochs,DataSetIterator iter) {
        this.numEpochs = numEpochs;
        this.iter = iter;
    }

    public MultipleEpochsIterator(int numEpochs,DataSetIterator iter, int queueSize) {
        this.numEpochs = numEpochs;
        this.queueSize = queueSize;
        this.async = queueSize > 1 && iter.asyncSupported();
        this.iter = async ? new AsyncDataSetIterator(iter, queueSize): iter;
    }

    public MultipleEpochsIterator(DataSetIterator iter, int queueSize, long totalIterations) {
        this.numEpochs = Integer.MAX_VALUE;
        this.queueSize = queueSize;
        this.async = queueSize > 1 && iter.asyncSupported();
        this.iter = async ? new AsyncDataSetIterator(iter, queueSize): iter;
        this.totalIterations = totalIterations;
    }

    public MultipleEpochsIterator(DataSetIterator iter, long totalIterations) {
        this.numEpochs = Integer.MAX_VALUE;
        this.queueSize = 1;
        this.async = false;
        this.iter = async ? new AsyncDataSetIterator(iter, queueSize): iter;
        this.totalIterations = totalIterations;
    }

    public MultipleEpochsIterator(int numEpochs,DataSet ds) {
        this.numEpochs = numEpochs;
        this.ds = ds;
    }


    /**
     * Like the standard next method but allows a
     * customizable number of examples returned
     *
     * @param num the number of examples
     * @return the next data applyTransformToDestination
     */
    @Override
    public DataSet next(int num) {
        DataSet next;
        setBatch(getBatch() + 1);
        iterationsCounter.incrementAndGet();
        if(iter == null){
            // return full DataSet
            if(num == -1) {
                next = ds;
                if (getEpochs() < numEpochs)
                    trackEpochs();
            }
            // return DataSet broken into batches
            else {
                if(batchedDS.isEmpty() && num > 0)
                    batchedDS = ds.batchBy(num);
                next = batchedDS.get(getBatch());
                if(getBatch()+1 == batchedDS.size()) {
                    trackEpochs();
                    if (getEpochs() < numEpochs)
                        setBatch(-1);
                }
            }
        } else {
            next = num == -1? iter.next(): iter.next(num);
            if(!iter.hasNext()) {
                trackEpochs();
                // track number of epochs and won't reset if it's over
                if (getEpochs() < numEpochs) {
                    iter.reset();
                    lastBatch = getBatch();
                    setBatch(0);
                }
            }
        }
        if(preProcessor != null)
            preProcessor.preProcess(next);
        return next;
    }

    public void trackEpochs(){
        setEpochs(getEpochs() + 1);
        newEpoch = true;
    }

    @Override
    public DataSet next() {
        return next(-1);
    }

    /**
     * Total examples in the iterator
     *
     * @return
     */
    @Override
    public int totalExamples() {
        return iter.totalExamples();
    }

    /**
     * Input columns for the dataset
     *
     * @return
     */
    @Override
    public int inputColumns() {
        return iter.inputColumns();
    }

    /**
     * The number of labels for the dataset
     *
     * @return
     */
    @Override
    public int totalOutcomes() {
        return iter.totalOutcomes();
    }

    @Override
    public boolean resetSupported(){
        return iter.resetSupported();
    }

    @Override
    public boolean asyncSupported() {
            return false;
    }

    /**
     * Resets the iterator back to the beginning
     */
    @Override
    public void reset() {
        setEpochs(0);
        lastBatch = getBatch();
        setBatch(0);
        iterationsCounter.set(0);
        iter.reset();
    }

    /**
     * Batch size
     *
     * @return
     */
    @Override
    public int batch() {
        return iter.batch();
    }

    /**
     * The current cursor if applicable
     *
     * @return
     */
    @Override
    public int cursor() {
        return iter.cursor();
    }

    /**
     * Total number of examples in the dataset
     *
     * @return
     */
    @Override
    public int numExamples() {
        return iter.numExamples();
    }

    @Override
    public void setPreProcessor(DataSetPreProcessor preProcessor) {
        this.preProcessor = (DataSetPreProcessor) preProcessor;
    }

    @Override
    public List<String> getLabels() {
        return iter.getLabels();
    }


    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (iterationsCounter.get() >= totalIterations)
            return false;

        if (newEpoch) {
            log.info("Epoch " + getEpochs() + ", number of batches completed " + lastBatch);
            newEpoch = false;
        }
        if (iter == null)
            return (getEpochs() < numEpochs) && ((!batchedDS.isEmpty() && batchedDS.size() > getBatch()) || batchedDS.isEmpty());
        else
            // either there are still epochs to complete or its the first epoch
            return (getEpochs() < numEpochs) || (iter.hasNext() && (getEpochs() == 0 || getEpochs() == numEpochs));
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this iterator
     * @throws IllegalStateException         if the {@code next} method has not
     *                                       yet been called, or the {@code remove} method has already
     *                                       been called after the last call to the {@code next}
     *                                       method
     */
    @Override
    public void remove() {
           iter.remove();
    }


    //self made
	public int getEpochs() {
		return epochs;
	}

	public void setEpochs(int epochs) {
		this.epochs = epochs;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}
	//so far
}
