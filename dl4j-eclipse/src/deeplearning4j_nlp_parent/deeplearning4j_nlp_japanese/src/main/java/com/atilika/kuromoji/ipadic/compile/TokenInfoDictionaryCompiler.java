/**
 * Copyright © 2010-2015 Atilika Inc. and contributors (see CONTRIBUTORS.md)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  A copy of the
 * License is distributed with this work in the LICENSE.md file.  You may
 * also obtain a copy of the License from
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.com.atilika.kuromoji.ipadic.compile;

import java.util.ArrayList;
import java.util.List;

import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.com.atilika.kuromoji.compile.TokenInfoDictionaryCompilerBase;
import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.com.atilika.kuromoji.dict.GenericDictionaryEntry;
import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.com.atilika.kuromoji.util.DictionaryEntryLineParser;

public class TokenInfoDictionaryCompiler extends TokenInfoDictionaryCompilerBase<DictionaryEntry> {

    public TokenInfoDictionaryCompiler(String encoding) {
        super(encoding);
    }

    @Override
    protected DictionaryEntry parse(String line) {
        String[] fields = DictionaryEntryLineParser.parseLine(line);
        DictionaryEntry entry = new DictionaryEntry(fields);
        return entry;
    }

    @Override
    protected GenericDictionaryEntry generateGenericDictionaryEntry(DictionaryEntry entry) {
        List<String> pos = extractPosFeatures(entry);
        List<String> features = extractOtherFeatures(entry);

        return new GenericDictionaryEntry.Builder()
            .surface(entry.getSurface())
            .leftId(entry.getLeftId())
            .rightId(entry.getRightId())
            .wordCost(entry.getWordCost())
            .pos(pos)
            .features(features)
            .build();
    }

    public List<String> extractPosFeatures(DictionaryEntry entry) {
        List<String> posFeatures = new ArrayList<>();

        posFeatures.add(entry.getPartOfSpeechLevel1());
        posFeatures.add(entry.getPartOfSpeechLevel2());
        posFeatures.add(entry.getPartOfSpeechLevel3());
        posFeatures.add(entry.getPartOfSpeechLevel4());

        posFeatures.add(entry.getConjugationType());
        posFeatures.add(entry.getConjugatedForm());

        return posFeatures;
    }

    public List<String> extractOtherFeatures(DictionaryEntry entry) {
        List<String> otherFeatures = new ArrayList<>();

        otherFeatures.add(entry.getBaseForm());
        otherFeatures.add(entry.getReading());
        otherFeatures.add(entry.getPronunciation());

        return otherFeatures;
    }
}
