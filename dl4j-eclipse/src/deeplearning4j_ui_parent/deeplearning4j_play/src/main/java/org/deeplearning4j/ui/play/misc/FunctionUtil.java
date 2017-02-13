package deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.play.misc;
//package org.deeplearning4j.ui.play.misc;

import java.util.function.Function;
import java.util.function.Supplier;

import play.libs.F;
import play.mvc.Result;

/**
 * Utility methods for Routing
 *
 * @author Alex Black
 */
public class FunctionUtil {

    public static F.Function0<Result> function0(Supplier<Result> supplier) {
        return supplier::get;
    }

    public static <T> F.Function<T, Result> function(Function<T, Result> function) {
        return function::apply;
    }

}
