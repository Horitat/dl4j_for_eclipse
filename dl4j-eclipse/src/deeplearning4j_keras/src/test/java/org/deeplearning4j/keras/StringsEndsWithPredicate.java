package deeplearning4j_keras.src.test.java.org.deeplearning4j.keras;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;


public class StringsEndsWithPredicate implements Predicate<String> {

    private final String anEnd;

    public StringsEndsWithPredicate(String anEnd) {
        this.anEnd = anEnd;
    }

    @Override
    public boolean apply(@Nullable String s) {
    	//check Null. self made
    	if(s == null){
    		/*if(anEnd == null){
    			return true;
    		}*/
    		return false;
    	} // so far

        return s.endsWith(anEnd);
    }

    //self made
    @Override
    public boolean equals(@Nullable Object obj) {
      return super.equals(obj);
    }
    //because apply method competing equals of super class

    public static Predicate<String> endsWith(String anEnd) {
        return new StringsEndsWithPredicate(anEnd);
    }
}
