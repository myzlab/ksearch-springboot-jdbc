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
    
    public static KField add(
        final KField kField1,
        final KField kField2
    ) {
        return doOperation(kField1, kField2, "+");
    }
    
    public static KField add(
        final KField kField,
        final Number number
    ) {
        return doOperation(kField, new KField(new StringBuilder(number.toString())), "+");
    }
    
    public static KField add(
        final Number number,
        final KField kField
    ) {
        return doOperation(new KField(new StringBuilder(number.toString())), kField, "+");
    }
    
    public static KValField add(
        final KValField kValField,
        final Number number
    ) {
        return doOperation(kValField, new KValField(number), "+");
    }
    
    public static KValField add(
        final Number number,
        final KValField kValField
    ) {
        return doOperation(new KValField(number), kValField, "+");
    }
    
    public static KValField add(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return doOperation(kValField1, kValField2, "+");
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
    
    public static KField cbrt(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "CBRT");
    }
    
    public static KField ceil(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "CEIL");
    }
    
    public static KField ceiling(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "CEILING");
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
    
    public static KField degrees(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "DEGREES");
    }
    
    public static KField div(
        final KField kField1,
        final KField kField2
    ) {
        return doOperation(kField1, kField2, "/");
    }
    
    public static KField div(
        final KField kField,
        final Number number
    ) {
        return doOperation(kField, new KField(new StringBuilder(number.toString())), "/");
    }
    
    public static KField div(
        final Number number,
        final KField kField
    ) {
        return doOperation(new KField(new StringBuilder(number.toString())), kField, "/");
    }
    
    public static KValField div(
        final KValField kValField,
        final Number number
    ) {
        return doOperation(kValField, new KValField(number), "/");
    }
    
    public static KValField div(
        final Number number,
        final KValField kValField
    ) {
        return doOperation(new KValField(number), kValField, "/");
    }
    
    public static KValField div(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return doOperation(kValField1, kValField2, "/");
    }
    
    private static KField doOperation(
        final KField kField1,
        final KField kField2,
        final String operation
    ) {
        final KField operationKField = new KField(kField1.sb);
        
        operationKField.sb.append(" ").append(operation).append(" ").append(kField2.sb);
        
        return operationKField;
    }
    
    private static KValField doOperation(
        final KValField kValField1,
        final KValField kValField2,
        final String operation
    ) {
        if (!kValField1.isNumber) {
            throw KExceptionHelper.internalServerError("The '" + operation + "' method only can be used in 'val' of number type. Current value: [" + kValField1.sb.toString() + "]");
        }
        
        if (!kValField2.isNumber) {
            throw KExceptionHelper.internalServerError("The '" + operation + "' method only can be used in 'val' of number type. Current value: [" + kValField2.sb.toString() + "]");
        }
        
        final KValField newKValField = new KValField(kValField1.sb, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.append(" ").append(operation).append(" ");
        
        final boolean kValFieldCasteableToANumber = isCasteableToANumber(kValField2.sb.toString());
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append("(");
        }
        
        newKValField.sb.append(kValField2.sb);
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append(")");
        }
        
        return newKValField;
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
    
    public static KField floor(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "FLOOR");
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
    
    private static boolean isCasteableToANumber(
        final String text
    ) {
        try {
            Double.parseDouble(text);
            
            return true;
        } catch (Exception e) {
            return false;
        }
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
    
    public static KField mod(
        final KField kField1,
        final KField kField2
    ) {
        return doOperation(kField1, kField2, "%");
    }
    
    public static KField mod(
        final KField kField,
        final Number number
    ) {
        return doOperation(kField, new KField(new StringBuilder(number.toString())), "%");
    }
    
    public static KField mod(
        final Number number,
        final KField kField
    ) {
        return doOperation(new KField(new StringBuilder(number.toString())), kField, "%");
    }
    
    public static KValField mod(
        final KValField kValField,
        final Number number
    ) {
        return doOperation(kValField, new KValField(number), "%");
    }
    
    public static KValField mod(
        final Number number,
        final KValField kValField
    ) {
        return doOperation(new KValField(number), kValField, "%");
    }
    
    public static KValField mod(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return doOperation(kValField1, kValField2, "%");
    }
    
    public static KField mul(
        final KField kField1,
        final KField kField2
    ) {
        return doOperation(kField1, kField2, "*");
    }
    
    public static KField mul(
        final KField kField,
        final Number number
    ) {
        return doOperation(kField, new KField(new StringBuilder(number.toString())), "*");
    }
    
    public static KField mul(
        final Number number,
        final KField kField
    ) {
        return doOperation(new KField(new StringBuilder(number.toString())), kField, "*");
    }
    
    public static KValField mul(
        final KValField kValField,
        final Number number
    ) {
        return doOperation(kValField, new KValField(number), "*");
    }
    
    public static KValField mul(
        final Number number,
        final KValField kValField
    ) {
        return doOperation(new KValField(number), kValField, "*");
    }
    
    public static KValField mul(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return doOperation(kValField1, kValField2, "*");
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
    
    public static KField round(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "ROUND");
    }
    
    public static KField round(
        final KField kField1,
        final KField kField2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kField1, kField2, "ROUND");
    }
    
    public static KField sign(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "SIGN");
    }
    
    public static KField sin(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "SIN");
    }
    
    public static KField sinh(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "SINH");
    }
    
    public static KField sqrt(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "SQRT");
    }
    
    public static KField tan(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "TAN");
    }
    
    public static KField tanh(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "TANH");
    }
    
    public static KField radians(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "RADIANS");
    }
    
    public static KField sub(
        final KField kField1,
        final KField kField2
    ) {
        return doOperation(kField1, kField2, "-");
    }
    
    public static KField sub(
        final KField kField,
        final Number number
    ) {
        return doOperation(kField, new KField(new StringBuilder(number.toString())), "-");
    }
    
    public static KField sub(
        final Number number,
        final KField kField
    ) {
        return doOperation(new KField(new StringBuilder(number.toString())), kField, "-");
    }
    
    public static KValField sub(
        final KValField kValField,
        final Number number
    ) {
        return doOperation(kValField, new KValField(number), "-");
    }
    
    public static KValField sub(
        final Number number,
        final KValField kValField
    ) {
        return doOperation(new KValField(number), kValField, "-");
    }
    
    public static KValField sub(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return doOperation(kValField1, kValField2, "-");
    }
    
    public static KField trunc(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "TRUNC");
    }
    
    public static KField widthBucket(
        final KField op,
        final KField b1,
        final KField b2,
        final KField count
    ) {
        final KField widthBucketKField = new KField();
        
        final boolean opIsVal = op instanceof KValField;
        final boolean b1IsVal = b1 instanceof KValField;
        final boolean b2IsVal = b2 instanceof KValField;
        final boolean countIsVal = count instanceof KValField;
        
        widthBucketKField.sb.append("WIDTH_BUCKET(");
        
        if (opIsVal) {
            final boolean isText = ((KValField) op).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'WIDTH_BUCKET' function is not available with a 'val' of string type");
            }
        }
        
        if (b1IsVal) {
            final boolean isText = ((KValField) b1).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'WIDTH_BUCKET' function is not available with a 'val' of string type");
            }
        }
        
        if (b2IsVal) {
            final boolean isText = ((KValField) b2).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'WIDTH_BUCKET' function is not available with a 'val' of string type");
            }
        }
        
        if (countIsVal) {
            final boolean isText = ((KValField) count).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("'WIDTH_BUCKET' function is not available with a 'val' of string type");
            }
        }
        
        widthBucketKField.sb.append(op.sb).append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count.sb).append(")");
        
        return widthBucketKField;
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