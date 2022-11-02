package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFunction {

    public static KAliasedColumn<?> as(
        final KColumn kColumn,
        final String alias
    ) {
        assertNotNull(kColumn, "kColumn");
                
        return new KAliasedColumn(kColumn.sb, alias);
    }
    
    public static KColumn abs(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "ABS");
    }
    
    public static KColumn acos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "ACOS");
    }
    
    public static KColumn add(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "+");
    }
    
    public static KColumn add(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "+");
    }
    
    public static KColumn add(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "+");
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
    
    private static KColumn applyOneParameterFunction(
        final KColumn kColumn,
        final String functionName,
        final boolean valNumberIsValid,
        final boolean valStringIsValid
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        final KColumn functionkColumn = new KColumn(kColumn.sb);
        
        if (kColumnIsVal) {
            final boolean isNumber = ((KValField) kColumn).isNumber;
            final boolean isText = ((KValField) kColumn).isText;
            
            if (isText && !valStringIsValid) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kColumn));
            }
            
            if (isNumber && !valNumberIsValid) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType(functionName, kColumn));
            }
            
            if (isText) {
                functionkColumn.sb.insert(0, "'");
            }
        }
        
        functionkColumn.sb.insert(0, "(").insert(0, functionName);
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (isText) {
                functionkColumn.sb.append("'");
            }
        }
        
        functionkColumn.sb.append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyOneParameterFunctionWithValStringValid(
        final KColumn kColumn,
        final String functionName
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyOneParameterFunction(kColumn, functionName, false, true);
    }
    
    private static KColumn applyOneParameterFunctionWithValNumberValid(
        final KColumn kColumn,
        final String functionName
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyOneParameterFunction(kColumn, functionName, true, false);
    }
    
    private static KColumn applyTwoParameterFunctionWithValNumberValid(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String functionName
    ) {
        
        assertNotNull(kColumn1, "kColumn1");
        assertNotNull(kColumn2, "kColumn2");
        
        final KColumn functionkColumn = new KColumn();
        
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        functionkColumn.sb.append(functionName).append("(");
        
        if (kColumn1IsVal) {
            final boolean isNumber = ((KValField) kColumn1).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kColumn1));
            }
        }
        
        if (kColumn2IsVal) {
            final boolean isNumber = ((KValField) kColumn2).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType(functionName, kColumn2));
            }
        }
        
        functionkColumn.sb.append(kColumn1.sb).append(", ").append(kColumn2.sb).append(")");
        
        return functionkColumn;
    }
    
    public static KColumn ascii(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "ASCII");
    }
    
    public static KColumn asin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "ASIN");
    }
    
    public static KColumn atan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "ATAN");
    }
    
    public static KColumn atan2(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kColumn1, kColumn2, "ATAN2");
    }
    
    public static KColumn avg(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "AVG");
    }
    
    public static KColumn bitAnd(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "&");
    }
    
    public static KColumn bitAnd(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "&");
    }
    
    public static KColumn bitAnd(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "&");
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
    
    public static KColumn bitNot(
        final KColumn kColumn
    ) {
        return applyUnaryOperator(kColumn, "~", false);
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
    
    public static KColumn bitOr(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "|");
    }
    
    public static KColumn bitOr(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "|");
    }
    
    public static KColumn bitOr(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "|");
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
    
    public static KColumn bitShiftLeft(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "<<");
    }
    
    public static KColumn bitShiftLeft(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(kColumn, new KColumn(new StringBuilder(n)), "<<");
    }
    
    public static KValField bitShiftLeft(
        final KValField kValField,
        final int n
    ) {
        assertNotNull(kValField, "kValField");
        
        return applyBinaryOperatorWithValNumberValid(kValField, new KValField(n), "<<");
    }
    
    public static KColumn bitShiftRight(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, ">>");
    }
    
    public static KColumn bitShiftRight(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(kColumn, new KColumn(new StringBuilder(n)), ">>");
    }
    
    public static KValField bitShiftRight(
        final KValField kValField,
        final int n
    ) {
        assertNotNull(kValField, "kValField");
        
        return applyBinaryOperatorWithValNumberValid(kValField, new KValField(n), ">>");
    }
    
    public static KColumn bitXor(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "#");
    }
    
    public static KColumn bitXor(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "#");
    }
    
    public static KColumn bitXor(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "#");
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
    
    public static KColumn cast(
        final KColumn kColumn,
        final KDataType kDataType    
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(kColumn.sb);
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isNumber = ((KValField) kColumn).isNumber;
            
            if (!isNumber) {
                castkColumn.sb.insert(0, "'").append("'");
            }
        }
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cbrt(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "CBRT");
    }
    
    public static KColumn ceil(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "CEIL");
    }
    
    public static KColumn ceiling(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "CEILING");
    }
    
    public static KColumn chr(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "CHR");
    }
    
    public static KColumn coalesce(
        final KColumn... kColumns
    ) {
        
        assertNotNull(kColumns, "kColumns");
        
        if (kColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'COALESCE' function requires at least two kColumns");
        }
        
        final KColumn coalescekColumn = new KColumn();
        
        boolean first = true;
        
        coalescekColumn.sb.append("COALESCE(");
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }
            
            if (!first) {
                coalescekColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kColumn instanceof KValField) {
                coalescekColumn.sb.append("'").append(kColumn.sb).append("'");
                
                continue;
            }
            
            coalescekColumn.sb.append(kColumn.sb);
        }
        
        coalescekColumn.sb.append(")");
        
        return coalescekColumn;
    }
    
    public static KColumn cos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "COS");
    }
    
    public static KColumn cosh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "COSH");
    }
    
    public static KColumn cot(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "COT");
    }
    
    public static KColumn count() {
        return new KColumn("COUNT(*)");
    }
    
    public static KColumn concat(
        final KColumn... kColumns
    ) {
        
        assertNotNull(kColumns, "kColumns");
        
        if (kColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT' function requires at least two kColumns");
        }
        
        final KColumn concatkColumn = new KColumn();
        
        boolean first = true;
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }
            
            if (!first) {
                concatkColumn.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kColumn instanceof KValField) {
                final boolean isNumber = ((KValField) kColumn).isNumber;
                
                if (!isNumber) {
                    concatkColumn.sb.append("'");
                }
                
                concatkColumn.sb.append(kColumn.sb);
                
                if (!isNumber) {
                    concatkColumn.sb.append("'");
                }
                
                continue;
            }
            
            concatkColumn.sb.append(kColumn.sb);
        }
        
        return concatkColumn;
    }
    
    public static KColumn currentDate() {
        return new KColumn("CURRENT_DATE");
    }
    
    public static KColumn currentSchema() {
        return new KColumn("CURRENT_SCHEMA");
    }
    
    public static KColumn currentTime() {
        return currentTime(null);
    }
    
    public static KColumn currentTime(
        final Integer precision
    ) {
        return new KColumn("CURRENT_TIME" + ((precision != null) ? "(" + precision + ")": ""));
    }
    
    public static KColumn currentTimestamp() {
        return currentTimestamp(null);
    }
    
    public static KColumn currentTimestamp(
        final Integer precision
    ) {
        return new KColumn("CURRENT_TIMESTAMP" + ((precision != null) ? "(" + precision + ")": ""));
    }
    
    public static KColumn currentUser() {
        return new KColumn("CURRENT_USER");
    }
    
    public static KColumn datePart(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        if (kColumn instanceof KValField) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("The 'DATE_PART' function only can be used with a column. Current value: ['" + kColumn.sb.toString() + "']" );
            }
            
            final boolean isNumber = ((KValField) kColumn).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("The 'DATE_PART' function only can be used with a column. Current value: [" + kColumn.sb.toString() + "]" );
            }
            
            throw KExceptionHelper.internalServerError("The 'DATE_PART' function only can be used with a column.");
        }
        
        final KColumn extractkColumn = new KColumn(kColumn.sb);
        
        extractkColumn.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_PART('").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn dateTrunc(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        if (kColumn instanceof KValField) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("The 'DATE_TRUNC' function only can be used with a column. Current value: ['" + kColumn.sb.toString() + "']" );
            }
            
            final boolean isNumber = ((KValField) kColumn).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("The 'DATE_TRUNC' function only can be used with a column. Current value: [" + kColumn.sb.toString() + "]" );
            }
            
            throw KExceptionHelper.internalServerError("The 'DATE_TRUNC' function only can be used with a column.");
        }
        
        final KColumn extractkColumn = new KColumn(kColumn.sb);
        
        extractkColumn.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_TRUNC('").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn decode(
        final KColumn kColumn,
        final KFormat kFormat
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kFormat, "kFormat");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("DECODE", kColumn));
            }
        }
        
        final KColumn encodekColumn = kColumn.cloneMe();
        
        if (kColumnIsVal) {
            encodekColumn.sb.insert(0, "'").append("'");
        }
        
        encodekColumn.sb.insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodekColumn;
    }
    
    public static KColumn degrees(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "DEGREES");
    }
    
    public static KColumn div(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "/");
    }
    
    public static KColumn div(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "/");
    }
    
    public static KColumn div(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "/");
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
    
    private static KColumn applyBinaryOperator(
        final Number number,
        final KColumn kColumn,
        final String operator
    ) {
        assertNotNull(number, "number");
        assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(new KColumn(new StringBuilder(number.toString())), kColumn, operator);
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn,
        final Number number,
        final String operator
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(number, "number");
        
        return applyBinaryOperator(kColumn, new KColumn(new StringBuilder(number.toString())), operator);
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        assertNotNull(kColumn1, "kColumn1");
        assertNotNull(kColumn2, "kColumn2");
        
        final KColumn operationkColumn = new KColumn(kColumn1.sb);
        
        operationkColumn.sb.append(" ").append(operator).append(" ").append(kColumn2.sb);
        
        return operationkColumn;
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
        
        final KValField newKValField = new KValField(kValField1.sb, kValField1.sbParam, kValField1.params, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
            newKValField.sbParam.insert(0, "(").append(")");
        }
        
        newKValField.sb.append(" ").append(operator).append(" ");
        newKValField.sbParam.append(" ").append(operator).append(" ");
        
        final boolean kValFieldCasteableToANumber = isCasteableToANumber(kValField2.sb.toString());
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append("(");
            newKValField.sbParam.append("(");
        }
        
        newKValField.sb.append(kValField2.sb);
        newKValField.sbParam.append(kValField2.sbParam);
        newKValField.params.addAll(kValField2.params);
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append(")");
            newKValField.sbParam.append(")");
        }
        
        return newKValField;
    }
    
    private static KColumn applyUnaryOperator(
        final KColumn kColumn,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn operationkColumn = new KColumn();
        
        operationkColumn.sb.append(addToRightSide ? "" : operator).append(kColumn.sb).append(addToRightSide ? operator : "");
        
        return operationkColumn;
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
        
        final KValField newKValField = new KValField(kValField.sb, kValField.sbParam, kValField.params, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
            newKValField.sbParam.insert(0, "(").append(")");
        }
        
        newKValField.sb.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        newKValField.sbParam.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        
        return newKValField;
    }
    
    public static KColumn encode(
        final KColumn kColumn,
        final KFormat kFormat
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kFormat, "kFormat");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("ENCODE", kColumn));
            }
        }
        
        final KColumn encodekColumn = kColumn.cloneMe();
        
        if (kColumnIsVal) {
            encodekColumn.sb.insert(0, "'").append("'");
        }
        
        encodekColumn.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodekColumn;
    }
    
    public static KColumn exp(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "EXP");
    }
    
    public static KColumn extract(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        if (kColumn instanceof KValField) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (isText) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: ['" + kColumn.sb.toString() + "']" );
            }
            
            final boolean isNumber = ((KValField) kColumn).isNumber;
            
            if (isNumber) {
                throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column. Current value: [" + kColumn.sb.toString() + "]" );
            }
            
            throw KExceptionHelper.internalServerError("The 'EXTRACT' function only can be used with a column.");
        }
        
        final KColumn extractkColumn = new KColumn(kColumn.sb);
        
        extractkColumn.sb.insert(0, " FROM ").insert(0, kExtractField.toSql()).insert(0, "EXTRACT(").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn floor(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "FLOOR");
    }
    
    public static KColumn genRandomUuid() {
        return new KColumn("GEN_RANDOM_UUID()");
    }
    
    private static KColumn genericTrim(
        final String trimFunctionName,
        final KColumn kColumn,
        final String characters
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn lpadkColumn = new KColumn();
        
        lpadkColumn.sb.append(trimFunctionName).append("(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType(trimFunctionName, kColumn));
            }
            
            lpadkColumn.sb.append("'");
        }
        
        lpadkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            lpadkColumn.sb.append("'");
        }
                
        if (characters != null) {
            lpadkColumn.sb.append(", '").append(characters).append("'");
        }
                
        lpadkColumn.sb.append(")");
        
        return lpadkColumn;
    }
    
    private static String getErrorMessageFunctionTextType(
        final String functionName,
        final KColumn kColumn
    ) {
        return getGenericErrorMessage(functionName, "function", "text", kColumn);
    }
    
    private static String getErrorMessageFunctionNumberType(
        final String functionName,
        final KColumn kColumn
    ) {
        return getGenericErrorMessage(functionName, "function", "number", kColumn);
    }
    
    private static String getErrorMessageOperatorTextType(
        final String operatorName,
        final KColumn kColumn
    ) {
        return getGenericErrorMessage(operatorName, "operator", "text", kColumn);
    }
    
    private static String getErrorMessageOperatorNumberType(
        final String operatorName,
        final KColumn kColumn
    ) {
        return getGenericErrorMessage(operatorName, "operator", "number", kColumn);
    }
    
    private static String getGenericErrorMessage(
        final String name,
        final String entity,
        final String type,
        final KColumn kColumn
    ) {
        return "The '" + name + "' " + entity + " only can be used with a column or with a 'val' of " + type + " type. Current value: [" 
            + (type.equals("number") ? "'" : "") 
            + kColumn.sb.toString() 
            + (type.equals("number") ? "'" : "") 
            + "]";
    }
    
    public static KColumn getJsonArray(
        final KColumn kColumn,
        final int index
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("->").append(index);
        
        return jsonkColumn;
    }
    
    public static KColumn getJsonArrayAsText(
        final KColumn kColumn,
        final int index
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("->>").append(index);
        
        return jsonkColumn;
    }
    
    public static KColumn getJsonObject(
        final KColumn kColumn,
        final String name
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("->'").append(name).append("'");
        
        return jsonkColumn;
    }
    
    public static KColumn getJsonObjectAsText(
        final KColumn kColumn,
        final String name
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("->>'").append(name).append("'");
        
        return jsonkColumn;
    }
    
    public static KColumn getJsonObjectAtPath(
        final KColumn kColumn,
        final String path
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("#>'{").append(path).append("}'");
        
        return jsonkColumn;
    }
    
    public static KColumn getJsonObjectAtPathAsText(
        final KColumn kColumn,
        final String path
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonkColumn = new KColumn(kColumn.sb);
        
        jsonkColumn.sb.append("#>>'{").append(path).append("}'");
        
        return jsonkColumn;
    }
    
    public static KColumn greatest(
        final KColumn... kColumns
    ) {
        assertNotNull(kColumns, "kColumns");
        
        if (kColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'GREATEST' function requires at least two kColumns");
        }
        
        final KColumn greatestkColumn = new KColumn();
        
        boolean first = true;
        
        greatestkColumn.sb.append("GREATEST(");
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }
            
            if (!first) {
                greatestkColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kColumn instanceof KValField) {
                final boolean isText = ((KValField) kColumn).isText;
            
                if (isText) {
                    greatestkColumn.sb.append("'");
                }
            }
            
            greatestkColumn.sb.append(kColumn.sb);
            
            if (kColumn instanceof KValField) {
                final boolean isText = ((KValField) kColumn).isText;
            
                if (isText) {
                    greatestkColumn.sb.append("'");
                }
            }
            
        }
        
        greatestkColumn.sb.append(")");
        
        return greatestkColumn;
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
    
    public static KColumn isolate(
        final KColumn kColumn
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn isolatekColumn = kColumn.cloneMe();
        
        isolatekColumn.sb.insert(0, "(").append(")");
        
        return isolatekColumn;
    }
    
    public static KColumn least(
        final KColumn... kColumns
    ) {
        assertNotNull(kColumns, "kColumns");
        
        if (kColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'LEAST' function requires at least two kColumns");
        }
        
        final KColumn greatestkColumn = new KColumn();
        
        boolean first = true;
        
        greatestkColumn.sb.append("LEAST(");
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }
            
            if (!first) {
                greatestkColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kColumn instanceof KValField) {
                final boolean isText = ((KValField) kColumn).isText;
            
                if (isText) {
                    greatestkColumn.sb.append("'");
                }
            }
            
            greatestkColumn.sb.append(kColumn.sb);
            
            if (kColumn instanceof KValField) {
                final boolean isText = ((KValField) kColumn).isText;
            
                if (isText) {
                    greatestkColumn.sb.append("'");
                }
            }
            
        }
        
        greatestkColumn.sb.append(")");
        
        return greatestkColumn;
    }
    
    public static KColumn left(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn leftkColumn = new KColumn();
        
        leftkColumn.sb.append("LEFT(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("LEFT", kColumn));
            }
            
            leftkColumn.sb.append("'");
        }
        
        leftkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            leftkColumn.sb.append("'");
        }
        
        leftkColumn.sb.append(", ").append(n).append(")");
        
        return leftkColumn;
    }
    
    public static KColumn length(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "LENGTH");
    }
    
    public static KColumn ln(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "LN");
    }
    
    public static KColumn localTime() {
        return localTime(null);
    }
    
    public static KColumn localTime(
        final Integer precision
    ) {
        return new KColumn("LOCALTIME" + ((precision != null) ? "(" + precision + ")": ""));
    }
    
    public static KColumn localTimestamp() {
        return localTimestamp(null);
    }
    
    public static KColumn localTimestamp(
        final Integer precision
    ) {
        return new KColumn("LOCALTIMESTAMP" + ((precision != null) ? "(" + precision + ")": ""));
    }
    
    public static KColumn log(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kColumn1, kColumn2, "LOG");
    }
    
    public static KColumn log10(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "LOG10");
    }
    
    public static KColumn lower(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "LOWER");
    }
    
    public static KColumn lpad(
        final KColumn kColumn,
        final int n
    ) {
        return lpad(kColumn, n, null);
    }
    
    public static KColumn lpad(
        final KColumn kColumn,
        final int n,
        final String fillText
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn lpadkColumn = new KColumn();
        
        lpadkColumn.sb.append("LPAD(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("LPAD", kColumn));
            }
            
            lpadkColumn.sb.append("'");
        }
        
        lpadkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            lpadkColumn.sb.append("'");
        }
        
        lpadkColumn.sb.append(", ").append(n);
                
        if (fillText != null) {
            lpadkColumn.sb.append(", '").append(fillText).append("'");
        }
                
        lpadkColumn.sb.append(")");
        
        return lpadkColumn;
    }
    
    public static KColumn ltrim(
        final KColumn kColumn
    ) {
        return ltrim(kColumn, null);
    }
    
    public static KColumn ltrim(
        final KColumn kColumn,
        final String characters
    ) {
        return genericTrim("LTRIM", kColumn, characters);
    }
    
    public static KColumn md5(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "MD5");
    }
    
    public static KColumn mod(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "%");
    }
    
    public static KColumn mod(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "%");
    }
    
    public static KColumn mod(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "%");
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
    
    public static KColumn mul(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "*");
    }
    
    public static KColumn mul(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "*");
    }
    
    public static KColumn mul(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "*");
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
    
    public static KColumn now() {
        return new KColumn("NOW()");
    }
    
    public static KColumn nullif(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        
        assertNotNull(kColumn1, "kColumn1");
        assertNotNull(kColumn2, "kColumn2");
        
        final KColumn nullifkColumn = new KColumn();
        
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        nullifkColumn.sb.append("NULLIF(");
        
        if (kColumn1IsVal) {
            final boolean isNumber = ((KValField) kColumn1).isNumber;
            
            if (!isNumber) {
                nullifkColumn.sb.append("'");
            }
        }
        
        nullifkColumn.sb.append(kColumn1.sb);
        
        if (kColumn1IsVal) {
            final boolean isNumber = ((KValField) kColumn1).isNumber;
            
            if (!isNumber) {
                nullifkColumn.sb.append("'");
            }
        }
        
        nullifkColumn.sb.append(", ");
        
        if (kColumn2IsVal) {
            final boolean isNumber = ((KValField) kColumn2).isNumber;
            
            if (!isNumber) {
                nullifkColumn.sb.append("'");
            }
        }
        
        nullifkColumn.sb.append(kColumn2.sb);
        
        if (kColumn2IsVal) {
            final boolean isNumber = ((KValField) kColumn2).isNumber;
            
            if (!isNumber) {
                nullifkColumn.sb.append("'");
            }
        }
        
        nullifkColumn.sb.append(")");
        
        return nullifkColumn;
    }
    
    public static KColumn overlay(
        final KColumn kColumn,
        final String value,
        final Integer from
    ) {
        return overlay(kColumn, value, from, null);
    }
    
    public static KColumn overlay(
        final KColumn kColumn,
        final String value,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(value, "value");
        
        final KColumn overlaykColumn = new KColumn();
        
        overlaykColumn.sb.append("OVERLAY(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("OVERLAY", kColumn));
            }
            
            overlaykColumn.sb.append("'");
        }
        
        overlaykColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            overlaykColumn.sb.append("'");
        }
        
        overlaykColumn.sb.append(" PLACING '").append(value).append("'").append(" from ").append(from);
        
        if (for_ != null) {
            overlaykColumn.sb.append(" for ").append(for_);
        }
                
        overlaykColumn.sb.append(")");
        
        return overlaykColumn;
    }
    
    public static KColumn pi() {
        return new KColumn("PI()");
    }
    
    public static KColumn position(
        final KColumn kColumn,
        final String valueToLocate
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(valueToLocate, "valueToLocate");
        
        final KColumn positionkColumn = new KColumn();
        
        positionkColumn.sb.append("POSITION('").append(valueToLocate).append("'").append(" in ");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("POSITION", kColumn));
            }
            
            positionkColumn.sb.append("'");
        }
        
        positionkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            positionkColumn.sb.append("'");
        }
        
        positionkColumn.sb.append(")");
        
        return positionkColumn;
    }
    
    public static KColumn power(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kColumn1, kColumn2, "POWER");
    }
    
    public static KColumn radians(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "RADIANS");
    }
    
    public static KColumn random() {
        return new KColumn("RANDOM()");
    }
    
    public static KColumn rawColumn(
        final String content
    ) {
        return new KColumn(content);
    }
    
    public static KColumn repeat(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn repeatkColumn = new KColumn();
        
        repeatkColumn.sb.append("REPEAT(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REPEAT", kColumn));
            }
            
            repeatkColumn.sb.append("'");
        }
        
        repeatkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            repeatkColumn.sb.append("'");
        }
        
        repeatkColumn.sb.append(", ").append(n).append(")");
        
        return repeatkColumn;
    }
    
    public static KColumn regexpReplace(
        final KColumn kColumn,
        final String pattern,
        final String replacement
    ) {
        return regexpReplace(kColumn, pattern, replacement, null);
    }
    
    public static KColumn regexpReplace(
        final KColumn kColumn,
        final String pattern,
        final String replacement,
        final String flags
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(pattern, "pattern");
        assertNotNull(replacement, "replacement");
        
        final KColumn regexpReplacekColumn = new KColumn();
        
        regexpReplacekColumn.sb.append("REGEXP_REPLACE(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REGEXP_REPLACE", kColumn));
            }
            
            regexpReplacekColumn.sb.append("'");
        }
        
        regexpReplacekColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            regexpReplacekColumn.sb.append("'");
        }

        regexpReplacekColumn.sb.append(", '").append(pattern).append("', '").append(replacement).append("'");
        
        if (flags != null) {
            regexpReplacekColumn.sb.append(", '").append(flags).append("'");
        }
        
        regexpReplacekColumn.sb.append(")");
        
        return regexpReplacekColumn;
    }
    
    public static KColumn replace(
        final KColumn kColumn,
        final String from,
        final String to
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn replacekColumn = new KColumn();
        
        replacekColumn.sb.append("REPLACE(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("REGEXP_REPLACE", kColumn));
            }
            
            replacekColumn.sb.append("'");
        }
        
        replacekColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            replacekColumn.sb.append("'");
        }

        replacekColumn.sb.append(", '").append(from).append("', '").append(to).append("'").append(")");
        
        return replacekColumn;
    }
    
    public static KColumn reverse(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "REVERSE");
    }
    
    public static KColumn right(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn leftkColumn = new KColumn();
        
        leftkColumn.sb.append("RIGHT(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("RIGHT", kColumn));
            }
            
            leftkColumn.sb.append("'");
        }
        
        leftkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            leftkColumn.sb.append("'");
        }
        
        leftkColumn.sb.append(", ").append(n).append(")");
        
        return leftkColumn;
    }
    
    public static KColumn round(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunctionWithValNumberValid(kColumn1, kColumn2, "ROUND");
    }
    
    public static KColumn rpad(
        final KColumn kColumn,
        final int n
    ) {
        return rpad(kColumn, n, null);
    }
    
    public static KColumn rpad(
        final KColumn kColumn,
        final int n,
        final String fillText
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn rpadkColumn = new KColumn();
        
        rpadkColumn.sb.append("RPAD(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("RPAD", kColumn));
            }
            
            rpadkColumn.sb.append("'");
        }
        
        rpadkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            rpadkColumn.sb.append("'");
        }
        
        rpadkColumn.sb.append(", ").append(n);
                
        if (fillText != null) {
            rpadkColumn.sb.append(", '").append(fillText).append("'");
        }
                
        rpadkColumn.sb.append(")");
        
        return rpadkColumn;
    }
    
    public static KColumn rtrim(
        final KColumn kColumn
    ) {
        return rtrim(kColumn, null);
    }
    
    public static KColumn rtrim(
        final KColumn kColumn,
        final String characters
    ) {
        return genericTrim("RTRIM", kColumn, characters);
    }
    
    public static KColumn sign(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "SIGN");
    }
    
    public static KColumn sin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "SIN");
    }
    
    public static KColumn sinh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "SINH");
    }
    
    public static KColumn splitPart(
        final KColumn kColumn,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kColumn, "kColumn");
        
        if (delimiter == null) {
            throw KExceptionHelper.internalServerError("'delimiter' is required in 'SPLIT_PART' function");
        }
        
        final KColumn splitPartkColumn = new KColumn();
        
        splitPartkColumn.sb.append("SPLIT_PART(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SPLIT_PART", kColumn));
            }
            
            splitPartkColumn.sb.append("'");
        }
        
        splitPartkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            splitPartkColumn.sb.append("'");
        }
        
        splitPartkColumn.sb.append(", '").append(delimiter).append("', ").append(field).append(")");
        
        return splitPartkColumn;
    }
    
    public static KColumn sqrt(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "SQRT");
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final Integer from
    ) {
        return substring(kColumn, from, null);
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kColumn, "kColumn");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least 1 is required");
        }
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SUBSTRING", kColumn));
            }
            
            substringkColumn.sb.append("'");
        }
        
        substringkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            substringkColumn.sb.append("'");
        }
        
        if (from != null) {
            substringkColumn.sb.append(" from ").append(from);
        }
        
        if (for_ != null) {
            substringkColumn.sb.append(" for ").append(for_);
        }
                
        substringkColumn.sb.append(")");
        
        return substringkColumn;
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final String from
    ) {
        return substring(kColumn, from, null);
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final String from,
        final String for_
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("SUBSTRING", kColumn));
            }
            
            substringkColumn.sb.append("'");
        }
        
        substringkColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            substringkColumn.sb.append("'");
        }
        
        substringkColumn.sb.append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringkColumn.sb.append(" for '").append(for_).append("'");
        }
        
        substringkColumn.sb.append(")");
        
        return substringkColumn;
    }
    
    public static KColumn tan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "TAN");
    }
    
    public static KColumn tanh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "TANH");
    }
    
    public static KColumn toDate(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_DATE(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("TO_DATE", kColumn));
            }
            
            toDatekColumn.sb.append("'");
        }
        
        toDatekColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            toDatekColumn.sb.append("'");
        }

        toDatekColumn.sb.append(", '").append(format).append("'").append(")");
        
        return toDatekColumn;
    }
    
    public static KColumn toHex(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "TO_HEX");
    }
    
    public static KColumn toTimestamp(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_TIMESTAMP(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("TO_DATE", kColumn));
            }
            
            toDatekColumn.sb.append("'");
        }
        
        toDatekColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            toDatekColumn.sb.append("'");
        }

        toDatekColumn.sb.append(", '").append(format).append("'").append(")");
        
        return toDatekColumn;
    }
    
    public static KColumn translate(
        final KColumn kColumn,
        final String from,
        final String to
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn translatekColumn = new KColumn();
        
        translatekColumn.sb.append("TRANSLATE(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isText = ((KValField) kColumn).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionTextType("TRANSLATE", kColumn));
            }
            
            translatekColumn.sb.append("'");
        }
        
        translatekColumn.sb.append(kColumn.sb);
        
        if (kColumnIsVal) {
            translatekColumn.sb.append("'");
        }
        
        translatekColumn.sb.append(", '").append(from).append("', '").append(to).append("')");
        
        return translatekColumn;
    }
    
    public static KColumn trim(
        final KColumn kColumn
    ) {
        return trim(kColumn, null);
    }
    
    public static KColumn trim(
        final KColumn kColumn,
        final String characters
    ) {
        return genericTrim("TRIM", kColumn, characters);
    }
    
    public static KColumn sub(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyBinaryOperator(kColumn1, kColumn2, "-");
    }
    
    public static KColumn sub(
        final KColumn kColumn,
        final Number number
    ) {
        return applyBinaryOperator(kColumn, number, "-");
    }
    
    public static KColumn sub(
        final Number number,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(number, kColumn, "-");
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
    
    public static KColumn toChar(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("TO_CHAR(");
        
        final boolean kColumnIsVal = kColumn instanceof KValField;
        
        if (kColumnIsVal) {
            final boolean isNumber = ((KValField) kColumn).isNumber;
            
            if (!isNumber) {
                throw KExceptionHelper.internalServerError(getErrorMessageFunctionNumberType("TO_CHAR", kColumn));
            }
        }
        
        substringkColumn.sb.append(kColumn.sb).append(", '").append(format).append("'").append(")");
        
        return substringkColumn;
    }
    
    public static KColumn trunc(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValNumberValid(kColumn, "TRUNC");
    }
    
    public static KColumn uuidGenerateV1() {
        return new KColumn("UUID_GENERATE_V1()");
    }
    
    public static KColumn uuidGenerateV4() {
        return new KColumn("UUID_GENERATE_V4()");
    }
    
    public static KColumn upper(
        final KColumn kColumn
    ) {
        return applyOneParameterFunctionWithValStringValid(kColumn, "UPPER");
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
    
    public static KColumn widthBucket(
        final KColumn op,
        final KColumn b1,
        final KColumn b2,
        final KColumn count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        final boolean opIsVal = op instanceof KValField;
        final boolean b1IsVal = b1 instanceof KValField;
        final boolean b2IsVal = b2 instanceof KValField;
        final boolean countIsVal = count instanceof KValField;
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(");
        
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
        
        widthBucketkColumn.sb.append(op.sb).append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count.sb).append(")");
        
        return widthBucketkColumn;
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