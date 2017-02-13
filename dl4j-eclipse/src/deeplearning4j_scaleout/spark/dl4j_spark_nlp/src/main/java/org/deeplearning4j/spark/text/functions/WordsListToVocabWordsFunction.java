package deeplearning4j_scaleout.spark.dl4j_spark_nlp.src.main.java.org.deeplearning4j.spark.text.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
//import org.deeplearning4j.berkeley.Pair;
//import org.deeplearning4j.models.word2vec.VocabWord;
//import org.deeplearning4j.models.word2vec.wordstore.VocabCache;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.VocabWord;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.berkeley.Pair;

/**
 * @author jeffreytang
 */
public class WordsListToVocabWordsFunction implements Function<Pair<List<String>, AtomicLong>, List<VocabWord>> {

    Broadcast<VocabCache<VocabWord>> vocabCacheBroadcast;

    public WordsListToVocabWordsFunction(Broadcast<VocabCache<VocabWord>> vocabCacheBroadcast) {
        this. vocabCacheBroadcast = vocabCacheBroadcast;
    }

    @Override
    public List<VocabWord> call(Pair<List<String>, AtomicLong> pair)
            throws Exception {
        List<String> wordsList = pair.getFirst();
        List<VocabWord> vocabWordsList = new ArrayList<>();
        VocabCache<VocabWord> vocabCache = vocabCacheBroadcast.getValue();
        for (String s : wordsList) {
            if (vocabCache.containsWord(s)) {
                VocabWord word = vocabCache.wordFor(s);

                vocabWordsList.add(word);
            } else if (vocabCache.containsWord("UNK")) {
                VocabWord word = vocabCache.wordFor("UNK");

                vocabWordsList.add(word);
            }
        }
        return vocabWordsList;
    }
}

