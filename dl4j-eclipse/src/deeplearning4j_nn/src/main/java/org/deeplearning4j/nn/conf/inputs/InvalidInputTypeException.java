/*
 *
 *  * Copyright 2016 Skymind,Inc.
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

package deeplearning4j_nn.src.main.java.org.deeplearning4j.nn.conf.inputs;


/** InvalidInputTypeException: Thrown if the GraphVertex cannot handle the type of input provided.
 */
public class InvalidInputTypeException extends RuntimeException {

    public InvalidInputTypeException(String message) {
        super(message);
    }

    public InvalidInputTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputTypeException(Throwable cause) {
        super(cause);
    }
}
