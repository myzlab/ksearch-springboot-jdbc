package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFunction {

    public static KAliasedColumn as(
        final KBaseColumnCastable kBaseColumnCastable,
        final String alias
    ) {
        assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
                
        return new KAliasedColumn(kBaseColumnCastable, alias);
    }
    
    public static KAliasedColumn as(
        final Number val,
        final String alias
    ) {
        assertNotNull(val, "val");
                
        return new KAliasedColumn(val(val), alias);
    }
    
    public static KAliasedColumn as(
        final String val,
        final String alias
    ) {
        assertNotNull(val, "val");
                
        return new KAliasedColumn(val(val), alias);
    }
    
    public static KColumn abs(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ABS");
    }
    
    public static KColumn abs(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ABS");
    }
    
    public static KColumn acos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ACOS");
    }
    
    public static KColumn acos(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ACOS");
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
    
    public static KValNumberField add(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "+");
    }
    
    public static KValNumberField add(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "+");
    }
    
    public static KValNumberField add(
        final KValNumberField kValField1,
        final KValNumberField kValField2
    ) {
        return applyBinaryOperator(kValField1, kValField2, "+");
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
    
    private static KValNumberField applyBinaryOperator(
        final KValNumberField kValNumberField,
        final Number number,
        final String operator
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        assertNotNull(number, "number");
        
        return applyBinaryOperator(kValNumberField, new KValNumberField(number), operator);
    }
    
    private static KValNumberField applyBinaryOperator(
        final Number number,
        final KValNumberField kValNumberField,
        final String operator
    ) {
        assertNotNull(number, "number");
        assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(new KValNumberField(number), kValNumberField, operator);
    }
    
    private static KValNumberField applyBinaryOperator(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2,
        final String operator
    ) {
        
        assertNotNull(kValNumberField1, "kValNumberField1");
        assertNotNull(kValNumberField2, "kValNumberField2");
        
        final KValNumberField newKValField = new KValNumberField(kValNumberField1.sb, kValNumberField1.params, kValNumberField1.operating, false);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.append(" ").append(operator).append(" ");
        
        final boolean kValFieldCasteableToANumber = isCasteableToANumber(kValNumberField2.sb.toString());
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append("(");
        }
        
        newKValField.sb.append(kValNumberField2.sb);
        newKValField.params.addAll(kValNumberField2.params);
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append(")");
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
    
    private static KValNumberField applyUnaryOperator(
        final Number number,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(number, "number");
        
        return applyUnaryOperator(val(number), operator, addToRightSide);
    }
    
    private static KValNumberField applyUnaryOperator(
        final KValNumberField kValNumberField,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField newKValField = new KValNumberField(kValNumberField.sb, kValNumberField.params, kValNumberField.operating, false);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        
        return newKValField;
    }
    
    private static KColumn applyOneParameterFunction(
        final KColumn kColumn,
        final String functionName
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn functionkColumn = new KColumn(kColumn.sb, kColumn.params, kColumn.operating, true);
        
        functionkColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyOneParameterFunction(
        final KValNumberField kValNumberField,
        final String functionName
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KColumn functionkColumn = new KColumn(kValNumberField.sb, kValNumberField.params, kValNumberField.operating, true);
        
        functionkColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyOneParameterFunction(
        final Number number,
        final String functionName
    ) {
        assertNotNull(number, "number");
        
        final KColumn functionkColumn = new KColumn(null, number);
        
        functionkColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyOneParameterFunction(
        final KValTextField kValTextField,
        final String functionName
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn functionkColumn = new KColumn(kValTextField.sb);
        
        functionkColumn.sb.insert(0, "'").insert(0, "(").insert(0, functionName).append("'").append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyTwoParameterFunction(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String functionName
    ) {
        assertNotNull(kColumn1, "kColumn1");
        assertNotNull(kColumn2, "kColumn2");
        
        final KColumn functionkColumn = new KColumn();
        
        functionkColumn.sb.append(functionName).append("(").append(kColumn1.sb).append(", ").append(kColumn2.sb).append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyTwoParameterFunction(
        final KValNumberField kValNumberField,
        final KColumn kColumn,
        final String functionName
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        assertNotNull(kColumn, "kColumn");
        
        final KColumn functionkColumn = new KColumn();
        
        functionkColumn.sb.append(functionName).append("(").append(kValNumberField.sb).append(", ").append(kColumn.sb).append(")");
        
        return functionkColumn;
    }
    
    private static KColumn applyTwoParameterFunction(
        final KColumn kColumn,
        final KValNumberField kValNumberField,
        final String functionName
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KColumn functionkColumn = new KColumn();
        
        functionkColumn.sb.append(functionName).append("(").append(kColumn.sb).append(", ").append(kValNumberField.sb).append(")");
        
        return functionkColumn;
    }
    
    private static KValNumberField applyTwoParameterFunction(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2,
        final String functionName
    ) {
        
        assertNotNull(kValNumberField1, "kValNumberField1");
        assertNotNull(kValNumberField2, "kValNumberField2");
        
        final KValNumberField functionkValNumberField = new KValNumberField();
        
        functionkValNumberField.sb.append(functionName).append("(").append(kValNumberField1.sb).append(", ").append(kValNumberField2.sb).append(")");
        functionkValNumberField.params.addAll(kValNumberField1.params);
        functionkValNumberField.params.addAll(kValNumberField2.params);
        
        return functionkValNumberField;
    }
    
    public static KColumn ascii(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ASCII");
    }
    
    public static KColumn ascii(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "ASCII");
    }
    
    public static KColumn asin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ASIN");
    }
    
    public static KColumn asin(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ASIN");
    }
    
    public static KColumn atan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ATAN");
    }
    
    public static KColumn atan(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ATAN");
    }
    
    public static KColumn atan2(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(kColumn1, kColumn2, "ATAN2");
    }
    
    public static KColumn atan2(
        final KColumn kColumn,
        final Number number
    ) {
        return applyTwoParameterFunction(kColumn, val(number), "ATAN2");
    }
    
    public static KColumn atan2(
        final Number number,
        final KColumn kColumn
    ) {
        return applyTwoParameterFunction(val(number), kColumn, "ATAN2");
    }
    
    public static KValNumberField atan2(
        final Number number1,
        final Number number2
    ) {
        return applyTwoParameterFunction(val(number1), val(number2), "ATAN2");
    }
    
    public static KColumn avg(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "AVG");
    }
    
    public static KColumn avg(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "AVG");
    }
    
    public static KColumn avg(
        final Number number
    ) {
        return applyOneParameterFunction(number, "AVG");
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
    
    public static KValNumberField bitAnd(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "&");
    }
    
    public static KValNumberField bitAnd(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "&");
    }
    
    public static KValNumberField bitAnd(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "&");
    }
    
    public static KColumn bitNot(
        final KColumn kColumn
    ) {
        return applyUnaryOperator(kColumn, "~", false);
    }
    
    public static KValNumberField bitNot(
        final Number number
    ) {
        return applyUnaryOperator(number, "~", false);
    }
    
    public static KValNumberField bitNot(
        final KValNumberField kValNumberField
    ) {
        return applyUnaryOperator(kValNumberField, "~", false);
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
    
    public static KValNumberField bitOr(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "|");
    }
    
    public static KValNumberField bitOr(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "|");
    }
    
    public static KValNumberField bitOr(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "|");
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
    
    public static KValNumberField bitShiftLeft(
        final KValNumberField kValNumberField,
        final int n
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(kValNumberField, new KValNumberField(n), "<<");
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
    
    public static KValNumberField bitShiftRight(
        final KValNumberField kValNumberField,
        final int n
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(kValNumberField, new KValNumberField(n), ">>");
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
    
    public static KValNumberField bitXor(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "#");
    }
    
    public static KValNumberField bitXor(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "#");
    }
    
    public static KValNumberField bitXor(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "#");
    }
    
    public static KColumn cast(
        final KBaseColumnCastable kBaseColumnCastable,
        final KDataType kDataType    
    ) {
        assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(kBaseColumnCastable.sb, kBaseColumnCastable.params, kBaseColumnCastable.operating, true);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cast(
        final Number val,
        final KDataType kDataType    
    ) {
        assertNotNull(val, "val");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(null, val);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cast(
        final String val,
        final KDataType kDataType    
    ) {
        assertNotNull(val, "val");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(null, val);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
//    public static KColumn cast(
//        final KColumn kColumn,
//        final KDataType kDataType    
//    ) {
//        assertNotNull(kColumn, "kColumn");
//        assertNotNull(kDataType, "kDataType");
//        
//        final KColumn castkColumn = kColumn.cloneMe();
//        
//        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
//        
//        return castkColumn;
//    }
//    
//    public static KColumn cast(
//        final KValNumberField kValNumberField,
//        final KDataType kDataType    
//    ) {
//        assertNotNull(kValNumberField, "kValNumberField");
//        assertNotNull(kDataType, "kDataType");
//        
//        final KColumn castkColumn = new KColumn(kValNumberField.sb);
//        
//        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
//        
//        return castkColumn;
//    }
    
//    public static KColumn cast(
//        final KValTextField kValTextField,
//        final KDataType kDataType    
//    ) {
//        assertNotNull(kValTextField, "kValTextField");
//        assertNotNull(kDataType, "kDataType");
//        
//        final KColumn castkColumn = new KColumn(kValTextField.sb);
//        
//        castkColumn.sb.insert(0, "'").append("'").insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
//        
//        return castkColumn;
//    }
    
    public static KColumn cbrt(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CBRT");
    }
    
    public static KColumn cbrt(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CBRT");
    }
    
    public static KColumn ceil(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CEIL");
    }
    
    public static KColumn ceil(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CEIL");
    }
    
    public static KColumn ceiling(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CEILING");
    }
    
    public static KColumn ceiling(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CEILING");
    }
    
    public static KColumn chr(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CHR");
    }
    
    public static KColumn chr(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "CHR");
    }
    
    public static KColumn coalesce(
        final KBaseColumn... kBaseColumns
    ) {
        assertNotNull(kBaseColumns, "kBaseColumn");
        
        if (kBaseColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'COALESCE' function requires at least two kBaseColumns");
        }
        
        final KColumn coalescekColumn = new KColumn();
        
        boolean first = true;
        
        coalescekColumn.sb.append("COALESCE(");
        
        for (final KBaseColumn kBaseColumn : kBaseColumns) {
            if (kBaseColumn == null) {
                continue;
            }
            
            if (!first) {
                coalescekColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kBaseColumn instanceof KValTextField) {
                coalescekColumn.sb.append("'").append(kBaseColumn.sb).append("'");
                
                continue;
            }
            
            coalescekColumn.sb.append(kBaseColumn.sb);
        }
        
        coalescekColumn.sb.append(")");
        
        return coalescekColumn;
    }
    
    public static KColumn cos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COS");
    }
    
    public static KColumn cos(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COS");
    }
    
    public static KColumn cosh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COSH");
    }
    
    public static KColumn cosh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COSH");
    }
    
    public static KColumn cot(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COT");
    }
    
    public static KColumn cot(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COT");
    }
    
    public static KColumn count() {
        return new KColumn("COUNT(*)");
    }
    
    public static KColumn concat(
        final KBaseColumnCastable... kBaseColumnCastables
    ) {
        assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
        if (kBaseColumnCastables.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT' function requires at least two kBaseColumnCastable");
        }
        
        final KColumn concatkColumn = new KColumn(false);
        
        boolean first = true;
        
        for (final KBaseColumnCastable kBaseColumnCastable : kBaseColumnCastables) {
            if (kBaseColumnCastable == null) {
                continue;
            }
            
            if (!first) {
                concatkColumn.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            concatkColumn.sb.append(kBaseColumnCastable.sb);
            concatkColumn.params.addAll(kBaseColumnCastable.params);
            concatkColumn.operating += kBaseColumnCastable.operating;
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
        
        final KColumn decodeKColumn = kColumn.cloneMe();
        
        decodeKColumn.sb.insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return decodeKColumn;
    }
    
    public static KValTextField decode(
        final KValTextField kValTextField,
        final KFormat kFormat
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(kFormat, "kFormat");
        
        final KValTextField decodeKValTextField = kValTextField.cloneMe();
        
        decodeKValTextField.sb.insert(0, "'").append("'").insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return decodeKValTextField;
    }
    
    public static KColumn degrees(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "DEGREES");
    }
    
    public static KColumn degrees(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "DEGREES");
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
    
    public static KValNumberField div(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "/");
    }
    
    public static KValNumberField div(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "/");
    }
    
    public static KValNumberField div(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, 2, "/");
    }
    
    public static KColumn encode(
        final KColumn kColumn,
        final KFormat kFormat
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kFormat, "kFormat");
        
        final KColumn encodeKColumn = kColumn.cloneMe();
        
        encodeKColumn.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKColumn;
    }
    
    public static KValTextField encode(
        final KValTextField kValTextField,
        final KFormat kFormat
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(kFormat, "kFormat");
        
        final KValTextField encodeKColumn = kValTextField.cloneMe();
        
        encodeKColumn.sb.insert(0, "'").append("'").insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKColumn;
    }
    
    public static KColumn exp(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "EXP");
    }
    
    public static KColumn exp(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "EXP");
    }
    
    public static KColumn extract(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractkColumn = new KColumn(kColumn.sb);
        
        extractkColumn.sb.insert(0, " FROM ").insert(0, kExtractField.toSql()).insert(0, "EXTRACT(").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn floor(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "FLOOR");
    }
    
    public static KColumn floor(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "FLOOR");
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
        
        final KColumn genericTrimKColumn = new KColumn();
        
        genericTrimKColumn.sb.append(trimFunctionName).append("(");
        genericTrimKColumn.sb.append(kColumn.sb);
                
        if (characters != null) {
            genericTrimKColumn.sb.append(", '").append(characters).append("'");
        }
                
        genericTrimKColumn.sb.append(")");
        
        return genericTrimKColumn;
    }
    
    private static KColumn genericTrim(
        final String trimFunctionName,
        final KValTextField kValTextField,
        final String characters
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn genericTrimKColumn = new KColumn();
        
        genericTrimKColumn.sb.append(trimFunctionName).append("(").append("'").append(kValTextField.sb).append("'");
                
        if (characters != null) {
            genericTrimKColumn.sb.append(", '").append(characters).append("'");
        }
                
        genericTrimKColumn.sb.append(")");
        
        return genericTrimKColumn;
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
        final KBaseColumn... KBaseColumns
    ) {
        assertNotNull(KBaseColumns, "KBaseColumns");
        
        if (KBaseColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'GREATEST' function requires at least two KBaseColumns");
        }
        
        final KColumn greatestkColumn = new KColumn();
        
        boolean first = true;
        
        greatestkColumn.sb.append("GREATEST(");
        
        for (final KBaseColumn kBaseColumn : KBaseColumns) {
            if (kBaseColumn == null) {
                continue;
            }
            
            if (!first) {
                greatestkColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kBaseColumn instanceof KValTextField) {
                greatestkColumn.sb.append("'");
            }
            
            greatestkColumn.sb.append(kBaseColumn.sb);
            
            if (kBaseColumn instanceof KValTextField) {
                greatestkColumn.sb.append("'");
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
    
    public static KValTextField isolate(
        final KValTextField kValTextField
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField isolateKValTextField = kValTextField.cloneMe();
        
        isolateKValTextField.sb.insert(0, "(").append(")");
        
        return isolateKValTextField;
    }
    
    public static KValNumberField isolate(
        final KValNumberField kValNumberField
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField isolateKValNumberField = kValNumberField.cloneMe();
        
        isolateKValNumberField.sb.insert(0, "(").append(")");
        
        return isolateKValNumberField;
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
        final KBaseColumn... KBaseColumns
    ) {
        assertNotNull(KBaseColumns, "KBaseColumns");
        
        if (KBaseColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'LEAST' function requires at least two KBaseColumns");
        }
        
        final KColumn greatestkColumn = new KColumn();
        
        boolean first = true;
        
        greatestkColumn.sb.append("LEAST(");
        
        for (final KBaseColumn kBaseColumn : KBaseColumns) {
            if (kBaseColumn == null) {
                continue;
            }
            
            if (!first) {
                greatestkColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kBaseColumn instanceof KValTextField) {
                greatestkColumn.sb.append("'");
            }
            
            greatestkColumn.sb.append(kBaseColumn.sb);
            
            if (kBaseColumn instanceof KValTextField) {
                greatestkColumn.sb.append("'");
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
        
        leftkColumn.sb.append("LEFT(").append(kColumn.sb).append(", ").append(n).append(")");
        return leftkColumn;
    }
    
    public static KColumn left(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn leftkColumn = new KColumn();
        
        leftkColumn.sb.append("LEFT(").append("'").append(kValTextField.sb).append("'").append(", ").append(n).append(")");
        
        return leftkColumn;
    }
    
    public static KColumn length(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LENGTH");
    }
    
    public static KColumn length(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "LENGTH");
    }
    
    public static KColumn ln(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LN");
    }
    
    public static KColumn ln(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "LN");
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
        return applyTwoParameterFunction(kColumn1, kColumn2, "LOG");
    }
    
    public static KColumn log(
        final Number number,
        final KColumn kColumn
    ) {
        return applyTwoParameterFunction(val(number), kColumn, "LOG");
    }
    
    public static KColumn log(
        final KColumn kColumn,
        final Number number
    ) {
        return applyTwoParameterFunction(kColumn, val(number), "LOG");
    }
    
    public static KValNumberField log(
        final Number number1,
        final Number number2
    ) {
        return applyTwoParameterFunction(val(number1), val(number2), "LOG");
    }
    
    public static KColumn log10(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LOG10");
    }
    
    public static KColumn log10(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "LOG10");
    }
    
    public static KColumn lower(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LOWER");
    }
    
    public static KColumn lower(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "LOWER");
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
        
        final KColumn lpadKColumn = new KColumn();
        
        lpadKColumn.sb.append("LPAD(").append(kColumn.sb).append(", ").append(n);
                
        if (fillText != null) {
            lpadKColumn.sb.append(", '").append(fillText).append("'");
        }
                
        lpadKColumn.sb.append(")");
        
        return lpadKColumn;
    }
    
    public static KColumn lpad(
        final KValTextField kValTextField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn lpadKColumn = new KColumn();
        
        lpadKColumn.sb.append("LPAD(").append("'").append(kValTextField.sb).append("'").append(", ").append(n);
                
        if (fillText != null) {
            lpadKColumn.sb.append(", '").append(fillText).append("'");
        }
                
        lpadKColumn.sb.append(")");
        
        return lpadKColumn;
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
        return applyOneParameterFunction(kColumn, "MD5");
    }
    
    public static KColumn md5(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "MD5");
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
    
    public static KValNumberField mod(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "%");
    }
    
    public static KValNumberField mod(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "%");
    }
    
    public static KValNumberField mod(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "%");
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
    
    public static KValNumberField mul(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "*");
    }
    
    public static KValNumberField mul(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "*");
    }
    
    public static KValNumberField mul(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "*");
    }
    
    public static KColumn now() {
        return new KColumn("NOW()");
    }
    
    public static KColumn nullif(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        
        assertNotNull(kBaseColumn1, "kBaseColumn1");
        assertNotNull(kBaseColumn2, "kBaseColumn2");
        
        final KColumn nullifkColumn = new KColumn();
        
        nullifkColumn.sb.append("NULLIF(");
        
        if (kBaseColumn1 instanceof KValTextField) {
            nullifkColumn.sb.append("'");
        }
        
        nullifkColumn.sb.append(kBaseColumn1.sb);
        
        if (kBaseColumn1 instanceof KValTextField) {
            nullifkColumn.sb.append("'");
        }
        
        nullifkColumn.sb.append(", ");
        
        if (kBaseColumn2 instanceof KValTextField) {
            nullifkColumn.sb.append("'");
        }
        
        nullifkColumn.sb.append(kBaseColumn2.sb);
        
        if (kBaseColumn2 instanceof KValTextField) {
            nullifkColumn.sb.append("'");
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
        
        overlaykColumn.sb.append("OVERLAY(").append(kColumn.sb).append(" PLACING '").append(value).append("'").append(" from ").append(from);
        
        if (for_ != null) {
            overlaykColumn.sb.append(" for ").append(for_);
        }
                
        overlaykColumn.sb.append(")");
        
        return overlaykColumn;
    }
    
    public static KColumn overlay(
        final KValTextField kValTextField,
        final String value,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(value, "value");
        
        final KColumn overlaykColumn = new KColumn();
        
        overlaykColumn.sb.append("OVERLAY(").append("'").append(kValTextField.sb).append("'").append(" PLACING '").append(value).append("'").append(" from ").append(from);
        
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
        
        positionkColumn.sb.append("POSITION('").append(valueToLocate).append("'").append(" in ").append(kColumn.sb).append(")");
        
        return positionkColumn;
    }
    
    public static KColumn position(
        final KValTextField kValTextField,
        final String valueToLocate
    ) {
        
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(valueToLocate, "valueToLocate");
        
        final KColumn positionkColumn = new KColumn();
        
        positionkColumn.sb.append("POSITION('").append(valueToLocate).append("'").append(" in ").append("'").append(kValTextField.sb).append("'").append(")");
        
        return positionkColumn;
    }
    
    public static KColumn power(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(kColumn1, kColumn2, "POWER");
    }
    
    public static KColumn power(
        final Number number,
        final KColumn kColumn
    ) {
        return applyTwoParameterFunction(val(number), kColumn, "POWER");
    }
    
    public static KColumn power(
        final KColumn kColumn,
        final Number number
    ) {
        return applyTwoParameterFunction(kColumn, val(number), "POWER");
    }
    
    public static KValNumberField power(
        final Number number1,
        final Number number2
    ) {
        return applyTwoParameterFunction(val(number1), val(number2), "POWER");
    }
    
    public static KColumn radians(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "RADIANS");
    }
    
    public static KColumn radians(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "RADIANS");
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
        
        repeatkColumn.sb.append("REPEAT(").append(kColumn.sb).append(", ").append(n).append(")");
        
        return repeatkColumn;
    }
    
    public static KColumn repeat(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn repeatkColumn = new KColumn();
        
        repeatkColumn.sb.append("REPEAT(").append("'").append(kValTextField.sb).append("'").append(", ").append(n).append(")");
        
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
        final KValTextField kValTextField,
        final String pattern,
        final String replacement
    ) {
        return regexpReplace(kValTextField, pattern, replacement, null);
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
        
        regexpReplacekColumn.sb.append("REGEXP_REPLACE(").append(kColumn.sb).append(", '").append(pattern).append("', '").append(replacement).append("'");
        
        if (flags != null) {
            regexpReplacekColumn.sb.append(", '").append(flags).append("'");
        }
        
        regexpReplacekColumn.sb.append(")");
        
        return regexpReplacekColumn;
    }
    
    public static KColumn regexpReplace(
        final KValTextField kValTextField,
        final String pattern,
        final String replacement,
        final String flags
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(pattern, "pattern");
        assertNotNull(replacement, "replacement");
        
        final KColumn regexpReplacekColumn = new KColumn();
        
        regexpReplacekColumn.sb.append("REGEXP_REPLACE(").append("'").append(kValTextField.sb).append("'").append(", '").append(pattern).append("', '").append(replacement).append("'");
        
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
        
        replacekColumn.sb.append("REPLACE(").append(kColumn.sb).append(", '").append(from).append("', '").append(to).append("'").append(")");
        
        return replacekColumn;
    }
    
    public static KColumn replace(
        final KValTextField kValTextField,
        final String from,
        final String to
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn replacekColumn = new KColumn();
        
        replacekColumn.sb.append("REPLACE(").append("'").append(kValTextField.sb).append("'").append(", '").append(from).append("', '").append(to).append("'").append(")");
        
        return replacekColumn;
    }
    
    public static KColumn reverse(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "REVERSE");
    }
    
    public static KColumn reverse(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "REVERSE");
    }
    
    public static KColumn right(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn leftkColumn = new KColumn();
        
        leftkColumn.sb.append("RIGHT(").append(kColumn.sb).append(", ").append(n).append(")");
        
        return leftkColumn;
    }
    
    public static KColumn right(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn leftkColumn = new KColumn();
        
        leftkColumn.sb.append("RIGHT(").append("'").append(kValTextField.sb).append("'").append(", ").append(n).append(")");
        
        return leftkColumn;
    }
    
    public static KColumn round(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ROUND");
    }
    
    public static KColumn round(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(kColumn1, kColumn2, "ROUND");
    }
    
    public static KColumn round(
        final Number number,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(val(number), kColumn2, "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn1,
        final Number number
    ) {
        return applyTwoParameterFunction(kColumn1, val(number), "ROUND");
    }
    
    public static KValNumberField round(
        final Number number1,
        final Number number2
    ) {
        return applyTwoParameterFunction(val(number1), val(number2), "ROUND");
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
        
        rpadkColumn.sb.append("RPAD(").append(kColumn.sb).append(", ").append(n);
                
        if (fillText != null) {
            rpadkColumn.sb.append(", '").append(fillText).append("'");
        }
                
        rpadkColumn.sb.append(")");
        
        return rpadkColumn;
    }
    
    public static KColumn rpad(
        final KValTextField kValTextField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KColumn rpadKColumn = new KColumn();
        
        rpadKColumn.sb.append("RPAD(").append("'").append(kValTextField.sb).append("'").append(", ").append(n);
                
        if (fillText != null) {
            rpadKColumn.sb.append(", '").append(fillText).append("'");
        }
                
        rpadKColumn.sb.append(")");
        
        return rpadKColumn;
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
        return applyOneParameterFunction(kColumn, "SIGN");
    }
    
    public static KColumn sign(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SIGN");
    }
    
    public static KColumn sin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SIN");
    }
    
    public static KColumn sin(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SIN");
    }
    
    public static KColumn sinh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SINH");
    }
    
    public static KColumn sinh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SINH");
    }
    
    public static KColumn splitPart(
        final KColumn kColumn,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(delimiter, "delimiter");
        
        final KColumn splitPartkColumn = new KColumn();
        
        splitPartkColumn.sb.append("SPLIT_PART(").append(kColumn.sb).append(", '").append(delimiter).append("', ").append(field).append(")");
        
        return splitPartkColumn;
    }
    
    public static KColumn splitPart(
        final KValTextField kValTextField,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(delimiter, "delimiter");
        
        final KColumn splitPartkColumn = new KColumn();
        
        splitPartkColumn.sb.append("SPLIT_PART(").append("'").append(kValTextField.sb).append("'").append(", '").append(delimiter).append("', ").append(field).append(")");
        
        return splitPartkColumn;
    }
    
    public static KColumn sqrt(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SQRT");
    }
    
    public static KColumn sqrt(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SQRT");
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final Integer from
    ) {
        return substring(kColumn, from, null);
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final Integer from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kColumn, "kColumn");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least one is required");
        }
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(").append(kColumn.sb);
        
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
        final KValTextField kValTextField,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least one is required");
        }
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(").append("'").append(kValTextField.sb).append("'");
        
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
        final KValTextField kValTextField,
        final String from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final String from,
        final String for_
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(").append(kColumn.sb).append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringkColumn.sb.append(" for '").append(for_).append("'");
        }
        
        substringkColumn.sb.append(")");
        
        return substringkColumn;
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final String from,
        final String for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("SUBSTRING(").append("'").append(kValTextField.sb).append("'").append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringkColumn.sb.append(" for '").append(for_).append("'");
        }
        
        substringkColumn.sb.append(")");
        
        return substringkColumn;
    }
    
    public static KColumn tan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TAN");
    }
    
    public static KColumn tan(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TAN");
    }
    
    public static KColumn tanh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TANH");
    }
    
    public static KColumn tanh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TANH");
    }
    
    public static KColumn toDate(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_DATE(").append(kColumn.sb).append(", '").append(format).append("'").append(")");
        
        return toDatekColumn;
    }
    
    public static KColumn toDate(
        final KValTextField kValTextField,
        final String format
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_DATE(").append("'").append(kValTextField.sb).append("'").append(", '").append(format).append("'").append(")");
        
        return toDatekColumn;
    }
    
    public static KColumn toHex(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TO_HEX");
    }
    
    public static KColumn toHex(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TO_HEX");
    }
    
    public static KColumn toTimestamp(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_TIMESTAMP(").append(kColumn.sb).append(", '").append(format).append("'").append(")");
        
        return toDatekColumn;
    }
    
    public static KColumn toTimestamp(
        final KValTextField kValTextField,
        final String format
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(format, "format");
        
        final KColumn toDatekColumn = new KColumn();
        
        toDatekColumn.sb.append("TO_TIMESTAMP(").append("'").append(kValTextField.sb).append("'").append(", '").append(format).append("'").append(")");
        
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
        
        translatekColumn.sb.append("TRANSLATE(").append(kColumn.sb).append(", '").append(from).append("', '").append(to).append("')");
        
        return translatekColumn;
    }
    
    public static KColumn translate(
        final KValTextField kValTextField,
        final String from,
        final String to
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn translatekColumn = new KColumn();
        
        translatekColumn.sb.append("TRANSLATE(").append("'").append(kValTextField.sb).append("'").append(", '").append(from).append("', '").append(to).append("')");
        
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
    
    public static KValNumberField sub(
        final KValNumberField kValNumberField,
        final Number number
    ) {
        return applyBinaryOperator(kValNumberField, number, "-");
    }
    
    public static KValNumberField sub(
        final Number number,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(number, kValNumberField, "-");
    }
    
    public static KValNumberField sub(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "-");
    }
    
    public static KColumn toChar(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("TO_CHAR(").append(kColumn.sb).append(", '").append(format).append("'").append(")");
        
        return substringkColumn;
    }
    
    public static KColumn toChar(
        final KValNumberField kValNumberField,
        final String format
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        assertNotNull(format, "format");
        
        final KColumn substringkColumn = new KColumn();
        
        substringkColumn.sb.append("TO_CHAR(").append(kValNumberField.sb).append(", '").append(format).append("'").append(")");
        
        return substringkColumn;
    }
    
    public static KColumn trunc(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TRUNC");
    }
    
    public static KColumn trunc(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TRUNC");
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
        return applyOneParameterFunction(kColumn, "UPPER");
    }
    
    public static KColumn upper(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "UPPER");
    }
    
    public static KValTextField val(
        final String val
    ) {
        return new KValTextField(val);
    }
    
    public static KValNumberField val(
        final Number val
    ) {
        return new KValNumberField(val);
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
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op.sb).append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count.sb).append(")");
        
        return widthBucketkColumn;
    }
    
    public static KColumn widthBucket(
        final KColumn op,
        final KColumn b1,
        final KColumn b2,
        final int count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op.sb).append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count).append(")");
        
        return widthBucketkColumn;
    }
    
    public static KColumn widthBucket(
        final KColumn op,
        final Number b1,
        final Number b2,
        final int count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op.sb).append(", ").append(b1).append(", ").append(b2).append(", ").append(count).append(")");
        
        return widthBucketkColumn;
    }
    
    public static KColumn widthBucket(
        final Number op,
        final Number b1,
        final Number b2,
        final int count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op).append(", ").append(b1).append(", ").append(b2).append(", ").append(count).append(")");
        
        return widthBucketkColumn;
    }
    
    public static KColumn widthBucket(
        final Number op,
        final Number b1,
        final Number b2,
        final KColumn count
    ) {
        
        assertNotNull(op, "op");
        assertNotNull(b1, "b1");
        assertNotNull(b2, "b2");
        assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op).append(", ").append(b1).append(", ").append(b2).append(", ").append(count.sb).append(")");
        
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