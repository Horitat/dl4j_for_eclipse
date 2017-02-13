package deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.play.staticroutes;

//import org.deeplearning4j.ui.i18n.I18NProvider;
import static play.mvc.Results.*;

import java.util.function.Function;

import play.mvc.Result;
import deeplearning4j_ui_parent.deeplearning4j_play.src.main.java.org.deeplearning4j.ui.i18n.I18NProvider;

/**
 * Route for global internationalization setting
 *
 * @author Alex Black
 */
public class I18NRoute implements Function<String,Result> {
    @Override
    public Result apply(String s) {
        I18NProvider.getInstance().setDefaultLanguage(s);
        return ok();
    }
}
