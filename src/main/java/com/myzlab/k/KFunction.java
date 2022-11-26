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
    
//    public static KAliasedColumn as(
//        final Number val,
//        final String alias
//    ) {
//        assertNotNull(val, "val");
//                
//        return new KAliasedColumn(val(val), alias);
//    }
//    
//    public static KAliasedColumn as(
//        final String val,
//        final String alias
//    ) {
//        assertNotNull(val, "val");
//                
//        return new KAliasedColumn(val(val), alias);
//    }
    
    public static KColumn abs(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ABS");
    }
    
    public static KValNumberField abs(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ABS");
    }
    
    public static KValNumberField abs(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "ABS");
    }
    
    public static KColumn acos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ACOS");
    }
    
    public static KValNumberField acos(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ACOS");
    }
    
    public static KValNumberField acos(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "ACOS");
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
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) {
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "+");
    }
    
    public static KColumn add(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "+");
    }
    
    public static KColumn add(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "+");
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn,
        final Number number,
        final String operator
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(number, "number");
        
        return applyBinaryOperator(kColumn, new KValNumberField(number), operator);
    }
    
    private static KColumn applyBinaryOperator(
        final Number number,
        final KColumn kColumn,
        final String operator
    ) {
        assertNotNull(number, "number");
        assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(new KValNumberField(number), kColumn, operator);
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        assertNotNull(kColumn1, "kColumn1");
        assertNotNull(kColumn2, "kColumn2");
        
        final KColumn operationKColumn = new KColumn(kColumn1.sb, kColumn1.params, false);
        
        if (!kColumn1.closed) {
            operationKColumn.sb.insert(0, "(").append(")");
        }
        
        final boolean closeNextParam = !kColumn2.closed;
        
        operationKColumn.sb.append(" ").append(operator).append(" ").append(closeNextParam ? "(" : "").append(kColumn2.sb).append(closeNextParam ? ")" : "");
        operationKColumn.params.addAll(kColumn2.params);
        
        return operationKColumn;
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
        
        final KValNumberField newKValNumberField = new KValNumberField(kValNumberField1.sb, kValNumberField1.params, false);
        
        if (!kValNumberField1.closed) {
            newKValNumberField.sb.insert(0, "(").append(")");
        }
        
        final boolean closeNextParam = !kValNumberField2.closed;
        
        newKValNumberField.sb.append(" ").append(operator).append(" ").append(closeNextParam ? "(" : "").append(kValNumberField2.sb).append(closeNextParam ? ")" : "");
        newKValNumberField.params.addAll(kValNumberField2.params);
        
        return newKValNumberField;
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn,
        final KValNumberField kValNumberField,
        final String operator
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KColumn operationKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        if (!kColumn.closed) {
            operationKColumn.sb.insert(0, "(").append(")");
        }
        
        final boolean closeNextParam = !kValNumberField.closed;
        
        operationKColumn.sb.append(" ").append(operator).append(" ").append(closeNextParam ? "(" : "").append(kValNumberField.sb).append(closeNextParam ? ")" : "");
        operationKColumn.params.addAll(kValNumberField.params);
        
        return operationKColumn;
    }
    
    private static KColumn applyBinaryOperator(
        final KValNumberField kValNumberField,
        final KColumn kColumn,
        final String operator
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        assertNotNull(kColumn, "kColumn");
        
        final KColumn operationKColumn = new KColumn(kValNumberField.sb, kValNumberField.params, false);
        
        if (!kValNumberField.closed) {
            operationKColumn.sb.insert(0, "(").append(")");
        }
        
        final boolean closeNextParam = !kColumn.closed;
        
        operationKColumn.sb.append(" ").append(operator).append(" ").append(closeNextParam ? "(" : "").append(kColumn.sb).append(closeNextParam ? ")" : "");
        operationKColumn.params.addAll(kColumn.params);
        
        return operationKColumn;
    }
    
    private static KColumn applyUnaryOperator(
        final KColumn kColumn,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn operationKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        if (!kColumn.closed) {
            operationKColumn.sb.insert(0, "(").append(")");
        }
        
        operationKColumn.sb.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        
        return operationKColumn;
    }
    
    private static KValNumberField applyUnaryOperator(
        final KValNumberField kValNumberField,
        final String operator,
        final boolean addToRightSide
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField operationKValNumberField = new KValNumberField(kValNumberField.sb, kValNumberField.params, false);
        
        if (!kValNumberField.closed) {
            operationKValNumberField.sb.insert(0, "(").append(")");
        }
        
        operationKValNumberField.sb.insert(0, addToRightSide ? "" : operator).append(addToRightSide ? operator : "");
        
        return operationKValNumberField;
    }
    
    private static KColumn applyOneParameterFunction(
        final KColumn kColumn,
        final String functionName
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn functionKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKColumn;
    }
    
    private static KValNumberField applyOneParameterFunction(
        final KValNumberField kValNumberField,
        final String functionName
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField functionKValNumberField = new KValNumberField(kValNumberField.sb, kValNumberField.params, true);
        
        functionKValNumberField.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKValNumberField;
    }
    
    private static KValTextField applyOneParameterFunction(
        final KValTextField kValTextField,
        final String functionName
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField functionKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        functionKValTextField.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKValTextField;
    }
    
    private static KColumn applyTwoParameterFunction(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2,
        final String functionName
    ) {
        assertNotNull(kBaseColumn1, "kBaseColumn1");
        assertNotNull(kBaseColumn2, "kBaseColumn2");
        
        final KColumn functionKColumn = new KColumn(kBaseColumn1.sb, kBaseColumn1.params, true);
        
        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kBaseColumn2.sb).append(")");
        functionKColumn.params.addAll(kBaseColumn2.params);
        
        return functionKColumn;
    }
    
//    private static KColumn applyTwoParameterFunction(
//        final KColumn kColumn1,
//        final KColumn kColumn2,
//        final String functionName
//    ) {
//        assertNotNull(kColumn1, "kColumn1");
//        assertNotNull(kColumn2, "kColumn2");
//        
//        final KColumn functionKColumn = new KColumn(kColumn1.sb, kColumn1.params, kColumn1.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kColumn2.sb).append(")");
//        functionKColumn.params.addAll(kColumn2.params);
//        functionKColumn.operating += kColumn2.operating;
//        
//        return functionKColumn;
//    }
//    
//    private static KColumn applyTwoParameterFunction(
//        final KValNumberField kValNumberField,
//        final KColumn kColumn,
//        final String functionName
//    ) {
//        assertNotNull(kValNumberField, "kValNumberField");
//        assertNotNull(kColumn, "kColumn");
//        
//        final KColumn functionKColumn = new KColumn(kValNumberField.sb, kValNumberField.params, kValNumberField.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kColumn.sb).append(")");
//        functionKColumn.params.addAll(kColumn.params);
//        functionKColumn.operating += kColumn.operating;
//        
//        return functionKColumn;
//    }
//    
//    private static KColumn applyTwoParameterFunction(
//        final KColumn kColumn,
//        final KValNumberField kValNumberField,
//        final String functionName
//    ) {
//        assertNotNull(kColumn, "kColumn");
//        assertNotNull(kValNumberField, "kValNumberField");
//        
//        final KColumn functionKColumn = new KColumn(kColumn.sb, kColumn.params, kColumn.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kValNumberField.sb).append(")");
//        functionKColumn.params.addAll(kValNumberField.params);
//        functionKColumn.operating += kValNumberField.operating;
//        
//        return functionKColumn;
//    }
//    
    private static KValNumberField applyTwoParameterFunction(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2,
        final String functionName
    ) {
        
        assertNotNull(kValNumberField1, "kValNumberField1");
        assertNotNull(kValNumberField2, "kValNumberField2");
        
        final KValNumberField functionKValNumberField = new KValNumberField(kValNumberField1.sb, kValNumberField1.params, true);
        
        functionKValNumberField.sb.insert(0, "(").insert(0, functionName).append(", ").append(kValNumberField2.sb).append(")");
        functionKValNumberField.params.addAll(kValNumberField2.params);
        
        return functionKValNumberField;
    }
//    
//    private static KColumn applyTwoParameterFunction(
//        final KValTextField kValTextField,
//        final KColumn kColumn,
//        final String functionName
//    ) {
//        assertNotNull(kValTextField, "kValTextField");
//        assertNotNull(kColumn, "kColumn");
//        
//        final KColumn functionKColumn = new KColumn(kValTextField.sb, kValTextField.params, kValTextField.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kColumn.sb).append(")");
//        functionKColumn.params.addAll(kColumn.params);
//        functionKColumn.operating += kColumn.operating;
//        
//        return functionKColumn;
//    }
//    
//    private static KColumn applyTwoParameterFunction(
//        final KColumn kColumn,
//        final KValTextField kValTextField,
//        final String functionName
//    ) {
//        assertNotNull(kColumn, "kColumn");
//        assertNotNull(kValTextField, "kValTextField");
//        
//        final KColumn functionKColumn = new KColumn(kColumn.sb, kColumn.params, kColumn.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kValTextField.sb).append(")");
//        functionKColumn.params.addAll(kValTextField.params);
//        functionKColumn.operating += kValTextField.operating;
//        
//        return functionKColumn;
//    }
    
    public static KColumn ascii(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ASCII");
    }
    
    public static KValTextField ascii(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "ASCII");
    }
    
    public static KValTextField ascii(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "ASCII");
    }
    
    public static KColumn asin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ASIN");
    }
    
    public static KValNumberField asin(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ASIN");
    }
    
    public static KValNumberField asin(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "ASIN");
    }
    
    public static KColumn atan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ATAN");
    }
    
    public static KValNumberField atan(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ATAN");
    }
    
    public static KValNumberField atan(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "ATAN");
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
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(kColumn, val(number), "ATAN2");
    }
    
    public static KColumn atan2(
        final Number number,
        final KColumn kColumn
    ) {
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "ATAN2");
    }
    
    public static KColumn atan2(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        
        return applyTwoParameterFunction(kColumn, kValNumberField, "ATAN2");
    }
    
    public static KColumn atan2(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) { 
        return applyTwoParameterFunction(kValNumberField, kColumn, "ATAN2");
    }
    
    public static KValNumberField atan2(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) { 
        return applyTwoParameterFunction(kValNumberField1, kValNumberField2, "ATAN2");
    }
    
    public static KValNumberField atan2(
        final Number number1,
        final Number number2
    ) {
        assertNotNull(number1, "number1");
        assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "ATAN2");
    }
    
    public static KColumn avg(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "AVG");
    }
    
    public static KValNumberField avg(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "AVG");
    }
    
    public static KValNumberField avg(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "AVG");
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
    
    public static KColumn bitAnd(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "&");
    }
    
    public static KColumn bitAnd(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "&");
    }
    
    public static KColumn bitNot(
        final KColumn kColumn
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyUnaryOperator(kColumn, "~", false);
    }
    
    public static KValNumberField bitNot(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyUnaryOperator(val(number), "~", false);
    }
    
    public static KValNumberField bitNot(
        final KValNumberField kValNumberField
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
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
    
    public static KColumn bitOr(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "|");
    }
    
    public static KColumn bitOr(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "|");
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
        
        return applyBinaryOperator(kColumn, n, "<<");
    }
    
    public static KValNumberField bitShiftLeft(
        final KValNumberField kValNumberField,
        final int n
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(kValNumberField, n, "<<");
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
        
        return applyBinaryOperator(kColumn, n, ">>");
    }
    
    public static KValNumberField bitShiftRight(
        final KValNumberField kValNumberField,
        final int n
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(kValNumberField, n, ">>");
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
    
    public static KColumn bitXor(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "#");
    }
    
    public static KColumn bitXor(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "#");
    }
    
    public static KColumn boolAnd(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        return processBoolAnd(kCondition);
    }
    
    public static KColumn boolOr(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        return processBoolOr(kCondition);
    }
    
    public static KColumn cast(
        final KBaseColumnCastable kBaseColumnCastable,
        final KDataType kDataType    
    ) {
        assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(kBaseColumnCastable.sb, kBaseColumnCastable.params, true);
        
        if (!kBaseColumnCastable.closed) {
            castkColumn.sb.insert(0, "(").append(")");
        }
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cast(
        final Number val,
        final KDataType kDataType    
    ) {
        assertNotNull(val, "val");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(val);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cast(
        final String val,
        final KDataType kDataType    
    ) {
        assertNotNull(val, "val");
        assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(val);
        
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
    
    public static KValNumberField cbrt(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CBRT");
    }
    
    public static KValNumberField cbrt(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "CBRT");
    }
    
    public static KColumn ceil(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CEIL");
    }
    
    public static KValNumberField ceil(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CEIL");
    }
    
    public static KValNumberField ceil(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "CEIL");
    }
    
    public static KColumn ceiling(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CEILING");
    }
    
    public static KValNumberField ceiling(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "CEILING");
    }
    
    public static KValNumberField ceiling(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "CEILING");
    }
    
    public static KColumn chr(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CHR");
    }
    
    public static KValTextField chr(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "CHR");
    }
    
    public static KValTextField chr(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "CHR");
    }
    
    public static KColumn coalesce(
        final KBaseColumnCastable... kBaseColumnCastables
    ) {
        assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
        if (kBaseColumnCastables.length < 2) {
            throw KExceptionHelper.internalServerError("'COALESCE' function requires at least two kBaseColumnCastables");
        }
        
        final KColumn coalesceKColumn = new KColumn();
        
        boolean first = true;
        
        coalesceKColumn.sb.append("COALESCE(");
        
        for (final KBaseColumnCastable kBaseColumnCastable : kBaseColumnCastables) {
            if (kBaseColumnCastable == null) {
                continue;
            }
            
            if (!first) {
                coalesceKColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            coalesceKColumn.sb.append(kBaseColumnCastable.sb);
            coalesceKColumn.params.addAll(kBaseColumnCastable.params);
        }
        
        coalesceKColumn.sb.append(")");
        
        return coalesceKColumn;
    }
    
    public static KColumn cos(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COS");
    }
    
    public static KValNumberField cos(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COS");
    }
    
    public static KValNumberField cos(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "COS");
    }
    
    public static KColumn cosh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COSH");
    }
    
    public static KValNumberField cosh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COSH");
    }
    
    public static KValNumberField cosh(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "COSH");
    }
    
    public static KColumn cot(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "COT");
    }
    
    public static KValNumberField cot(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "COT");
    }
    
    public static KValNumberField cot(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "COT");
    }
    
    public static KColumn count() {
        return new KColumn(new StringBuilder("COUNT(*)"), true);
    }
    
    public static KColumn count(
        final KColumn kColumn
    ) {
        return processCount(kColumn);
    }
    
    public static KColumn countDistinct(
        final KColumn kColumn
    ) {
        return processCountDistinct(kColumn);
    }
    
    public static KColumn concat(
        final KBaseColumnCastable... kBaseColumnCastables
    ) {
        assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
        if (kBaseColumnCastables.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT' function requires at least two kBaseColumnCastables");
        }
        
        final KColumn concatKColumn = new KColumn(false);
        
        boolean first = true;
        
        concatKColumn.sb.append("CONCAT(");
        
        for (final KBaseColumnCastable kBaseColumnCastable : kBaseColumnCastables) {
            if (kBaseColumnCastable == null) {
                continue;
            }
            
            if (!first) {
                concatKColumn.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            concatKColumn.sb.append(kBaseColumnCastable.sb);
            concatKColumn.params.addAll(kBaseColumnCastable.params);
        }
        
        concatKColumn.sb.append(")");
        
        return concatKColumn;
    }
    
    public static KCommonTableExpressionNamed cte(
        final String name
    ) {
        return KCommonTableExpressionNamed.getInstance(name);
    }
    
    public static KColumn currentDate() {
        return new KColumn(new StringBuilder("CURRENT_DATE"), true);
    }
    
    public static KColumn currentSchema() {
        return new KColumn(new StringBuilder("CURRENT_SCHEMA"), true);
    }
    
    public static KColumn currentTime() {
        return currentTime(null);
    }
    
    public static KColumn currentTime(
        final Integer precision
    ) {
        return new KColumn(new StringBuilder("CURRENT_TIME" + ((precision != null) ? "(" + precision + ")": "")), true);
    }
    
    public static KColumn currentTimestamp() {
        return currentTimestamp(null);
    }
    
    public static KColumn currentTimestamp(
        final Integer precision
    ) {
        return new KColumn(new StringBuilder("CURRENT_TIMESTAMP" + ((precision != null) ? "(" + precision + ")": "")), true);
    }
    
    public static KColumn currentUser() {
        return new KColumn(new StringBuilder("CURRENT_USER"), true);
    }
    
    public static KColumn datePart(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractkColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        extractkColumn.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_PART('").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn dateTrunc(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractkColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
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
        decodeKColumn.closed = true;
        
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
        decodeKValTextField.closed = true;
        
        decodeKValTextField.sb.insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return decodeKValTextField;
    }
    
    public static KColumn degrees(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "DEGREES");
    }
    
    public static KValNumberField degrees(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "DEGREES");
    }
    
    public static KValNumberField degrees(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "DEGREES");
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
        return applyBinaryOperator(kValNumberField1, kValNumberField2, "/");
    }
    
    public static KColumn div(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "/");
    }
    
    public static KColumn div(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "/");
    }
    
    public static KColumn encode(
        final KColumn kColumn,
        final KFormat kFormat
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kFormat, "kFormat");
        
        final KColumn encodeKColumn = kColumn.cloneMe();
        encodeKColumn.closed = true;
        
        encodeKColumn.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKColumn;
    }
    
    public static KValTextField encode(
        final KValTextField kValTextField,
        final KFormat kFormat
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(kFormat, "kFormat");
        
        final KValTextField encodeKValTextField = kValTextField.cloneMe();
        encodeKValTextField.closed = true;
        
        encodeKValTextField.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKValTextField;
    }
    
    public static KCondition exists(
        final KQuery kQuery
    ) {
        return KCondition.exists(kQuery);
    }
    
    public static KColumn exp(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "EXP");
    }
    
    public static KValNumberField exp(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "EXP");
    }
    
    public static KValNumberField exp(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "EXP");
    }
    
    public static KColumn extract(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        extractKColumn.sb.insert(0, " FROM ").insert(0, kExtractField.toSql()).insert(0, "EXTRACT(").append(")");
        
        return extractKColumn;
    }
    
    public static KColumn floor(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "FLOOR");
    }
    
    public static KValNumberField floor(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "FLOOR");
    }
    
    public static KValNumberField floor(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "FLOOR");
    }
    
    public static KColumn genRandomUuid() {
        return new KColumn(new StringBuilder("GEN_RANDOM_UUID()"), true);
    }
    
    private static KColumn genericTrim(
        final KColumn kColumn,
        final String characters,
        final String trimFunctionName
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn genericTrimKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        genericTrimKColumn.sb.insert(0, "(").insert(0, trimFunctionName);
                
        if (characters != null) {
            genericTrimKColumn.sb.append(", ").append("?");
            genericTrimKColumn.params.add(characters);
        }
                
        genericTrimKColumn.sb.append(")");
        
        return genericTrimKColumn;
    }
    
    private static KValTextField genericTrim(
        final KValTextField kValTextField,
        final String characters,
        final String trimFunctionName
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField genericTrimKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        genericTrimKValTextField.sb.insert(0, "(").insert(0, trimFunctionName);
                
        if (characters != null) {
            genericTrimKValTextField.sb.append(", ").append("?");
            genericTrimKValTextField.params.add(characters);
        }
                
        genericTrimKValTextField.sb.append(")");
        
        return genericTrimKValTextField;
    }
    
//    private static String getErrorMessageFunctionTextType(
//        final String functionName,
//        final KColumn kColumn
//    ) {
//        return getGenericErrorMessage(functionName, "function", "text", kColumn);
//    }
//    
//    private static String getErrorMessageFunctionNumberType(
//        final String functionName,
//        final KColumn kColumn
//    ) {
//        return getGenericErrorMessage(functionName, "function", "number", kColumn);
//    }
//    
//    private static String getErrorMessageOperatorTextType(
//        final String operatorName,
//        final KColumn kColumn
//    ) {
//        return getGenericErrorMessage(operatorName, "operator", "text", kColumn);
//    }
//    
//    private static String getErrorMessageOperatorNumberType(
//        final String operatorName,
//        final KColumn kColumn
//    ) {
//        return getGenericErrorMessage(operatorName, "operator", "number", kColumn);
//    }
//    
//    private static String getGenericErrorMessage(
//        final String name,
//        final String entity,
//        final String type,
//        final KColumn kColumn
//    ) {
//        return "The '" + name + "' " + entity + " only can be used with a column or with a 'val' of " + type + " type. Current value: [" 
//            + (type.equals("number") ? "'" : "") 
//            + kColumn.sb.toString() 
//            + (type.equals("number") ? "'" : "") 
//            + "]";
//    }
    
    public static KColumn getJsonArray(
        final KColumn kColumn,
        final int index
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" -> ").append(index);
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonArrayAsText(
        final KColumn kColumn,
        final int index
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" ->> ").append(index);
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObject(
        final KColumn kColumn,
        final String name
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(name, "name");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" -> '").append(name).append("'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAsText(
        final KColumn kColumn,
        final String name
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(name, "name");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" ->> '").append(name).append("'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAtPath(
        final KColumn kColumn,
        final String path
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(path, "path");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" #> '{").append(path).append("}'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAtPathAsText(
        final KColumn kColumn,
        final String path
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(path, "path");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" #>> '{").append(path).append("}'");
        
        return jsonKColumn;
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
            
            greatestkColumn.sb.append(kBaseColumn.sb);
            greatestkColumn.params.addAll(kBaseColumn.params);
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
    
    public static KColumn isolate(
        final KColumn kColumn
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn isolatekColumn = kColumn.cloneMe();
        isolatekColumn.closed = true;
        
        isolatekColumn.sb.insert(0, "(").append(")");
        
        return isolatekColumn;
    }
    
    public static KValTextField isolate(
        final KValTextField kValTextField
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField isolateKValTextField = kValTextField.cloneMe();
        isolateKValTextField.closed = true;
        
        isolateKValTextField.sb.insert(0, "(").append(")");
        
        return isolateKValTextField;
    }
    
    public static KValNumberField isolate(
        final KValNumberField kValNumberField
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField isolateKValNumberField = kValNumberField.cloneMe();
        isolateKValNumberField.closed = true;
        
        isolateKValNumberField.sb.insert(0, "(").append(")");
        
        return isolateKValNumberField;
    }
    
    public static KColumn least(
        final KBaseColumn... KBaseColumns
    ) {
        assertNotNull(KBaseColumns, "KBaseColumns");
        
        if (KBaseColumns.length < 2) {
            throw KExceptionHelper.internalServerError("'LEAST' function requires at least two KBaseColumns");
        }
        
        final KColumn leastKColumn = new KColumn();
        
        boolean first = true;
        
        leastKColumn.sb.append("LEAST(");
        
        for (final KBaseColumn kBaseColumn : KBaseColumns) {
            if (kBaseColumn == null) {
                continue;
            }
            
            if (!first) {
                leastKColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            leastKColumn.sb.append(kBaseColumn.sb);
            leastKColumn.params.addAll(kBaseColumn.params);
        }
        
        leastKColumn.sb.append(")");
        
        return leastKColumn;
    }
    
    public static KColumn left(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyTwoParameterFunction(kColumn, val(n), "LEFT");
    }
    
    public static KColumn left(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        return applyTwoParameterFunction(kValTextField, val(n), "LEFT");
    }
    
    public static KColumn length(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LENGTH");
    }
    
    public static KValTextField length(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "LENGTH");
    }
    
    public static KValTextField length(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "LENGTH");
    }
    
    public static KColumn ln(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LN");
    }
    
    public static KValNumberField ln(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "LN");
    }
    
    public static KValNumberField ln(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "LN");
    }
    
    public static KColumn localTime() {
        return localTime(null);
    }
    
    public static KColumn localTime(
        final Integer precision
    ) {
        return new KColumn(new StringBuilder("LOCALTIME" + ((precision != null) ? "(" + precision + ")": "")), true);
    }
    
    public static KColumn localTimestamp() {
        return localTimestamp(null);
    }
    
    public static KColumn localTimestamp(
        final Integer precision
    ) {
        return new KColumn(new StringBuilder("LOCALTIMESTAMP" + ((precision != null) ? "(" + precision + ")": "")), true);
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
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "LOG");
    }
    
    public static KColumn log(
        final KColumn kColumn,
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(kColumn, val(number), "LOG");
    }
    
    public static KColumn log(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) { 
        return applyTwoParameterFunction(kValNumberField, kColumn, "LOG");
    }
    
    public static KValNumberField log(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) { 
        return applyTwoParameterFunction(kValNumberField1, kValNumberField2, "LOG");
    }
    
    public static KValNumberField log(
        final Number number1,
        final Number number2
    ) {
        assertNotNull(number1, "number1");
        assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "LOG");
    }
    
    public static KColumn log(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyTwoParameterFunction(kColumn, kValNumberField, "LOG");
    }
    
    public static KColumn log10(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LOG10");
    }
    
    public static KValNumberField log10(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "LOG10");
    }
    
    public static KValNumberField log10(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "LOG10");
    }
    
    public static KColumn lower(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "LOWER");
    }
    
    public static KValTextField lower(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "LOWER");
    }
    
    public static KValTextField lower(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "LOWER");
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
        
        final KColumn lpadKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        lpadKColumn.sb.insert(0, "LPAD(").append(", ").append(n);
                
        if (fillText != null) {
            lpadKColumn.sb.append(", ?");
            lpadKColumn.params.add(fillText);
        }
                
        lpadKColumn.sb.append(")");
        
        return lpadKColumn;
    }
    
    public static KValTextField lpad(
        final KValTextField kValTextField,
        final int n
    ) {
        return lpad(kValTextField, n, null);
    }
    
    public static KValTextField lpad(
        final KValTextField kValTextField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField lpadKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        lpadKValTextField.sb.insert(0, "LPAD(").append(", ").append(n);
                
        if (fillText != null) {
            lpadKValTextField.sb.append(", ?");
            lpadKValTextField.params.add(fillText);
        }
                
        lpadKValTextField.sb.append(")");
        
        return lpadKValTextField;
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
        return genericTrim(kColumn, characters, "LTRIM");
    }
    
    public static KValTextField ltrim(
        final KValTextField kValTextField
    ) {
        return ltrim(kValTextField, null);
    }
    
    public static KValTextField ltrim(
        final KValTextField kValTextField,
        final String characters
    ) {
        return genericTrim(kValTextField, characters, "LTRIM");
    }
    
    public static KColumn max(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "MAX");
    }
    
    public static KValNumberField max(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "MAX");
    }
    
    public static KValNumberField max(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "MAX");
    }
    
    public static KColumn md5(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "MD5");
    }
    
    public static KValTextField md5(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "MD5");
    }
    
    public static KValTextField md5(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "MD5");
    }
    
    public static KColumn min(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "MIN");
    }
    
    public static KValNumberField min(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "MIN");
    }
    
    public static KValNumberField min(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "MIN");
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
    
    public static KColumn mod(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "%");
    }
    
    public static KColumn mod(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "%");
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
    
    public static KColumn mul(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "*");
    }
    
    public static KColumn mul(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "*");
    }
    
    public static KCondition notExists(
        final KQuery kQuery
    ) {
        return KCondition.notExists(kQuery);
    }
    
    public static KColumn now() {
        return new KColumn(new StringBuilder("NOW()"), true);
    }
    
    public static KColumn nullif(
        final KBaseColumnCastable kBaseColumnCastable1,
        final KBaseColumnCastable kBaseColumnCastable2
    ) {
        
        assertNotNull(kBaseColumnCastable1, "kBaseColumnCastable1");
        assertNotNull(kBaseColumnCastable2, "kBaseColumnCastable2");
        
        final KColumn nullifkColumn = new KColumn(kBaseColumnCastable1.sb, kBaseColumnCastable1.params, true);
        
        nullifkColumn.sb.insert(0, "NULLIF(").append(", ").append(kBaseColumnCastable2.sb).append(")");
        nullifkColumn.params.addAll(kBaseColumnCastable2.params);
        
        return nullifkColumn;
    }
    
    public static KCondition not(
        final KCondition kCondition
    ) {
        kCondition.sb.insert(0, "NOT (").append(")");
        
        return kCondition;
    }
    
    public static KColumn overlay(
        final KColumn kColumn,
        final String value,
        final int from
    ) {
        return overlay(kColumn, value, from, null);
    }
    
    public static KColumn overlay(
        final KColumn kColumn,
        final String value,
        final int from,
        final Integer for_
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(value, "value");
        
        final KColumn overlayKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        overlayKColumn.sb.insert(0, "OVERLAY(").append(" PLACING ?").append(" from ").append(from);
        overlayKColumn.params.add(value);
        
        if (for_ != null) {
            overlayKColumn.sb.append(" for ").append(for_);
        }
                
        overlayKColumn.sb.append(")");
        
        return overlayKColumn;
    }
    
    public static KValTextField overlay(
        final KValTextField kValTextField,
        final String value,
        final int from
    ) {
        return overlay(kValTextField, value, from, null);
    }
    
    public static KValTextField overlay(
        final KValTextField kValTextField,
        final String value,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(value, "value");
        
        final KValTextField overlayKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        overlayKValTextField.sb.insert(0, "OVERLAY(").append(" PLACING ?").append(" from ").append(from);
        overlayKValTextField.params.add(value);
        
        if (for_ != null) {
            overlayKValTextField.sb.append(" for ").append(for_);
        }
                
        overlayKValTextField.sb.append(")");
        
        return overlayKValTextField;
    }
    
    public static KColumn pi() {
        return new KColumn(new StringBuilder("PI()"), true);
    }
    
    public static KColumn position(
        final KColumn kColumn,
        final String valueToLocate
    ) {
        
        assertNotNull(kColumn, "kColumn");
        assertNotNull(valueToLocate, "valueToLocate");
        
        final KColumn positionKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        positionKColumn.sb.insert(0, "POSITION(? in ").append(")");
        positionKColumn.params.add(valueToLocate);
        
        return positionKColumn;
    }
    
    public static KValTextField position(
        final KValTextField kValTextField,
        final String valueToLocate
    ) {
        
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(valueToLocate, "valueToLocate");
        
        final KValTextField positionKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        positionKValTextField.sb.insert(0, "POSITION(? in ").append(")");
        positionKValTextField.params.add(valueToLocate);
        
        return positionKValTextField;
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
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "POWER");
    }
    
    public static KColumn power(
        final KColumn kColumn,
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(kColumn, val(number), "POWER");
    }
    
    public static KColumn power(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyTwoParameterFunction(kColumn, kValNumberField, "POWER");
    }
    
    public static KColumn power(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyTwoParameterFunction(kValNumberField, kColumn, "POWER");
    }
    
    public static KValNumberField power(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) { 
        return applyTwoParameterFunction(kValNumberField1, kValNumberField2, "POWER");
    }
    
    public static KValNumberField power(
        final Number number1,
        final Number number2
    ) {
        assertNotNull(number1, "number1");
        assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "POWER");
    }
    
    private static KColumn processBoolAnd(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        final StringBuilder sb = new StringBuilder();
        
        sb.append("BOOL_AND (").append(kCondition.sb).append(")");
        
        return new KColumn(sb, kCondition.params, false);
    }
    
    private static KColumn processBoolOr(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        final StringBuilder sb = new StringBuilder();
        
        sb.append("BOOL_OR (").append(kCondition.sb).append(")");
        
        return new KColumn(sb, kCondition.params, false);
    }
    
    private static KColumn processCount(
        final KColumn kColumn
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn kColumnCount = new KColumn(true);
        
        kColumnCount.sb.append("COUNT(").append(kColumn.sb).append(")");
        
        kColumnCount.params.addAll(kColumn.params);
        
        return kColumnCount;
    }
    
    private static KColumn processCountDistinct(
        final KColumn kColumn
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn kColumnCount = new KColumn(true);
        
        kColumnCount.sb.append("COUNT(DISTINCT ").append(kColumn.sb).append(")");
        
        kColumnCount.params.addAll(kColumn.params);
        
        return kColumnCount;
    }
    
    public static KColumn radians(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "RADIANS");
    }
    
    public static KValNumberField radians(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "RADIANS");
    }
    
    public static KValNumberField radians(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "RADIANS");
    }
    
    public static KColumn random() {
        return new KColumn(new StringBuilder("RANDOM()"), true);
    }
    
    public static KColumn rawColumn(
        final String content
    ) {
        return new KColumn(new StringBuilder(content), true);
    }
    
    public static KCondition rawCondition(
        final String content
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.sb.append(content);
        
        return kCondition;
    }
    
    public static KColumn repeat(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        final KColumn repeatKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        repeatKColumn.sb.insert(0, "REPEAT(").append(", ").append(n).append(")");
        
        return repeatKColumn;
    }
    
    public static KValTextField repeat(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField repeatKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        repeatKValTextField.sb.insert(0, "REPEAT(").append(", ").append(n).append(")");
        
        return repeatKValTextField;
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
        
        final KColumn regexpReplaceKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        regexpReplaceKColumn.sb.insert(0, "REGEXP_REPLACE(").append(", ?, ?");
        regexpReplaceKColumn.params.add(pattern);
        regexpReplaceKColumn.params.add(replacement);
        
        if (flags != null) {
            regexpReplaceKColumn.sb.append(", ?");
            regexpReplaceKColumn.params.add(flags);
        }
        
        regexpReplaceKColumn.sb.append(")");
        
        return regexpReplaceKColumn;
    }
    
    public static KValTextField regexpReplace(
        final KValTextField kValTextField,
        final String pattern,
        final String replacement
    ) {
        return regexpReplace(kValTextField, pattern, replacement, null);
    }
    
    public static KValTextField regexpReplace(
        final KValTextField kValTextField,
        final String pattern,
        final String replacement,
        final String flags
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(pattern, "pattern");
        assertNotNull(replacement, "replacement");
        
        final KValTextField regexpReplaceKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        regexpReplaceKValTextField.sb.insert(0, "REGEXP_REPLACE(").append(", ?, ?");
        regexpReplaceKValTextField.params.add(pattern);
        regexpReplaceKValTextField.params.add(replacement);
        
        if (flags != null) {
            regexpReplaceKValTextField.sb.append(", ?");
            regexpReplaceKValTextField.params.add(flags);
        }
        
        regexpReplaceKValTextField.sb.append(")");
        
        return regexpReplaceKValTextField;
    }
    
    public static KColumn replace(
        final KColumn kColumn,
        final String from,
        final String to
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn replaceKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        replaceKColumn.sb.insert(0, "REPLACE(").append(", ?, ?").append(")");
        replaceKColumn.params.add(from);
        replaceKColumn.params.add(to);
        
        return replaceKColumn;
    }
    
    public static KValTextField replace(
        final KValTextField kValTextField,
        final String from,
        final String to
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KValTextField replaceKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        replaceKValTextField.sb.insert(0, "REPLACE(").append(", ?, ?").append(")");
        replaceKValTextField.params.add(from);
        replaceKValTextField.params.add(to);
        
        return replaceKValTextField;
    }
    
    public static KColumn reverse(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "REVERSE");
    }
    
    public static KValTextField reverse(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "REVERSE");
    }
    
    public static KValTextField reverse(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "REVERSE");
    }
    
    public static KColumn right(
        final KColumn kColumn,
        final int n
    ) {
        assertNotNull(kColumn, "kColumn");
        
        return applyTwoParameterFunction(kColumn, val(n), "RIGHT");
    }
    
    public static KColumn right(
        final KValTextField kValTextField,
        final int n
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        return applyTwoParameterFunction(kValTextField, val(n), "RIGHT");
    }
    
    public static KColumn round(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ROUND");
    }
    
    public static KValNumberField round(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "ROUND");
    }
    
    public static KValNumberField round(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(kColumn1, kColumn2, "ROUND");
    }
    
    public static KColumn round(
        final Number number,
        final KColumn kColumn
    ) {
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn,
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyTwoParameterFunction(kColumn, val(number), "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyTwoParameterFunction(kColumn, kValNumberField, "ROUND");
    }
    
    public static KColumn round(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyTwoParameterFunction(kValNumberField, kColumn, "ROUND");
    }
    
    public static KValNumberField round(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2
    ) { 
        return applyTwoParameterFunction(kValNumberField1, kValNumberField2, "ROUND");
    }
    
    public static KValNumberField round(
        final Number number1,
        final Number number2
    ) {
        assertNotNull(number1, "number1");
        assertNotNull(number2, "number2");
        
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
        
        final KColumn rpadKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        rpadKColumn.sb.insert(0, "RPAD(").append(", ").append(n);
                
        if (fillText != null) {
            rpadKColumn.sb.append(", ?");
            rpadKColumn.params.add(fillText);
        }
                
        rpadKColumn.sb.append(")");
        
        return rpadKColumn;
    }
    
    public static KValTextField rpad(
        final KValTextField kValTextField,
        final int n
    ) {
        return rpad(kValTextField, n, null);
    }
    
    public static KValTextField rpad(
        final KValTextField kValTextField,
        final int n,
        final String fillText
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField rpadKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        rpadKValTextField.sb.insert(0, "RPAD(").append(", ").append(n);
                
        if (fillText != null) {
            rpadKValTextField.sb.append(", ?");
            rpadKValTextField.params.add(fillText);
        }
                
        rpadKValTextField.sb.append(")");
        
        return rpadKValTextField;
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
        return genericTrim(kColumn, characters, "RTRIM");
    }
    
    public static KValTextField rtrim(
        final KValTextField kValTextField
    ) {
        return rtrim(kValTextField, null);
    }
    
    public static KValTextField rtrim(
        final KValTextField kValTextField,
        final String characters
    ) {
        return genericTrim(kValTextField, characters, "RTRIM");
    }
    
    public static KColumn sign(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SIGN");
    }
    
    public static KValNumberField sign(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SIGN");
    }
    
    public static KValNumberField sign(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SIGN");
    }
    
    public static KColumn sin(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SIN");
    }
    
    public static KValNumberField sin(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SIN");
    }
    
    public static KValNumberField sin(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SIN");
    }
    
    public static KColumn sinh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SINH");
    }
    
    public static KValNumberField sinh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SINH");
    }
    
    public static KValNumberField sinh(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SINH");
    }
    
    public static KColumn splitPart(
        final KColumn kColumn,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(delimiter, "delimiter");
        
        final KColumn splitPartKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        splitPartKColumn.sb.insert(0, "SPLIT_PART(").append(", ?, ").append(field).append(")");
        splitPartKColumn.params.add(delimiter);
        
        return splitPartKColumn;
    }
    
    public static KValTextField splitPart(
        final KValTextField kValTextField,
        final String delimiter,
        final int field
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(delimiter, "delimiter");
        
        final KValTextField splitPartKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        splitPartKValTextField.sb.insert(0, "SPLIT_PART(").append(", ?, ").append(field).append(")");
        splitPartKValTextField.params.add(delimiter);
        
        return splitPartKValTextField;
    }
    
    public static KColumn sqrt(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SQRT");
    }
    
    public static KValNumberField sqrt(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SQRT");
    }
    
    public static KValNumberField sqrt(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SQRT");
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
            throw KExceptionHelper.internalServerError("Between 'from' and 'for_', at least one is required");
        }
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(");
        
        if (from != null) {
            substringKColumn.sb.append(" from ?");
            substringKColumn.params.add(from);
        }
        
        if (for_ != null) {
            substringKColumn.sb.append(" for ?");
            substringKColumn.params.add(for_);
        }
                
        substringKColumn.sb.append(")");
        
        return substringKColumn;
    }
    
    public static KValTextField substring(
        final String value,
        final Integer from
    ) {
        assertNotNull(value, "value");
        
        return substring(val(value), from, null);
    }
    
    public static KValTextField substring(
        final KValTextField kValTextField,
        final Integer from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KValTextField substring(
        final String value,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(value, "value");
        
        return substring(val(value), from, for_);
    }
    
    public static KValTextField substring(
        final KValTextField kValTextField,
        final Integer from,
        final Integer for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least one is required");
        }
        
        final KValTextField substringKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        substringKValTextField.sb.insert(0, "SUBSTRING(");
        
        if (from != null) {
            substringKValTextField.sb.append(" from ?");
            substringKValTextField.params.add(from);
        }
        
        if (for_ != null) {
            substringKValTextField.sb.append(" for ?");
            substringKValTextField.params.add(for_);
        }
                
        substringKValTextField.sb.append(")");
        
        return substringKValTextField;
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
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(").append(" from ?");
        substringKColumn.params.add(from);
        
        if (for_ != null) {
            substringKColumn.sb.append(" for ?");
            substringKColumn.params.add(for_);
        }
        
        substringKColumn.sb.append(")");
        
        return substringKColumn;
    }
    
    public static KValTextField substring(
        final String value,
        final String from
    ) {
        assertNotNull(value, "value");
        
        return substring(val(value), from);
    }
    
    public static KValTextField substring(
        final KValTextField kValTextField,
        final String from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KValTextField substring(
        final String value,
        final String from,
        final String for_
    ) {
        assertNotNull(value, "value");
        
        return substring(val(value), from, for_);
    }
    
    public static KValTextField substring(
        final KValTextField kValTextField,
        final String from,
        final String for_
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        
        final KValTextField substringKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        substringKValTextField.sb.insert(0, "SUBSTRING(").append(" from ?");
        substringKValTextField.params.add(from);
        
        if (for_ != null) {
            substringKValTextField.sb.append(" for ?");
            substringKValTextField.params.add(for_);
        }
        
        substringKValTextField.sb.append(")");
        
        return substringKValTextField;
    }
    
    public static KColumn sum(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SUM");
    }
    
    public static KValNumberField sum(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "SUM");
    }
    
    public static KValNumberField sum(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SUM");
    }
    
    public static KColumn tan(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TAN");
    }
    
    public static KValNumberField tan(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TAN");
    }
    
    public static KValNumberField tan(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TAN");
    }
    
    public static KColumn tanh(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TANH");
    }
    
    public static KValNumberField tanh(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TANH");
    }
    
    public static KValNumberField tanh(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TANH");
    }
    
    public static KColumn toDate(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDateKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        toDateKColumn.sb.insert(0, "TO_DATE(").append(", ?").append(")");
        toDateKColumn.params.add(format);
        
        return toDateKColumn;
    }
    
    public static KValTextField toDate(
        final KValTextField kValTextField,
        final String format
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(format, "format");
        
        final KValTextField toDateKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        toDateKValTextField.sb.insert(0, "TO_DATE(").append(", ?").append(")");
        toDateKValTextField.params.add(format);
        
        return toDateKValTextField;
    }
    
    public static KColumn toHex(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TO_HEX");
    }
    
    public static KValNumberField toHex(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TO_HEX");
    }
    
    public static KValNumberField toHex(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TO_HEX");
    }
    /*
    public static KColumn toDate(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toDateKColumn = new KColumn(kColumn.sb, kColumn.params, kColumn.operating + 1, true);
        
        toDateKColumn.sb.insert(0, "TO_DATE(").append(", ?").append(")");
        toDateKColumn.params.add(format);
        
        return toDateKColumn;
    }
    
    public static KValTextField toDate(
        final KValTextField kValTextField,
        final String format
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(format, "format");
        
        final KValTextField toDateKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, kValTextField.operating + 1, true);
        
        toDateKValTextField.sb.insert(0, "TO_DATE(").append(", ?").append(")");
        toDateKValTextField.params.add(format);
        
        return toDateKValTextField;
    }
    */
    public static KColumn toTimestamp(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn toTimestampKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        toTimestampKColumn.sb.insert(0, "TO_TIMESTAMP(").append(", ?").append(")");
        toTimestampKColumn.params.add(format);
        
        return toTimestampKColumn;
    }
    
    public static KValTextField toTimestamp(
        final KValTextField kValTextField,
        final String format
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(format, "format");
        
        final KValTextField toTimestampKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        toTimestampKValTextField.sb.insert(0, "TO_TIMESTAMP(").append(", ?").append(")");
        toTimestampKValTextField.params.add(format);
        
        return toTimestampKValTextField;
    }
    
    public static KColumn translate(
        final KColumn kColumn,
        final String from,
        final String to
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KColumn translateKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        translateKColumn.sb.insert(0, "TRANSLATE(").append(", ?, ?)");
        translateKColumn.params.add(from);
        translateKColumn.params.add(to);
        
        return translateKColumn;
    }
    
    public static KValTextField translate(
        final KValTextField kValTextField,
        final String from,
        final String to
    ) {
        assertNotNull(kValTextField, "kValTextField");
        assertNotNull(from, "from");
        assertNotNull(to, "to");
        
        final KValTextField translateKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        translateKValTextField.sb.insert(0, "TRANSLATE(").append(", ?, ?)");
        translateKValTextField.params.add(from);
        translateKValTextField.params.add(to);
        
        return translateKValTextField;
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
        return genericTrim(kColumn, characters, "TRIM");
    }
    
    public static KValTextField trim(
        final KValTextField kValTextField
    ) {
        return trim(kValTextField, null);
    }
    
    public static KValTextField trim(
        final KValTextField kValTextField,
        final String characters
    ) {
        return genericTrim(kValTextField, characters, "TRIM");
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
    
    public static KColumn sub(
        final KColumn kColumn,
        final KValNumberField kValNumberField
    ) {
        return applyBinaryOperator(kColumn, kValNumberField, "-");
    }
    
    public static KColumn sub(
        final KValNumberField kValNumberField,
        final KColumn kColumn
    ) {
        return applyBinaryOperator(kValNumberField, kColumn, "-");
    }
    
    public static KTable table(
        final KQuery kQuery,
        final String alias
    ) {
        return new KTable(kQuery, alias);
    }
    
    public static KColumn toChar(
        final KColumn kColumn,
        final String format
    ) {
        assertNotNull(kColumn, "kColumn");
        assertNotNull(format, "format");
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "TO_CHAR(").append(", ?").append(")");
        substringKColumn.params.add(format);
        
        return substringKColumn;
    }
    
    public static KValNumberField toChar(
        final KValNumberField kValNumberField,
        final String format
    ) {
        assertNotNull(kValNumberField, "kValNumberField");
        assertNotNull(format, "format");
        
        final KValNumberField substringKValNumberField = new KValNumberField(kValNumberField.sb, kValNumberField.params, true);
        
        substringKValNumberField.sb.insert(0, "TO_CHAR(").append(", ?").append(")");
        substringKValNumberField.params.add(format);
        
        return substringKValNumberField;
    }
    
    public static KColumn trunc(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "TRUNC");
    }
    
    public static KValNumberField trunc(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterFunction(kValNumberField, "TRUNC");
    }
    
    public static KValNumberField trunc(
        final Number number
    ) {
        assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TRUNC");
    }
    
    public static KColumn uuidGenerateV1() {
        return new KColumn(new StringBuilder("UUID_GENERATE_V1()"), true);
    }
    
    public static KColumn uuidGenerateV4() {
        return new KColumn(new StringBuilder("UUID_GENERATE_V4()"), true);
    }
    
    public static KColumn upper(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "UPPER");
    }
    
    public static KValTextField upper(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "UPPER");
    }
    
    public static KValTextField upper(
        final String value
    ) {
        assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "UPPER");
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
    
    public static KEmptyValues values() {
        return KEmptyValues.getInstance();
    }
    
    public static KWindowDefinitionUnnamed wd() {
        return KWindowDefinitionUnnamed.getInstance();
    }
    
    public static KWindowDefinitionNamed wd(
        final String name
    ) {
        return KWindowDefinitionNamed.getInstance(name);
    }
    
    public static KWindowDefinitionUnnamed windowDefinition() {
        return KWindowDefinitionUnnamed.getInstance();
    }
    
    public static KWindowDefinitionNamed windowDefinition(
        final String name
    ) {
        return KWindowDefinitionNamed.getInstance(name);
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
        
        final KColumn widthBucketkColumn = new KColumn(op.sb, op.params, true);
        
        widthBucketkColumn.sb.insert(0, "WIDTH_BUCKET(").append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count.sb).append(")");
        widthBucketkColumn.params.addAll(b1.params);
        widthBucketkColumn.params.addAll(b2.params);
        widthBucketkColumn.params.addAll(count.params);
        
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
        
        final KColumn widthBucketkColumn = new KColumn(op.sb, op.params, true);
        
        widthBucketkColumn.sb.insert(0, "WIDTH_BUCKET(").append(", ").append(b1.sb).append(", ").append(b2.sb).append(", ").append(count).append(")");
        widthBucketkColumn.params.addAll(b1.params);
        widthBucketkColumn.params.addAll(b2.params);
        
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
        
        final KColumn widthBucketkColumn = new KColumn(op.sb, op.params, true);
        
        widthBucketkColumn.sb.insert(0, "WIDTH_BUCKET(").append(", ").append(b1).append(", ").append(b2).append(", ").append(count).append(")");
        
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
    
    private static void assertNotNull(
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