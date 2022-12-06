package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.SqlTypeValue;

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
        
        if (o instanceof Collection) {
            for (final Object o_ : (Collection) o) {
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
    }
    
    protected static void assertNotNullNotEmpty(
        final Object o,
        final String name
    ) {
        assertNotNullNotEmpty(o, name, true);
    }
    
    protected static void assertNotNullNotEmpty(
        final Object o,
        final String name,
        final boolean canContainNullvalues
    ) {
        if (o == null) {
            throw KExceptionHelper.internalServerError("The '" + name + "' param is required"); 
        }
        
        if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                throw KExceptionHelper.internalServerError("The '" + name + "' param cannot be empty");
            }
            
            for (final Object o_ : (Object[]) o) {
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
        
        if (o instanceof Collection) {
            if (((Collection) o).isEmpty()) {
                throw KExceptionHelper.internalServerError("The '" + name + "' param cannot be empty");
            }
            
            for (final Object o_ : (Collection) o) {
                if (canContainNullvalues) {
                    continue;
                }
                
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
    }
    
}
