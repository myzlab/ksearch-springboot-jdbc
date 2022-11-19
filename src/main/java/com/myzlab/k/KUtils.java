package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;
import java.util.regex.Pattern;

public class KUtils {

    protected static String reverseParams(
        final KBaseColumn kBaseColumn
    ) {
        return reverseParams(kBaseColumn.sb.toString(), kBaseColumn.params);
    }
    
//    protected static String reverseParams(
//        final KColumn kColumn
//    ) {
//        return reverseParams(kColumn.sb.toString(), kColumn.params);
//    }
//    
//    protected static String reverseParams(
//        final KValNumberField kValNumberField
//    ) {
//        return reverseParams(kValNumberField.sb.toString(), kValNumberField.params);
//    }
    
    private static String reverseParams(
        final String value,
        final List<Object> params
    ) {
        String newValue = value;
        
        for (final Object o : params) {
            if (o == null) {
                throw KExceptionHelper.internalServerError("'o' is required");
            }

            final String quote = o instanceof String ? "'" : "";

            newValue = newValue.replaceFirst(Pattern.quote("?"), quote + o.toString() + quote);
        }

        return newValue;
    }
    
    protected static void assertNotNull(
        final Object o,
        final String name
    ) {
        if (o == null) {
            throw KExceptionHelper.internalServerError("The '" + name + "' param is required"); 
        }
        
        if (o instanceof Object[]) {
            for (final Object o_ : (Object[]) o) {
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
    }
}
