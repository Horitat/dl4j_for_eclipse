package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.impl.multilayer.evaluation;

import lombok.extern.slf4j.Slf4j;

import org.apache.spark.api.java.function.Function2;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.eval.IEvaluation;
//import org.deeplearning4j.eval.Evaluation;
//import org.deeplearning4j.eval.IEvaluation;

/**
 *
 * Reduction function for use with {@link IEvaluateFlatMapFunction} for distributed evaluation
 *
 * @author Alex Black
 */
@Slf4j
public class IEvaluationReduceFunction<T extends IEvaluation> implements Function2<T, T, T> {
    public IEvaluationReduceFunction(){}

    @Override
    public T call(T eval1, T eval2) throws Exception {
        eval1.merge(eval2);
        return eval1;
    }
}
