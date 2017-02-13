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

//package org.deeplearning4j.clustering.algorithm.strategy;
package deeplearning4j_core.src.main.java.org.deeplearning4j.clustering.algorithm.strategy;

import deeplearning4j_core.src.main.java.org.deeplearning4j.clustering.algorithm.condition.ClusteringAlgorithmCondition;
import deeplearning4j_core.src.main.java.org.deeplearning4j.clustering.algorithm.iteration.IterationHistory;


//import org.deeplearning4j.clustering.algorithm.condition.ClusteringAlgorithmCondition;
//import org.deeplearning4j.clustering.algorithm.iteration.IterationHistory;

public interface ClusteringStrategy {

	ClusteringStrategyType getType();
	boolean isStrategyOfType(ClusteringStrategyType type);

	Integer getInitialClusterCount();

	String getDistanceFunction();

	boolean isAllowEmptyClusters();

	ClusteringAlgorithmCondition getTerminationCondition();

	boolean isOptimizationDefined();
	boolean isOptimizationApplicableNow(IterationHistory iterationHistory);

	BaseClusteringStrategy endWhenIterationCountEquals(int maxIterationCount);
	BaseClusteringStrategy endWhenDistributionVariationRateLessThan(double rate);

}