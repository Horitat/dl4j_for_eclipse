package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.bagofwords.vectorizer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.iterators.AbstractSequenceIterator;
//import org.deeplearning4j.models.sequencevectors.iterators.AbstractSequenceIterator;
//import org.deeplearning4j.models.sequencevectors.transformers.impl.SentenceTransformer;
//import org.deeplearning4j.models.word2vec.VocabWord;
//import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
//import org.deeplearning4j.models.word2vec.wordstore.VocabConstructor;
//import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
//import org.deeplearning4j.text.documentiterator.LabelAwareIterator;
//import org.deeplearning4j.text.documentiterator.LabelsSource;
//import org.deeplearning4j.text.invertedindex.InvertedIndex;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.sequencevectors.transformers.impl.SentenceTransformer;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.VocabWord;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.wordstore.VocabConstructor;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.LabelAwareIterator;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.documentiterator.LabelsSource;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.invertedindex.InvertedIndex;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

/**
 * @author raver119@gmail.com
 */
public abstract class BaseTextVectorizer implements TextVectorizer {
    @Setter protected transient TokenizerFactory tokenizerFactory;
    protected transient LabelAwareIterator iterator;
    protected int minWordFrequency;
    @Getter protected VocabCache<VocabWord> vocabCache;
    protected LabelsSource labelsSource;
    protected List<String> stopWords = new ArrayList<>();
    @Getter protected transient InvertedIndex<VocabWord> index;

    //self made. protected -> public
    public LabelsSource getLabelsSource() {
        return labelsSource;
    }
//    protected LabelsSource getLabelsSource() {
//    	return labelsSource;
//    }

    public void buildVocab() {
        if (vocabCache == null) vocabCache = new AbstractCache.Builder<VocabWord>().build();


        SentenceTransformer transformer = new SentenceTransformer.Builder()
                .iterator(this.iterator)
                .tokenizerFactory(tokenizerFactory)
                .build();

        AbstractSequenceIterator<VocabWord> iterator = new AbstractSequenceIterator.Builder<>(transformer)
                .build();

        VocabConstructor<VocabWord> constructor = new VocabConstructor.Builder<VocabWord>()
                .addSource(iterator, minWordFrequency)
                .setTargetVocabCache(vocabCache)
                .build();

        constructor.buildJointVocabulary(false, true);
    }

    @Override
    public void fit() {
        buildVocab();
    }

    /**
     * Returns the number of words encountered so far
     *
     * @return the number of words encountered so far
     */
    @Override
    public long numWordsEncountered() {
        return vocabCache.totalWordOccurrences();
    }
}
