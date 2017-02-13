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

package deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.trainer;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.EarlyStoppingResult;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.earlystopping.listener.EarlyStoppingListener;
import deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.api.Model;

//import org.deeplearning4j.earlystopping.EarlyStoppingResult;
//import org.deeplearning4j.earlystopping.listener.EarlyStoppingListener;
//import org.deeplearning4j.nn.api.Model;

/** Interface for early stopping trainers */
public interface IEarlyStoppingTrainer<T extends Model> {

    /** Conduct early stopping training */
    EarlyStoppingResult<T> fit();

    /** Set the early stopping listener */
    void setListener(EarlyStoppingListener<T> listener);

}
