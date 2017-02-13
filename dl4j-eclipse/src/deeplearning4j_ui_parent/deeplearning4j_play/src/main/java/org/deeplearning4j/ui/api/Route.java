package deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.api;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import play.mvc.Result;

/**
 * A Route specifies an endpoint that can be queried in the UI - along with how it should be handled
 *
 * @author Alex Black
 */
@Data
@AllArgsConstructor
public class Route {
    private final String route;
    private final HttpMethod httpMethod;
    private final FunctionType functionType;
    private final Supplier<Result> supplier;
    private final Function<String, Result> function;
    private final BiFunction<String, String, Result> function2;

    public Route(String route, HttpMethod method, FunctionType functionType, Supplier<Result> supplier) {
        this(route, method, functionType, supplier, null, null);
    }

    public Route(String route, HttpMethod method, FunctionType functionType, Function<String, Result> function) {
        this(route, method, functionType, null, function, null);
    }

    public Route(String route, HttpMethod method, FunctionType functionType, BiFunction<String,String,Result> function) {
        this(route, method, functionType, null, null, function);
    }
}
