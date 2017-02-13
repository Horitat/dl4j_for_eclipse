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

package deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizerfactory;

//import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
//import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

import java.io.InputStream;

import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import deeplearning4j_nlp_parent.deeplearning4j_nlp.src.main.java.org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

/**
 * Generates a tokenizer for a given string
 * @author Adam Gibson
 *
 */
public interface TokenizerFactory {

	/**
	 * The tokenizer to createComplex
	 * @param toTokenize the string to createComplex the tokenizer with
	 * @return the new tokenizer
	 */
      Tokenizer create(String toTokenize);

	/**
	 * Create a tokenizer based on an input stream
	 * @param toTokenize
	 * @return
	 */
	Tokenizer create(InputStream toTokenize);

    /**
     * Sets a token pre processor to be used
     * with every tokenizer
     * @param preProcessor the token pre processor to use
     */
	void setTokenPreProcessor(TokenPreProcess preProcessor);

	/**
	 * Returns TokenPreProcessor set for this TokenizerFactory instance
	 *
	 * @return TokenPreProcessor instance, or null if no preprocessor was defined
     */
	TokenPreProcess getTokenPreProcessor();
}
