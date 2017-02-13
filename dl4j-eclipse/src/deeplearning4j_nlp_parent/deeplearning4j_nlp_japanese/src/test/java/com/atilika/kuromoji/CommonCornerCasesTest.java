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
package deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.test.java.com.atilika.kuromoji;
//package com.atilika.kuromoji;

//import static com.atilika.kuromoji.TestUtils.assertTokenSurfacesEquals;
import static deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.test.java.com.atilika.kuromoji.TestUtils.*;

import java.util.Arrays;

import deeplearning4j_nlp_parent.deeplearning4j_nlp_japanese.src.main.java.com.atilika.kuromoji.TokenizerBase;

public class CommonCornerCasesTest {

    public static void testPunctuation(TokenizerBase tokenizer) {
        String gerryNoHanaNoHanashi = "僕の鼻はちょっと\r\n長いよ。";

        assertTokenSurfacesEquals(
            Arrays.asList(
                "僕", "の", "鼻", "は", "ちょっと", "\r", "\n", "長い", "よ", "。"
            ),

            tokenizer.tokenize(gerryNoHanaNoHanashi)
        );
    }
}