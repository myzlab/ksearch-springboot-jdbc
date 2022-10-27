package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFunction {

    public static KAliasedField<?> as(
        final KField kField,
        final String alias
    ) {
        return new KAliasedField(kField.sb, alias);
    }
    
    public static KField avg(
        final KField kField
    ) {
        final KField avgKField = new KField(kField.sb);
        
        avgKField.sb.insert(0, "AVG(").append(")");
        
        return avgKField;
    }
    
    public static KField cast(
        final KField kField,
        final KDataType kDataType    
    ) {
        final KField castKField = new KField(kField.sb);
        
        castKField.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castKField;
    }
    
    public static KField coalesce(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'COALESCE' method requires at least two KFields");
        }
        
        final KField coalesceKField = new KField();
        
        boolean first = true;
        
        coalesceKField.sb.append("COALESCE(");
        
        for (final KField kField : kFields) {
            if (kField == null) {
                continue;
            }
            
            if (!first) {
                coalesceKField.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KValField) {
                coalesceKField.sb.append("'").append(kField.sb).append("'");
                
                continue;
            }
            
            coalesceKField.sb.append(kField.sb);
        }
        
        coalesceKField.sb.append(")");
        
        return coalesceKField;
    }
    
    public static KField count() {
        return new KField("COUNT(*)");
    }
    
    public static KField concat(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT' method requires at least two KFields");
        }
        
        final KField concatKField = new KField();
        
        boolean first = true;
        
        for (final KField kField : kFields) {
            if (kField == null) {
                continue;
            }
            
            if (!first) {
                concatKField.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KValField) {
                final boolean isNumber = ((KValField) kField).isNumber;
                
                if (!isNumber) {
                    concatKField.sb.append("'");
                }
                
                concatKField.sb.append(kField.sb);
                
                if (!isNumber) {
                    concatKField.sb.append("'");
                }
                
                continue;
            }
            
            concatKField.sb.append(kField.sb);
        }
        
        return concatKField;
    }
    
    public static KValField val(
        final String val
    ) {
        return new KValField(val);
    }
    
    public static KValField val(
        final Number val
    ) {
        return new KValField(val);
    }
    
    public static KValField isolate(
        final KValField kValField
    ) {
        final KValField isolateKValField = kValField.cloneMe();
        
        isolateKValField.sb.insert(0, "(").append(")");
        
        return isolateKValField;
    }
    
    public static KField isolate(
        final KField kField
    ) {
        final KField isolateKField = kField.cloneMe();
        
        isolateKField.sb.insert(0, "(").append(")");
        
        return isolateKField;
    }
    
    public static KField getJsonArray(
        final KField kField,
        final int index
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonArrayAsText(
        final KField kField,
        final int index
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonObject(
        final KField kField,
        final String name
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAsText(
        final KField kField,
        final String name
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPath(
        final KField kField,
        final String path
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>'{").append(path).append("}'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPathAsText(
        final KField kField,
        final String path
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>>'{").append(path).append("}'");
        
        return jsonKField;
    }
}
/*//#>
 //Get JSON object at specified path--#>
*/