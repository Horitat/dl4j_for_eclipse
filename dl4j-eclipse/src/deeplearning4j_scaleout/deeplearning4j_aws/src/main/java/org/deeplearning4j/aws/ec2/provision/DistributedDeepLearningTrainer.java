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

package deeplearning4j_scaleout.deeplearning4j_aws.src.main.java.org.deeplearning4j.aws.ec2.provision;

public class DistributedDeepLearningTrainer {

	private DistributedDeepLearningTrainer() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClusterSetup clusterSet = new ClusterSetup(args);

	}

}
