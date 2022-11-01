package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFunction {

    public static KAliasedField<?> as(
        final KField kField,
        final String alias
    ) {
        assertNotNull(kField, "kField");
                
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
        return applyBinaryOperator(kField1, kField2, "+");
    }
    
    public static KField add(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "+");
    }
    
    public static KField add(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "+");
    }
    
    public static KValField add(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "+");
    }
    
    public static KValField add(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "+");
    }
    
    public static KValField add(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "+");
    }
    
    private static KField applyOneParameterFunction(
        final KField kField,
        final String functionName,
        final boolean valNumberIsValid,
        final boolean valStringIsValid
    ) {
        assertNotNull(kField, "kField");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        final KField functionKField = new KField(kField.sb);
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            final boolean isText = ((KValField) kField).isText;
            
            if (isText && !valStringIsValid) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kField));
            }
            
            if (isNumber && !valNumberIsValid) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType(functionName, kField));
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
    
    private static KField applyOneParameterFunctionWithValStringValid(
        final KField kField,
        final String functionName
    ) {
        assertNotNull(kField, "kField");
        
        return applyOneParameterFunction(kField, functionName, false, true);
    }
    
    private static KField applyOneParameterFunctionWithValNumberValid(
        final KField kField,
        final String functionName
    ) {
        assertNotNull(kField, "kField");
        
        return applyOneParameterFunction(kField, functionName, true, false);
    }
    
    private static KField applyTwoParameterFunctionWithValNumberValid(
        final KField kField1,
        final KField kField2,
        final String functionName
    ) {
        
        assertNotNull(kField1, "kField1");
        assertNotNull(kField2, "kField2");
        
        final KField functionKField = new KField();
        
        final boolean kField1IsVal = kField1 instanceof KValField;
        final boolean kField2IsVal = kField2 instanceof KValField;
        
        functionKField.sb.append(functionName).append("(");
        
        if (kField1IsVal) {
            final boolean isNumber = ((KValField) kField1).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kField1));
            }
        }
        
        if (kField2IsVal) {
            final boolean isNumber = ((KValField) kField2).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kField2));
            }
        }
        
        functionKField.sb.append(kField1.sb).append(", ").append(kField2.sb).append(")");
        
        return functionKField;
    }
    
    public static KField ascii(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "ASCII");
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
    
    public static KField bitAnd(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "&");
    }
    
    public static KField bitAnd(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "&");
    }
    
    public static KField bitAnd(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "&");
    }
    
    public static KValField bitAnd(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "&");
    }
    
    public static KValField bitAnd(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "&");
    }
    
    public static KValField bitAnd(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "&");
    }
    
    public static KField bitNot(
        final KField kField
    ) {
        return applyUnaryOperator(kField, "~", false);
    }
    
    public static KValField bitNot(
        final Number number
    ) {
        return applyUnaryOperatorWithValNumberValid(number, "~", false);
    }
    
    public static KValField bitNot(
        final KValField kValField
    ) {
        return applyUnaryOperatorWithValNumberValid(kValField, "~", false);
    }
    
    public static KField bitOr(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "|");
    }
    
    public static KField bitOr(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "|");
    }
    
    public static KField bitOr(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "|");
    }
    
    public static KValField bitOr(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "|");
    }
    
    public static KValField bitOr(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "|");
    }
    
    public static KValField bitOr(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "|");
    }
    
    public static KField bitShiftLeft(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "<<");
    }
    
    public static KField bitShiftLeft(
        final KField kField,
        final int n
    ) {
        assertNotNull(kField, "kField");
        
        return applyBinaryOperator(kField, new KField(new StringBuilder(n)), "<<");
    }
    
    public static KValField bitShiftLeft(
        final KValField kValField,
        final int n
    ) {
        assertNotNull(kValField, "kValField");
        
        return applyBinaryOperatorWithValNumberValid(kValField, new KValField(n), "<<");
    }
    
    public static KField bitShiftRight(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, ">>");
    }
    
    public static KField bitShiftRight(
        final KField kField,
        final int n
    ) {
        assertNotNull(kField, "kField");
        
        return applyBinaryOperator(kField, new KField(new StringBuilder(n)), ">>");
    }
    
    public static KValField bitShiftRight(
        final KValField kValField,
        final int n
    ) {
        assertNotNull(kValField, "kValField");
        
        return applyBinaryOperatorWithValNumberValid(kValField, new KValField(n), ">>");
    }
    
    public static KField bitXor(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "#");
    }
    
    public static KField bitXor(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "#");
    }
    
    public static KField bitXor(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "#");
    }
    
    public static KValField bitXor(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "#");
    }
    
    public static KValField bitXor(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "#");
    }
    
    public static KValField bitXor(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "#");
    }
    
    public static KField cast(
        final KField kField,
        final KDataType kDataType    
    ) {
        
        assertNotNull(kField, "kField");
        assertNotNull(kDataType, "kDataType");
        
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
    
    public static KField chr(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "CHR");
    }
    
    public static KField coalesce(
        final KField... kFields
    ) {
        
        assertNotNull(kFields, "kFields");
        
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
        
        assertNotNull(kFields, "kFields");
        
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
    
    public static KField datePart(
        final KField kField,
        final KExtractField kExtractField
    ) {
        
        assertNotNull(kField, "kField");
        assertNotNull(kExtractField, "kExtractField");
        
        if (kField instanceof KValField) {
            final boolean isText = ((KValField) kField).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: ['" + kField.sb.toString() + "']" );
            }
            
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: [" + kField.sb.toString() + "]" );
            }
            
            throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column.");
        }
        
        final KField extractKField = new KField(kField.sb);
        
        extractKField.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_PART('").append(")");
        
        return extractKField;
    }
    
    public static KField decode(
        final KField kField,
        final KFormat kFormat
    ) {
        
        assertNotNull(kField, "kField");
        assertNotNull(kFormat, "kFormat");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("DECODE", kField));
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
        return applyBinaryOperator(kField1, kField2, "/");
    }
    
    public static KField div(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "/");
    }
    
    public static KField div(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "/");
    }
    
    public static KValField div(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "/");
    }
    
    public static KValField div(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "/");
    }
    
    public static KValField div(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "/");
    }
    
    private static KField applyBinaryOperator(
        final Number number,
        final KField kField,
        final String operator
    ) {
        assertNotNull(number, "number");
        assertNotNull(kField, "kField");
        
        return applyBinaryOperator(new KField(new StringBuilder(number.toString())), kField, operator);
    }
    
    private static KField applyBinaryOperator(
        final KField kField,
        final Number number,
        final String operator
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(number, "number");
        
        return applyBinaryOperator(kField, new KField(new StringBuilder(number.toString())), operator);
    }
    
    private static KField applyBinaryOperator(
        final KField kField1,
        final KField kField2,
        final String operator
    ) {
        assertNotNull(kField1, "kField1");
        assertNotNull(kField2, "kField2");
        
        final KField operationKField = new KField(kField1.sb);
        
        operationKField.sb.append(" ").append(operator).append(" ").append(kField2.sb);
        
        return operationKField;
    }
    
    private static KValField applyBinaryOperatorWithValNumberValid(
        final KValField kValField,
        final Number number,
        final String operator
    ) {
        assertNotNull(kValField, "kValField");
        assertNotNull(number, "number");
        
        return applyBinaryOperatorWithValNumberValid(kValField, new KValField(number), operator);
    }
    
    private static KValField applyBinaryOperatorWithValNumberValid(
        final Number number,
        final KValField kValField,
        final String operator
    ) {
        assertNotNull(number, "number");
        assertNotNull(kValField, "kValField");
        
        return applyBinaryOperatorWithValNumberValid(new KValField(number), kValField, operator);
    }
    
    private static KValField applyBinaryOperatorWithValNumberValid(
        final KValField kValField1,
        final KValField kValField2,
        final String operator
    ) {
        
        assertNotNull(kValField1, "kValField1");
        assertNotNull(kValField2, "kValField2");
        
        if (!kValField1.isNumber) {
            throw KExceptionHelper.internalServerError(getErrorMessageOperatorNumberType(operator, kValField1));
        }
        
        if (!kValField2.isNumber) {
            throw KExceptionHelper.internalServerError(getErrorMessageOperatorNumberType(operator, kValField2));
        }
        
        final KValField newKValField = new KValField(kValField1.sb, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.append(" ").append(operator).append(" ");
        
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
    
    private static KField applyUnaryOperator(
        final KField kField,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kField, "kField");
        
        final KField operationKField = new KField();
        
        operationKField.sb.append(addToRightSide ? "" : operator).append(kField.sb).append(addToRightSide ? operator : "");
        
        return operationKField;
    }
    
    private static KValField applyUnaryOperatorWithValNumberValid(
        final Number number,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(number, "number");
        
        return applyUnaryOperatorWithValNumberValid(new KValField(number), operator, addToRightSide);
    }
    
    private static KValField applyUnaryOperatorWithValNumberValid(
        final KValField kValField,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kValField, "kValField");
        
        if (!kValField.isNumber) {
            throw KExceptionHelper.internalServerError(getErrorMessageOperatorNumberType(operator, kValField));
        }
        
        final KValField newKValField = new KValField(kValField.sb, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        
        return newKValField;
    }
    
    public static KField encode(
        final KField kField,
        final KFormat kFormat
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(kFormat, "kFormat");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("ENCODE", kField));
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
    
    public static KField extract(
        final KField kField,
        final KExtractField kExtractField
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(kExtractField, "kExtractField");
        
        if (kField instanceof KValField) {
            final boolean isText = ((KValField) kField).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: ['" + kField.sb.toString() + "']" );
            }
            
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: [" + kField.sb.toString() + "]" );
            }
            
            throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column.");
        }
        
        final KField extractKField = new KField(kField.sb);
        
        extractKField.sb.insert(0, " FROM ").insert(0, kExtractField.toSql()).insert(0, "EXTRACT(").append(")");
        
        return extractKField;
    }
    
    public static KField floor(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "FLOOR");
    }
    
    public static KField genRandomUuid() {
        return new KField("GEN_RANDOM_UUID()");
    }
    
    private static KField genericTrim(
        final String trimFunctionName,
        final KField kField,
        final String characters
    ) {
        assertNotNull(kField, "kField");
        
        final KField lpadKField = new KField();
        
        lpadKField.sb.append(trimFunctionName).append("(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType(trimFunctionName, kField));
            }
            
            lpadKField.sb.append("'");
        }
        
        lpadKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            lpadKField.sb.append("'");
        }
                
        if (characters != null) {
            lpadKField.sb.append(", '").append(characters).append("'");
        }
                
        lpadKField.sb.append(")");
        
        return lpadKField;
    }
    
    private static String getErrorMessageFunctionTextType(
        final String functionName,
        final KField kField
    ) {
        return getGenericErrorMessage(functionName, "function", "text", kField);
    }
    
    private static String getErrorMessageFunctionNumberType(
        final String functionName,
        final KField kField
    ) {
        return getGenericErrorMessage(functionName, "function", "number", kField);
    }
    
    private static String getErrorMessageOperatorTextType(
        final String operatorName,
        final KField kField
    ) {
        return getGenericErrorMessage(operatorName, "operator", "text", kField);
    }
    
    private static String getErrorMessageOperatorNumberType(
        final String operatorName,
        final KField kField
    ) {
        return getGenericErrorMessage(operatorName, "operator", "number", kField);
    }
    
    private static String getGenericErrorMessage(
        final String name,
        final String entity,
        final String type,
        final KField kField
    ) {
        return "The '" + name + "' " + entity + " only can be used with a column or with a 'val' of " + type + " type. Current value: [" 
            + (type.equals("number") ? "'" : "") 
            + kField.sb.toString() 
            + (type.equals("number") ? "'" : "") 
            + "]";
    }
    
    public static KField getJsonArray(
        final KField kField,
        final int index
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonArrayAsText(
        final KField kField,
        final int index
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonObject(
        final KField kField,
        final String name
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAsText(
        final KField kField,
        final String name
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPath(
        final KField kField,
        final String path
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>'{").append(path).append("}'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPathAsText(
        final KField kField,
        final String path
    ) {
        assertNotNull(kField, "kField");
        
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>>'{").append(path).append("}'");
        
        return jsonKField;
    }
    
    public static KField greatest(
        final KField... kFields
    ) {
        assertNotNull(kFields, "kFields");
        
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
        assertNotNull(kValField, "kValField");
        
        final KValField isolateKValField = kValField.cloneMe();
        
        isolateKValField.sb.insert(0, "(").append(")");
        
        return isolateKValField;
    }
    
    public static KField isolate(
        final KField kField
    ) {
        assertNotNull(kField, "kField");
        
        final KField isolateKField = kField.cloneMe();
        
        isolateKField.sb.insert(0, "(").append(")");
        
        return isolateKField;
    }
    
    public static KField least(
        final KField... kFields
    ) {
        assertNotNull(kFields, "kFields");
        
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
        assertNotNull(kField, "kField");
        
        final KField leftKField = new KField();
        
        leftKField.sb.append("LEFT(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("LEFT", kField));
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
    
    public static KField length(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "LENGTH");
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
    
    public static KField lower(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "LOWER");
    }
    
    public static KField lpad(
        final KField kField,
        final int n
    ) {
        return lpad(kField, n, null);
    }
    
    public static KField lpad(
        final KField kField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kField, "kField");
        
        final KField lpadKField = new KField();
        
        lpadKField.sb.append("LPAD(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("LPAD", kField));
            }
            
            lpadKField.sb.append("'");
        }
        
        lpadKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            lpadKField.sb.append("'");
        }
        
        lpadKField.sb.append(", ").append(n);
                
        if (fillText != null) {
            lpadKField.sb.append(", '").append(fillText).append("'");
        }
                
        lpadKField.sb.append(")");
        
        return lpadKField;
    }
    
    public static KField ltrim(
        final KField kField
    ) {
        return ltrim(kField, null);
    }
    
    public static KField ltrim(
        final KField kField,
        final String characters
    ) {
        return genericTrim("LTRIM", kField, characters);
    }
    
    public static KField md5(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "MD5");
    }
    
    public static KField mod(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "%");
    }
    
    public static KField mod(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "%");
    }
    
    public static KField mod(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "%");
    }
    
    public static KValField mod(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "%");
    }
    
    public static KValField mod(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "%");
    }
    
    public static KValField mod(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "%");
    }
    
    public static KField mul(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "*");
    }
    
    public static KField mul(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "*");
    }
    
    public static KField mul(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "*");
    }
    
    public static KValField mul(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "*");
    }
    
    public static KValField mul(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "*");
    }
    
    public static KValField mul(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "*");
    }
    
    public static KField now() {
        return new KField("NOW()");
    }
    
    public static KField nullif(
        final KField kField1,
        final KField kField2
    ) {
        
        assertNotNull(kField1, "kField1");
        assertNotNull(kField2, "kField2");
        
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
    
    public static KField overlay(
        final KField kField,
        final String value,
        final Integer from
    ) {
        return overlay(kField, value, from, null);
    }
    
    public static KField overlay(
        final KField kField,
        final String value,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(from, "from");
        assertNotNull(value, "value");
        
        final KField overlayKField = new KField();
        
        overlayKField.sb.append("OVERLAY(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("OVERLAY", kField));
            }
            
            overlayKField.sb.append("'");
        }
        
        overlayKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            overlayKField.sb.append("'");
        }
        
        overlayKField.sb.append(" PLACING '").append(value).append("'").append(" from ").append(from);
        
        if (for_ != null) {
            overlayKField.sb.append(" for ").append(for_);
        }
                
        overlayKField.sb.append(")");
        
        return overlayKField;
    }
    
    public static KField pi() {
        return new KField("PI()");
    }
    
    public static KField position(
        final KField kField,
        final String valueToLocate
    ) {
        
        assertNotNull(kField, "kField");
        assertNotNull(valueToLocate, "valueToLocate");
        
        final KField positionKField = new KField();
        
        positionKField.sb.append("POSITION('").append(valueToLocate).append("'").append(" in ");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("POSITION", kField));
            }
            
            positionKField.sb.append("'");
        }
        
        positionKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            positionKField.sb.append("'");
        }
        
        positionKField.sb.append(")");
        
        return positionKField;
    }
    
    public static KField power(
        final KField kField1,
        final KField kField2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kField1, kField2, "POWER");
    }
    
    public static KField radians(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "RADIANS");
    }
    
    public static KField random() {
        return new KField("RANDOM()");
    }
    
    public static KField repeat(
        final KField kField,
        final int n
    ) {
        assertNotNull(kField, "kField");
        
        final KField repeatKField = new KField();
        
        repeatKField.sb.append("REPEAT(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REPEAT", kField));
            }
            
            repeatKField.sb.append("'");
        }
        
        repeatKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            repeatKField.sb.append("'");
        }
        
        repeatKField.sb.append(", ").append(n).append(")");
        
        return repeatKField;
    }
    
    public static KField regexpReplace(
        final KField kField,
        final String pattern,
        final String replacement
    ) {
        return regexpReplace(kField, pattern, replacement, null);
    }
    
    public static KField regexpReplace(
        final KField kField,
        final String pattern,
        final String replacement,
        final String flags
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(pattern, "pattern");
        assertNotNull(replacement, "replacement");
        
        final KField regexpReplaceKField = new KField();
        
        regexpReplaceKField.sb.append("REGEXP_REPLACE(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REGEXP_REPLACE", kField));
            }
            
            regexpReplaceKField.sb.append("'");
        }
        
        regexpReplaceKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            regexpReplaceKField.sb.append("'");
        }

        regexpReplaceKField.sb.append(", '").append(pattern).append("', '").append(replacement).append("'");
        
        if (flags != null) {
            regexpReplaceKField.sb.append(", '").append(flags).append("'");
        }
        
        regexpReplaceKField.sb.append(")");
        
        return regexpReplaceKField;
    }
    
    public static KField replace(
        final KField kField,
        final String from,
        final String to
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KField replaceKField = new KField();
        
        replaceKField.sb.append("REPLACE(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REGEXP_REPLACE", kField));
            }
            
            replaceKField.sb.append("'");
        }
        
        replaceKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            replaceKField.sb.append("'");
        }

        replaceKField.sb.append(", '").append(from).append("', '").append(to).append("'").append(")");
        
        return replaceKField;
    }
    
    public static KField reverse(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "REVERSE");
    }
    
    public static KField right(
        final KField kField,
        final int n
    ) {
        assertNotNull(kField, "kField");
        
        final KField leftKField = new KField();
        
        leftKField.sb.append("RIGHT(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("RIGHT", kField));
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
    
    public static KField rpad(
        final KField kField,
        final int n
    ) {
        return rpad(kField, n, null);
    }
    
    public static KField rpad(
        final KField kField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kField, "kField");
        
        final KField rpadKField = new KField();
        
        rpadKField.sb.append("RPAD(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("RPAD", kField));
            }
            
            rpadKField.sb.append("'");
        }
        
        rpadKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            rpadKField.sb.append("'");
        }
        
        rpadKField.sb.append(", ").append(n);
                
        if (fillText != null) {
            rpadKField.sb.append(", '").append(fillText).append("'");
        }
                
        rpadKField.sb.append(")");
        
        return rpadKField;
    }
    
    public static KField rtrim(
        final KField kField
    ) {
        return rtrim(kField, null);
    }
    
    public static KField rtrim(
        final KField kField,
        final String characters
    ) {
        return genericTrim("RTRIM", kField, characters);
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
    
    public static KField splitPart(
        final KField kField,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kField, "kField");
        
        if (delimiter == null) {
            throw KExceptionHelper.internalServerError("'delimiter' is required in 'SPLIT_PART' function");
        }
        
        final KField splitPartKField = new KField();
        
        splitPartKField.sb.append("SPLIT_PART(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SPLIT_PART", kField));
            }
            
            splitPartKField.sb.append("'");
        }
        
        splitPartKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            splitPartKField.sb.append("'");
        }
        
        splitPartKField.sb.append(", '").append(delimiter).append("', ").append(field).append(")");
        
        return splitPartKField;
    }
    
    public static KField sqrt(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "SQRT");
    }
    
    public static KField substring(
        final KField kField,
        final Integer from
    ) {
        return substring(kField, from, null);
    }
    
    public static KField substring(
        final KField kField,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kField, "kField");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least 1 is required");
        }
        
        final KField substringKField = new KField();
        
        substringKField.sb.append("SUBSTRING(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SUBSTRING", kField));
            }
            
            substringKField.sb.append("'");
        }
        
        substringKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            substringKField.sb.append("'");
        }
        
        if (from != null) {
            substringKField.sb.append(" from ").append(from);
        }
        
        if (for_ != null) {
            substringKField.sb.append(" for ").append(for_);
        }
                
        substringKField.sb.append(")");
        
        return substringKField;
    }
    
    public static KField substring(
        final KField kField,
        final String from
    ) {
        return substring(kField, from, null);
    }
    
    public static KField substring(
        final KField kField,
        final String from,
        final String for_
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(from, "from");
        
        final KField substringKField = new KField();
        
        substringKField.sb.append("SUBSTRING(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SUBSTRING", kField));
            }
            
            substringKField.sb.append("'");
        }
        
        substringKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            substringKField.sb.append("'");
        }
        
        substringKField.sb.append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringKField.sb.append(" for '").append(for_).append("'");
        }
        
        substringKField.sb.append(")");
        
        return substringKField;
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
    
    public static KField toHex(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "TO_HEX");
    }
    
    public static KField translate(
        final KField kField,
        final String from,
        final String to
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KField translateKField = new KField();
        
        translateKField.sb.append("TRANSLATE(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isText = ((KValField) kField).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("TRANSLATE", kField));
            }
            
            translateKField.sb.append("'");
        }
        
        translateKField.sb.append(kField.sb);
        
        if (kFieldIsVal) {
            translateKField.sb.append("'");
        }
        
        translateKField.sb.append(", '").append(from).append("', '").append(to).append("')");
        
        return translateKField;
    }
    
    public static KField trim(
        final KField kField
    ) {
        return trim(kField, null);
    }
    
    public static KField trim(
        final KField kField,
        final String characters
    ) {
        return genericTrim("TRIM", kField, characters);
    }
    
    public static KField sub(
        final KField kField1,
        final KField kField2
    ) {
        return applyBinaryOperator(kField1, kField2, "-");
    }
    
    public static KField sub(
        final KField kField,
        final Number number
    ) {
        return applyBinaryOperator(kField, number, "-");
    }
    
    public static KField sub(
        final Number number,
        final KField kField
    ) {
        return applyBinaryOperator(number, kField, "-");
    }
    
    public static KValField sub(
        final KValField kValField,
        final Number number
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField, number, "-");
    }
    
    public static KValField sub(
        final Number number,
        final KValField kValField
    ) {
        return applyBinaryOperatorWithValNumberValid(number, kValField, "-");
    }
    
    public static KValField sub(
        final KValField kValField1,
        final KValField kValField2
    ) {
        return applyBinaryOperatorWithValNumberValid(kValField1, kValField2, "-");
    }
    
    public static KField toChar(
        final KField kField,
        final String format
    ) {
        assertNotNull(kField, "kField");
        assertNotNull(format, "format");
        
        final KField substringKField = new KField();
        
        substringKField.sb.append("TO_CHAR(");
        
        final boolean kFieldIsVal = kField instanceof KValField;
        
        if (kFieldIsVal) {
            final boolean isNumber = ((KValField) kField).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("TO_CHAR", kField));
            }
        }
        
        substringKField.sb.append(kField.sb).append(", '").append(format).append("'").append(")");
        
        return substringKField;
    }
    
    public static KField trunc(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValNumberValid(kField, "TRUNC");
    }
    
    public static KField uuidGenerateV1() {
        return new KField("UUID_GENERATE_V1()");
    }
    
    public static KField uuidGenerateV4() {
        return new KField("UUID_GENERATE_V4()");
    }
    
    public static KField upper(
        final KField kField
    ) {
        return applyOneParameterFunctionWithValStringValid(kField, "UPPER");
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
    
    public static KField widthBucket(
        final KField op,
        final KField b1,
        final KField b2,
        final KField count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KField widthBucketKField = new KField();
        
        final boolean opIsVal = op instanceof KValField;
        final boolean b1IsVal = b1 instanceof KValField;
        final boolean b2IsVal = b2 instanceof KValField;
        final boolean countIsVal = count instanceof KValField;
        
        widthBucketKField.sb.append("WIDTH_BUCKET(");
        
        if (opIsVal) {
            final boolean isNumber = ((KValField) op).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("WIDTH_BUCKET", op));
            }
        }
        
        if (b1IsVal) {
            final boolean isNumber = ((KValField) b1).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("WIDTH_BUCKET", b1));
            }
        }
        
        if (b2IsVal) {
            final boolean isNumber = ((KValField) b2).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("WIDTH_BUCKET", b2));
            }
        }
        
        if (countIsVal) {
            final boolean isNumber = ((KValField) count).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("WIDTH_BUCKET", count));
            }
        }
        
        widthBucketKField.sb.append(op.sb).append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count.sb).append(")");
        
        return widthBucketKField;
    }
    
    private static void assertNotNull(
        final Object o,
        final String name
    ) {
        if (o == null) {
            throw KExceptionHelper.internalServerError("The '" + name + "' param is required"); 
        }
    }
}