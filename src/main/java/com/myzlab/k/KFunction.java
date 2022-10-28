package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFunction {

    public static KAliasedField<?> as(
        final KField kField,
        final String alias
    ) {
        return new KAliasedField(kField.sb, alias);
    }
    
    public static KField abs(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "ABS");
    }
    
    public static KField acos(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "ACOS");
    }
    
    private static KField applyOneParameterFunction(
        final KField kField,
        final String functionName,
        final boolean valNumberIsValid,
        final boolean valStringIsValid
    ) {
        final boolean kFieldIsVal = kField instanceof KValField;
        final KField functionKField = new KField(kField.sb);
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            final boolean isText = ((KValField) kField).isText;
            
            if (isText && !valStringIsValid) {
                throw KExceptionHelper.internalServerError("'" + functionName + "' function is not available with a 'val' of string type");
            }
            
            if (isNumber && !valNumberIsValid) {
                throw KExceptionHelper.internalServerError("'" + functionName + "' function is not available with a 'val' of number type");
            }
            
            if (isText) {
                functionKField.sb.insert(0, "'");
            }
        }
        
        functionKField.sb.insert(0, "(").insert(0, functionName);
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (isText) {
                functionKField.sb.append("'");
            }
        }
        
        functionKField.sb.append(")");
        
        return functionKField;
    }
    
    private static KField applyOneParameterFunctionWithValNumberValid(
        final KField kField,
        final String functionName
    ) {
        return applyOneParameterFunction(kField, functionName, true, false);
    }
    
    private static KField applyTwoParameterFunctionWithValNumberValid(
        final KField kField1,
        final KField kField2,
        final String functionName
    ) {
        final KField functionKField = new KField();
        
        final boolean kField1IsVal = kField1 instanceof KValField;
        final boolean kField2IsVal = kField2 instanceof KValField;
        
        functionKField.sb.append(functionName).append("(");
        
        if (kField1IsVal) {
            final boolean isText = ((KValField) kField1).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'" + functionName + "' function is not available with a 'val' of string type");
            }
        }
        
        if (kField2IsVal) {
            final boolean isText = ((KValField) kField2).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'" + functionName + "' function is not available with a 'val' of string type");
            }
        }
        
        functionKField.sb.append(kField1.sb).append(", ").append(kField2.sb).append(")");
        
        return functionKField;
    }
    
    public static KField asin(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "ASIN");
    }
    
    public static KField atan(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "ATAN");
    }
    
    public static KField atan2(
        final KField kField1,
        final KField kField2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kField1, kField2, "ATAN2");
    }
    
    public static KField avg(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "AVG");
    }
    
    public static KField cast(
        final KField kField,
        final KDataType kDataType    
    ) {
        final KField castKField = new KField(kField.sb);
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (!isNumber) {
                castKField.sb.insert(0, "'").append("'");
            }
        }
        
        castKField.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castKField;
    }
    
    public static KField ceil(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "CEIL");
    }
    
    public static KField coalesce(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'COALESCE' function requires at least two KFields");
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
    
    public static KField cos(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "COS");
    }
    
    public static KField cosh(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "COSH");
    }
    
    public static KField cot(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "COT");
    }
    
    public static KField count() {
        return new KField("COUNT(*)");
    }
    
    public static KField concat(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT' function requires at least two KFields");
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
    
    public static KField encode(
        final KField kField,
        final KFormat kFormat
    ) {
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("'ENCODE' function is not available with a 'val' of number type");
            }
        }
        
        final KField encodeKField = kField.cloneMe();
        
        if (kFieldIsVal) {
            encodeKField.sb.insert(0, "'").append("'");
        }
        
        encodeKField.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKField;
    }
    
    public static KField exp(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "EXP");
    }
    
    public static KField decode(
        final KField kField,
        final KFormat kFormat
    ) {
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("'ENCODE' function is not available with a 'val' of number type");
            }
        }
        
        final KField encodeKField = kField.cloneMe();
        
        if (kFieldIsVal) {
            encodeKField.sb.insert(0, "'").append("'");
        }
        
        encodeKField.sb.insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKField;
    }
    
    public static KField floor(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "FLOOR");
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
    
    public static KField greatest(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'GREATEST' function requires at least two KFields");
        }
        
        final KField greatestKField = new KField();
        
        boolean first = true;
        
        greatestKField.sb.append("GREATEST(");
        
        for (final KField kField : kFields) {
            if (kField == null) {
                continue;
            }
            
            if (!first) {
                greatestKField.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KValField) {
                final boolean isText = ((KValField) kField).isText;
            
                if (isText) {
                    greatestKField.sb.append("'");
                }
            }
            
            greatestKField.sb.append(kField.sb);
            
            if (kField instanceof KValField) {
                final boolean isText = ((KValField) kField).isText;
            
                if (isText) {
                    greatestKField.sb.append("'");
                }
            }
            
        }
        
        greatestKField.sb.append(")");
        
        return greatestKField;
    }
    
    public static KField least(
        final KField... kFields
    ) {
        if (kFields.length < 2) {
            throw KExceptionHelper.internalServerError("'LEAST' function requires at least two KFields");
        }
        
        final KField greatestKField = new KField();
        
        boolean first = true;
        
        greatestKField.sb.append("LEAST(");
        
        for (final KField kField : kFields) {
            if (kField == null) {
                continue;
            }
            
            if (!first) {
                greatestKField.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KValField) {
                final boolean isText = ((KValField) kField).isText;
            
                if (isText) {
                    greatestKField.sb.append("'");
                }
            }
            
            greatestKField.sb.append(kField.sb);
            
            if (kField instanceof KValField) {
                final boolean isText = ((KValField) kField).isText;
            
                if (isText) {
                    greatestKField.sb.append("'");
                }
            }
            
        }
        
        greatestKField.sb.append(")");
        
        return greatestKField;
    }
    
    public static KField left(
        final KField kField,
        final int n
    ) {
        final KField leftKField = new KField();
        
        leftKField.sb.append("LEFT(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("'LEFT' function is not available with a 'val' of number type");
            }
            
            leftKField.sb.append("'");
        }
        
        leftKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            leftKField.sb.append("'");
        }
        
        leftKField.sb.append(", ").append(n).append(")");
        
        return leftKField;
    }
    
    public static KField ln(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "LN");
    }
    
    public static KField log(
        final KField kField1,
        final KField kField2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kField1, kField2, "LOG");
    }
    
    public static KField log10(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "LOG10");
    }
    
    public static KField now() {
        return new KField("NOW()");
    }
    
    public static KField nullif(
        final KField kField1,
        final KField kField2
    ) {
        
        final KField nullifKField = new KField();
        
        final boolean kField1IsVal = kField1 instanceof KValField;
        final boolean kField2IsVal = kField2 instanceof KValField;
        
        nullifKField.sb.append("NULLIF(");
        
        if (kField1IsVal) {
            final boolean isNumber = ((KValField) kField1).isNumber;
            
            if (!isNumber) {
                nullifKField.sb.append("'");
            }
        }
        
        nullifKField.sb.append(kField1.sb);
        
        if (kField1IsVal) {
            final boolean isNumber = ((KValField) kField1).isNumber;
            
            if (!isNumber) {
                nullifKField.sb.append("'");
            }
        }
        
        nullifKField.sb.append(", ");
        
        if (kField2IsVal) {
            final boolean isNumber = ((KValField) kField2).isNumber;
            
            if (!isNumber) {
                nullifKField.sb.append("'");
            }
        }
        
        nullifKField.sb.append(kField2.sb);
        
        if (kField2IsVal) {
            final boolean isNumber = ((KValField) kField2).isNumber;
            
            if (!isNumber) {
                nullifKField.sb.append("'");
            }
        }
        
        nullifKField.sb.append(")");
        
        return nullifKField;
    }
    
    public static KField pi() {
        return new KField("PI()");
    }
    
    public static KField power(
        final KField kField1,
        final KField kField2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kField1, kField2, "POWER");
    }
    
    public static KField random() {
        return new KField("RANDOM()");
    }
    
    public static KField right(
        final KField kField,
        final int n
    ) {
        final KField leftKField = new KField();
        
        leftKField.sb.append("RIGHT(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("'RIGHT' function is not available with a 'val' of number type");
            }
            
            leftKField.sb.append("'");
        }
        
        leftKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            leftKField.sb.append("'");
        }
        
        leftKField.sb.append(", ").append(n).append(")");
        
        return leftKField;
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
}