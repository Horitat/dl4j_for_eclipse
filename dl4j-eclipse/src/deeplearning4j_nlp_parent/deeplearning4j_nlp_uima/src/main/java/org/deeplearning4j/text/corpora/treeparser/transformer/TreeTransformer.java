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

package deeplearning4j_nlp_parent.deeplearning4j_nlp_uima.src.main.java.org.deeplearning4j.text.corpora.treeparser.transformer;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.layers.feedforward.autoencoder.recursive.Tree;
//package org.deeplearning4j.text.corpora.treeparser.transformer;

//import org.deeplearning4j.nn.layers.feedforward.autoencoder.recursive.Tree;

/**
 * Tree transformer
 * @author Adam Gibson
 */
public interface TreeTransformer {

    /**
     * Applies a applyTransformToOrigin to a tree
     * @param t the tree to applyTransformToOrigin
     * @return the transformed tree
     */
    Tree transform(Tree t);


}
