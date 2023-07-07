package com.myzlab.k;

import static com.myzlab.k.SQLAlgorithm.bf;
import com.myzlab.k.functions.KTupleFunction;
import com.myzlab.k.helper.KExceptionHelper;
import com.myzlab.k.optional.KOptionalArrayObject;
import com.myzlab.k.optional.KOptionalCollection;
import com.myzlab.k.optional.KOptionalKAliasedColumn;
import com.myzlab.k.optional.KOptionalKJoinDefinition;
import com.myzlab.k.optional.KOptionalKColumn;
import com.myzlab.k.optional.KOptionalKColumnOrdered;
import com.myzlab.k.optional.KOptionalKCondition;
import com.myzlab.k.optional.KOptionalKValNumberField;
import com.myzlab.k.optional.KOptionalKValTextField;
import com.myzlab.k.optional.KOptionalLocalDate;
import com.myzlab.k.optional.KOptionalLocalDateTime;
import com.myzlab.k.optional.KOptionalLong;
import com.myzlab.k.optional.KOptionalNumber;
import com.myzlab.k.optional.KOptionalSpecialFunction;
import com.myzlab.k.optional.KOptionalString;
import com.myzlab.k.optional.KOptionalUuid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class KFunction {

    public static KAliasedColumn as(
        final KBaseColumnCastable kBaseColumnCastable,
        final String alias
    ) {
        KUtils.assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
        KUtils.assertNotNullNotEmpty(alias, "alias");
                
        return new KAliasedColumn(kBaseColumnCastable, alias);
    }
    
    public static KAliasedColumn as(
        final KColumnOvered kColumnOvered,
        final String alias
    ) {
        KUtils.assertNotNull(kColumnOvered, "kColumnOvered");
        KUtils.assertNotNullNotEmpty(alias, "alias");
                
        return new KAliasedColumn(kColumnOvered, alias);
    }
    
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
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
    
    public static KColumn all(
        final KQuery kQuery
    ) {
        return processAll(kQuery);
    }
    
    public static KColumn any(
        final KQuery kQuery
    ) {
        return processAny(kQuery);
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn,
        final Number number,
        final String operator
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(number, "number");
        
        return applyBinaryOperator(kColumn, new KValNumberField(number), operator);
    }
    
    private static KColumn applyBinaryOperator(
        final Number number,
        final KColumn kColumn,
        final String operator
    ) {
        KUtils.assertNotNull(number, "number");
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(new KValNumberField(number), kColumn, operator);
    }
    
    private static KColumn applyBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        KUtils.assertNotNull(kColumn1, "kColumn1");
        KUtils.assertNotNull(kColumn2, "kColumn2");
        
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
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        KUtils.assertNotNull(number, "number");
        
        return applyBinaryOperator(kValNumberField, new KValNumberField(number), operator);
    }
    
    private static KValNumberField applyBinaryOperator(
        final Number number,
        final KValNumberField kValNumberField,
        final String operator
    ) {
        KUtils.assertNotNull(number, "number");
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        return applyBinaryOperator(new KValNumberField(number), kValNumberField, operator);
    }
    
    private static KValNumberField applyBinaryOperator(
        final KValNumberField kValNumberField1,
        final KValNumberField kValNumberField2,
        final String operator
    ) {
        KUtils.assertNotNull(kValNumberField1, "kValNumberField1");
        KUtils.assertNotNull(kValNumberField2, "kValNumberField2");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
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
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        KUtils.assertNotNull(kColumn, "kColumn");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
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
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn functionKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKColumn;
    }
    
    private static KAggregateFunctionColumn applyOneParameterAggregateFunction(
        final KColumn kColumn,
        final String functionName
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KAggregateFunctionColumn functionKAggregateFunctionColumn = new KAggregateFunctionColumn(kColumn.sb, kColumn.params, true);
        
        functionKAggregateFunctionColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKAggregateFunctionColumn;
    }
    
    private static KValNumberField applyOneParameterFunction(
        final KValNumberField kValNumberField,
        final String functionName
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField functionKValNumberField = new KValNumberField(kValNumberField.sb, kValNumberField.params, true);
        
        functionKValNumberField.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKValNumberField;
    }
    
    private static KAggregateFunctionColumn applyOneParameterAggregateFunction(
        final KValNumberField kValNumberField,
        final String functionName
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        final KAggregateFunctionColumn functionKAggregateFunctionColumn = new KAggregateFunctionColumn(kValNumberField.sb, kValNumberField.params, true);
        
        functionKAggregateFunctionColumn.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKAggregateFunctionColumn;
    }
    
    private static KValTextField applyOneParameterFunction(
        final KValTextField kValTextField,
        final String functionName
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField functionKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        functionKValTextField.sb.insert(0, "(").insert(0, functionName).append(")");
        
        return functionKValTextField;
    }
    
    private static KColumn applyTwoParameterFunction(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2,
        final String functionName
    ) {
        KUtils.assertNotNull(kBaseColumn1, "kBaseColumn1");
        KUtils.assertNotNull(kBaseColumn2, "kBaseColumn2");
        
        final KColumn functionKColumn = new KColumn(kBaseColumn1.sb, kBaseColumn1.params, true);
        
        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kBaseColumn2.sb).append(")");
        functionKColumn.params.addAll(kBaseColumn2.params);
        
        return functionKColumn;
    }
    
    private static KAggregateFunctionColumn applyTwoParameterAggregateFunction(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2,
        final String functionName
    ) {
        KUtils.assertNotNull(kBaseColumn1, "kBaseColumn1");
        KUtils.assertNotNull(kBaseColumn2, "kBaseColumn2");
        
        final KAggregateFunctionColumn kAggregateFunctionColumn = new KAggregateFunctionColumn(kBaseColumn1.sb, kBaseColumn1.params, true);
        
        kAggregateFunctionColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kBaseColumn2.sb).append(")");
        kAggregateFunctionColumn.params.addAll(kBaseColumn2.params);
        
        return kAggregateFunctionColumn;
    }
    
//    private static KColumn applyTwoParameterFunction(
//        final KColumn kColumn1,
//        final KColumn kColumn2,
//        final String functionName
//    ) {
//        KUtils.assertNotNull(kColumn1, "kColumn1");
//        KUtils.assertNotNull(kColumn2, "kColumn2");
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
//        KUtils.assertNotNull(kValNumberField, "kValNumberField");
//        KUtils.assertNotNull(kColumn, "kColumn");
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
//        KUtils.assertNotNull(kColumn, "kColumn");
//        KUtils.assertNotNull(kValNumberField, "kValNumberField");
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
        
        KUtils.assertNotNull(kValNumberField1, "kValNumberField1");
        KUtils.assertNotNull(kValNumberField2, "kValNumberField2");
        
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
//        KUtils.assertNotNull(kValTextField, "kValTextField");
//        KUtils.assertNotNull(kColumn, "kColumn");
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
//        KUtils.assertNotNull(kColumn, "kColumn");
//        KUtils.assertNotNull(kValTextField, "kValTextField");
//        
//        final KColumn functionKColumn = new KColumn(kColumn.sb, kColumn.params, kColumn.operating, true);
//        
//        functionKColumn.sb.insert(0, "(").insert(0, functionName).append(", ").append(kValTextField.sb).append(")");
//        functionKColumn.params.addAll(kValTextField.params);
//        functionKColumn.operating += kValTextField.operating;
//        
//        return functionKColumn;
//    }
    
    public static KArrayAggColumn arrayAgg(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KArrayAggColumn kArrayAggColumn = new KArrayAggColumn(kColumn.sb, kColumn.params, true);
        
        kArrayAggColumn.sb.insert(0, "ARRAY_AGG(").append(")");
        
        return kArrayAggColumn;
    }
    
    public static KColumn arrayAppend(
        final KColumn kColumnArray,
        final KColumn kColumnElement
    ) {
        return applyTwoParameterFunction(kColumnArray, kColumnElement, "ARRAY_APPEND");
    }
    
    public static KColumn arrayAppend(
        final KColumn kColumnArray,
        final Object element
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder("?"),
                new ArrayList(){{
                    add(element);
                }},
                false
            ),
            "ARRAY_APPEND"
        );
    }
    
    public static KColumn arrayCat(
        final KColumn kColumnArray1,
        final KColumn kColumnArray2
    ) {
        return applyTwoParameterFunction(kColumnArray1, kColumnArray2, "ARRAY_CAT");
    }
    
    public static KColumn arrayDims(
        final KColumn kColumnArray
    ) {
        return applyOneParameterFunction(kColumnArray, "ARRAY_DIMS");
    }
    
    public static KColumn arrayFill(
        final KColumn kColumn,
        final int upper
    ) {
        return arrayFill(kColumn, upper, null);
    }
    
    public static KColumn arrayFill(
        final KColumn kColumn,
        final int upper,
        final Integer lower
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn arrayFillKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        arrayFillKColumn.sb.insert(0, "ARRAY_FILL(").append(", ARRAY[").append(upper).append("]");
        
        if (lower != null) {
            arrayFillKColumn.sb.append(", ARRAY[").append(lower).append("]");
        }
        
        arrayFillKColumn.sb.append(")");
        
        return arrayFillKColumn;
    }
    
    public static KColumn arrayFill(
        final Object element,
        final int upper
    ) {
        return arrayFill(element, upper, null);
    }
    
    public static KColumn arrayFill(
        final Object element,
        final int upper,
        final Integer lower
    ) {
        KUtils.assertNotNull(element, "element");
        
        final KColumn arrayFillKColumn = new KColumn(
            new StringBuilder("?"),
            new ArrayList(){{
                add(element);
            }},
            false
        );
        
        arrayFillKColumn.sb.insert(0, "ARRAY_FILL(").append(", ARRAY[").append(upper).append("]");
        
        if (lower != null) {
            arrayFillKColumn.sb.append(", ARRAY[").append(lower).append("]");
        }
        
        arrayFillKColumn.sb.append(")");
        
        return arrayFillKColumn;
    }
    
    public static KColumn arrayLength(
        final KColumn kColumnArray,
        final int n
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder(String.valueOf(n)),
                new ArrayList<>(),
                false
            ),
            "ARRAY_LENGTH"
        );
    }
    
    public static KColumn arrayLower(
        final KColumn kColumnArray,
        final int n
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder(String.valueOf(n)),
                new ArrayList<>(),
                false
            ),
            "ARRAY_LOWER"
        );
    }
    
    public static KColumn arrayNdims(
        final KColumn kColumnArray
    ) {
        return applyOneParameterFunction(kColumnArray, "ARRAY_NDIMS");
    }
    
    public static KColumn arrayPosition(
        final KColumn kColumnArray,
        final KColumn kColumnElement
    ) {
        return arrayPosition(kColumnArray, kColumnElement, null);
    }
    
    public static KColumn arrayPosition(
        final KColumn kColumnArray,
        final KColumn kColumnElement,
        final Integer n
    ) {
        KUtils.assertNotNull(kColumnArray, "kColumnArray");
        KUtils.assertNotNull(kColumnElement, "kColumnElement");
        
        final KColumn arrayPositionKColumn = new KColumn(kColumnArray.sb, kColumnArray.params, true);
        
        arrayPositionKColumn.sb.insert(0, "ARRAY_POSITION(").append(", ").append(kColumnElement.sb);
        
        if (n != null) {
            arrayPositionKColumn.sb.append(", ").append(n);
        }
        
        arrayPositionKColumn.sb.append(")");
        arrayPositionKColumn.params.addAll(kColumnElement.params);
        
        return arrayPositionKColumn;
    }
    
    public static KColumn arrayPosition(
        final KColumn kColumnArray,
        final Object element
    ) {
        return arrayPosition(kColumnArray, element, null);
    }
    
    public static KColumn arrayPosition(
        final KColumn kColumnArray,
        final Object element,
        final Integer n
    ) {
        KUtils.assertNotNull(kColumnArray, "kColumnArray");
        KUtils.assertNotNull(element, "element");
        
        final KColumn arrayPositionKColumn = new KColumn(kColumnArray.sb, kColumnArray.params, true);
        
        arrayPositionKColumn.sb.insert(0, "ARRAY_POSITION(").append(", ?");
        
        if (n != null) {
            arrayPositionKColumn.sb.append(", ").append(n);
        }
        
        arrayPositionKColumn.sb.append(")");
        arrayPositionKColumn.params.add(element);
        
        return arrayPositionKColumn;
    }
    
    public static KColumn arrayPositions(
        final KColumn kColumnArray,
        final KColumn kColumnElement
    ) {
        return applyTwoParameterFunction(kColumnArray, kColumnElement, "ARRAY_POSITIONS");
    }
    
    public static KColumn arrayPositions(
        final KColumn kColumnArray,
        final Object element
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder("?"),
                new ArrayList(){{
                    add(element);
                }},
                false
            ),
            "ARRAY_POSITIONS"
        );
    }
    
    public static KColumn arrayPrepend(
        final KColumn kColumnElement,
        final KColumn kColumnArray
    ) {
        return applyTwoParameterFunction(kColumnElement, kColumnArray, "ARRAY_PREPEND");
    }
    
    public static KColumn arrayPrepend(
        final Object element,
        final KColumn kColumnArray
    ) {
        return applyTwoParameterFunction(
            new KColumn(
                new StringBuilder("?"),
                new ArrayList(){{
                    add(element);
                }},
                false
            ),
            kColumnArray,
            "ARRAY_PREPEND"
        );
    }
    
    public static KColumn arrayRemove(
        final KColumn kColumnArray,
        final KColumn kColumnElement
    ) {
        return applyTwoParameterFunction(kColumnArray, kColumnElement, "ARRAY_REMOVE");
    }
    
    public static KColumn arrayRemove(
        final KColumn kColumnArray,
        final Object element
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder("?"),
                new ArrayList(){{
                    add(element);
                }},
                false
            ),
            "ARRAY_REMOVE"
        );
    }
    
    public static KColumn arrayReplace(
        final KColumn kColumnArray,
        final KColumn kColumnPreviousValue,
        final Object newValue
    ) {
        KUtils.assertNotNull(kColumnArray, "kColumnArray");
        KUtils.assertNotNull(kColumnPreviousValue, "kColumnPreviousValue");
        KUtils.assertNotNull(newValue, "newValue");
        
        final KColumn arrayReplaceKColumn = new KColumn(kColumnArray.sb, kColumnArray.params, true);
        
        arrayReplaceKColumn.sb.insert(0, "ARRAY_REPLACE(").append(", ").append(kColumnPreviousValue.sb).append(", ?)");
        
        arrayReplaceKColumn.params.addAll(kColumnPreviousValue.params);
        arrayReplaceKColumn.params.add(newValue);
        
        return arrayReplaceKColumn;
    }
    
    public static KColumn arrayReplace(
        final KColumn kColumnArray,
        final Object previousValue,
        final Object newValue
    ) {
        KUtils.assertNotNull(kColumnArray, "kColumnArray");
        KUtils.assertNotNull(previousValue, "previousValue");
        KUtils.assertNotNull(newValue, "newValue");
        
        final KColumn arrayReplaceKColumn = new KColumn(kColumnArray.sb, kColumnArray.params, true);
        
        arrayReplaceKColumn.sb.insert(0, "ARRAY_REPLACE(").append(", ?, ?)");
        
        arrayReplaceKColumn.params.add(previousValue);
        arrayReplaceKColumn.params.add(newValue);
        
        return arrayReplaceKColumn;
    }
    
    public static KColumn arrayToString(
        final KColumn kColumnArray,
        final String delimiter
    ) {
        return arrayToString(kColumnArray, delimiter, null);
    }
    
    public static KColumn arrayToString(
        final KColumn kColumnArray,
        final String delimiter,
        final String nullString
    ) {
        KUtils.assertNotNull(kColumnArray, "kColumnArray");
        KUtils.assertNotNullNotEmpty(delimiter, "delimiter");
        
        final KColumn arrayToStringKColumn = new KColumn(kColumnArray.sb, kColumnArray.params, true);
        
        arrayToStringKColumn.sb.insert(0, "ARRAY_TO_STRING(").append(", ?");
        
        arrayToStringKColumn.params.add(delimiter);
        
        if (nullString != null) {
            arrayToStringKColumn.sb.append(", ?");
            
            arrayToStringKColumn.params.add(nullString);
        }
        
        arrayToStringKColumn.sb.append(")");
        
        return arrayToStringKColumn;
    }
    
    public static KColumn arrayUpper(
        final KColumn kColumnArray,
        final int n
    ) {
        return applyTwoParameterFunction(
            kColumnArray, 
            new KColumn(
                new StringBuilder(String.valueOf(n)),
                new ArrayList<>(),
                false
            ),
            "ARRAY_UPPER"
        );
    }
    
    public static void assertExists(
        final KBuilder k,
        final KQuery kQuery,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertExists(k, k.getJdbcTemplateDefaultName(), kQuery, httpStatus, message);
    }
    
    public static void assertExists(
        final KBuilder k,
        final String jdbc,
        final KQuery kQuery,
        final HttpStatus httpStatus,
        final String message
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        
        final Boolean exists =
            k
            .jdbc(jdbc)
            .select(exists(kQuery).as("GOD_BLESS_YOU"))
            .single(Boolean.class);
        
        if (!exists) {
            throw new KException(httpStatus, message);
        }
    }
    
    public static void assertNotExists(
        final KBuilder k,
        final KQuery kQuery,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertNotExists(k, k.getJdbcTemplateDefaultName(), kQuery, httpStatus, message);
    }
    
    public static void assertNotExists(
        final KBuilder k,
        final String jdbc,
        final KQuery kQuery,
        final HttpStatus httpStatus,
        final String message
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        
        final Boolean notExists =
            k
            .jdbc(jdbc)
            .select(not(exists(kQuery)).as("GOD_BLESS_YOU"))
            .single(Boolean.class);
        
        if (!notExists) {
            throw new KException(httpStatus, message);
        }
    }
    
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyTwoParameterFunction(kColumn, val(number), "ATAN2");
    }
    
    public static KColumn atan2(
        final Number number,
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number1, "number1");
        KUtils.assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "ATAN2");
    }
    
    public static KAggregateFunctionColumn avg(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "AVG");
    }
    
    public static KAggregateFunctionColumn avg(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterAggregateFunction(kValNumberField, "AVG");
    }
    
    public static KAggregateFunctionColumn avg(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterAggregateFunction(val(number), "AVG");
    }
    
    public static KAggregateFunctionColumn bitAnd(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "BIT_AND");
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
    
    public static KColumn bitLength(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "BIT_LENGTH");
    }
    
    public static KColumn bitNot(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyUnaryOperator(kColumn, "~", false);
    }
    
    public static KValNumberField bitNot(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        return applyUnaryOperator(val(number), "~", false);
    }
    
    public static KValNumberField bitNot(
        final KValNumberField kValNumberField
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        return applyUnaryOperator(kValNumberField, "~", false);
    }
    
    public static KAggregateFunctionColumn bitOr(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "BIT_OR");
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(kColumn, n, "<<");
    }
    
    public static KValNumberField bitShiftLeft(
        final KValNumberField kValNumberField,
        final int n
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyBinaryOperator(kColumn, n, ">>");
    }
    
    public static KValNumberField bitShiftRight(
        final KValNumberField kValNumberField,
        final int n
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
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
    
    public static KAggregateFunctionColumn boolAnd(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "BOOL_AND");
    }
    
    public static KAggregateFunctionColumn boolAnd(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        final KAggregateFunctionColumn kAggregateFunctionColumn = new KAggregateFunctionColumn(kCondition.sb, kCondition.params, false);
        
        return applyOneParameterAggregateFunction(kAggregateFunctionColumn, "BOOL_AND");
    }
    
    public static KAggregateFunctionColumn boolOr(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "BOOL_OR");
    }
    
    public static KAggregateFunctionColumn boolOr(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        final KAggregateFunctionColumn kAggregateFunctionColumn = new KAggregateFunctionColumn(kCondition.sb, kCondition.params, false);
        
        return applyOneParameterAggregateFunction(kAggregateFunctionColumn, "BOOL_OR");
    }
    
    public static KColumn btrim(
        final KColumn kColumn
    ) {
        return btrim(kColumn, null);
    }
    
    public static KColumn btrim(
        final KColumn kColumn,
        final String characters
    ) {
        return genericTrim(kColumn, characters, "BTRIM");
    }
    
    public static KValTextField btrim(
        final KValTextField kValTextField
    ) {
        return btrim(kValTextField, null);
    }
    
    public static KValTextField btrim(
        final KValTextField kValTextField,
        final String characters
    ) {
        return genericTrim(kValTextField, characters, "BTRIM");
    }
    
    public static Long calculateOffset(
        final Long page,
        final Long limit
    ) {
        return (page - 1) * limit;
    }
    
    public static KOptionalLong calculateOffset(
        final KOptionalLong page,
        final KOptionalLong limit
    ) {
        if (!limit.isPresent() || !page.isPresent()) {
            return KOptionalLong.getNullInstance();
        }
        
        return KOptionalLong.getInstance((page.get() - 1) * limit.get());
    }
    
    public static KCase caseConditional() {
        return KCase.getInstance();
    }
    
    public static KColumn cast(
        final KBaseColumnCastable kBaseColumnCastable,
        final KDataType kDataType    
    ) {
        KUtils.assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
        KUtils.assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn;
        
        if (kBaseColumnCastable.kTable != null) {
            castkColumn = new KColumn(kBaseColumnCastable.sb, kBaseColumnCastable.params, true, kBaseColumnCastable.name, kBaseColumnCastable.type, kBaseColumnCastable.kTable);
        } else {
            castkColumn = new KColumn(kBaseColumnCastable.sb, kBaseColumnCastable.params, true);
        }
        
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
        KUtils.assertNotNull(val, "val");
        KUtils.assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(val);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cast(
        final String val,
        final KDataType kDataType    
    ) {
        KUtils.assertNotNull(val, "val");
        KUtils.assertNotNull(kDataType, "kDataType");
        
        final KColumn castkColumn = new KColumn(val);
        
        castkColumn.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castkColumn;
    }
    
    public static KColumn cardinality(
        final KColumn kColumnArray
    ) {
        return applyOneParameterFunction(kColumnArray, "CARDINALITY");
    }
    
//    public static KColumn cast(
//        final KColumn kColumn,
//        final KDataType kDataType    
//    ) {
//        KUtils.assertNotNull(kColumn, "kColumn");
//        KUtils.assertNotNull(kDataType, "kDataType");
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
//        KUtils.assertNotNull(kValNumberField, "kValNumberField");
//        KUtils.assertNotNull(kDataType, "kDataType");
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
//        KUtils.assertNotNull(kValTextField, "kValTextField");
//        KUtils.assertNotNull(kDataType, "kDataType");
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "CEILING");
    }
    
    public static KColumn characterLength(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CHARACTER_LENGTH");
    }
    
    public static KColumn charLength(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CHAR_LENGTH");
    }
    
    public static KColumn chr(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "CHR");
    }
    
    public static KColumn coalesce(
        final KBaseColumnCastable... kBaseColumnCastables
    ) {
        KUtils.assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "COT");
    }
    
    public static KAggregateFunctionColumn count() {
        return new KAggregateFunctionColumn(new StringBuilder("COUNT(*)"), new ArrayList<>(), true);
    }
    
    public static KAggregateFunctionColumn count(
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
        KUtils.assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
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
                concatKColumn.sb.append(", ");
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
    
    public static KColumn concatWs(
        final String separator,
        final KBaseColumnCastable... kBaseColumnCastables
    ) {
        KUtils.assertNotNullNotEmpty(separator, "separator");
        KUtils.assertNotNull(kBaseColumnCastables, "kBaseColumnCastables");
        
        if (kBaseColumnCastables.length < 2) {
            throw KExceptionHelper.internalServerError("'CONCAT_WS' function requires at least two kBaseColumnCastables");
        }
        
        final KColumn concatWsKColumn = new KColumn(false);
        
        concatWsKColumn.sb.append("CONCAT_WS(?");
        concatWsKColumn.params.add(separator);
        
        for (final KBaseColumnCastable kBaseColumnCastable : kBaseColumnCastables) {
            if (kBaseColumnCastable == null) {
                continue;
            }

            concatWsKColumn.sb.append(", ").append(kBaseColumnCastable.sb);
            concatWsKColumn.params.addAll(kBaseColumnCastable.params);
        }
        
        concatWsKColumn.sb.append(")");
        
        return concatWsKColumn;
    }
    
    public static KColumn convert(
        final KColumn kColumn,
        final KEncoding srcEncoding,
        final KEncoding destEncoding
    ) {
        KUtils.assertNotNullNotEmpty(kColumn, "kColumn");
        KUtils.assertNotNull(srcEncoding, "srcEncoding");
        KUtils.assertNotNull(destEncoding, "destEncoding");

        final KColumn convertKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        convertKColumn.sb.insert(0, "CONVERT(").append(", '").append(srcEncoding.toSql()).append("', '").append(destEncoding.toSql()).append("')");
        
        return convertKColumn;
    }
    
    public static KColumn convertFrom(
        final KColumn kColumn,
        final KEncoding srcEncoding
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(srcEncoding, "srcEncoding");
        
        return applyTwoParameterFunction(kColumn, new KColumn(new StringBuilder("'" + srcEncoding.toSql() + "'"), true), "CONVERT_FROM");
    }
    
    public static KColumn convertTo(
        final KColumn kColumn,
        final KEncoding destEncoding
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(destEncoding, "destEncoding");
        
        return applyTwoParameterFunction(kColumn, new KColumn(new StringBuilder("'" + destEncoding.toSql() + "'"), true), "CONVERT_TO");
    }
    
    public static KColumn crypt(
        final String password
    ) {
        return crypt(password, genSalt(bf()));
    }
    
    public static KColumn crypt(
        final String password,
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(password, "password");
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn crypKColumn = new KColumn();
        
        crypKColumn.sb.append("CRYPT(?, ").append(kColumn.sb).append(")");
        
        crypKColumn.params.add(password);
        crypKColumn.params.addAll(kColumn.params);
        
        return crypKColumn;
    }
    
    public static KColumn crypt(
        final String password,
        final String salt
    ) {
        KUtils.assertNotNull(password, "password");
        KUtils.assertNotNull(salt, "salt");
        
        final KColumn crypKColumn = new KColumn();
        
        crypKColumn.sb.append("CRYPT(?, ?)");
        
        crypKColumn.params.add(password);
        crypKColumn.params.add(salt);
        
        return crypKColumn;
    }
    
    public static KCommonTableExpressionNamed cte(
        final String name
    ) {
        return KCommonTableExpressionNamed.getInstance(name);
    }
    
    public static KColumn cube(
        final KColumn... kColumns
    ) {
        return groupingSet("CUBE", kColumns);
    }
    
    public static KWindowFunctionColumn cumeDist() {
        return new KWindowFunctionColumn(new StringBuilder("CUME_DIST()"), true);
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractkColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        extractkColumn.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_PART('").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn dateTrunc(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractkColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        extractkColumn.sb.insert(0, "', ").insert(0, kExtractField.toSql()).insert(0, "DATE_TRUNC('").append(")");
        
        return extractkColumn;
    }
    
    public static KColumn decode(
        final KColumn kColumn,
        final KFormat kFormat
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kFormat, "kFormat");
        
        final KColumn decodeKColumn = kColumn.cloneMe();
        decodeKColumn.closed = true;
        
        decodeKColumn.sb.insert(0, "DECODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return decodeKColumn;
    }
    
    public static KValTextField decode(
        final KValTextField kValTextField,
        final KFormat kFormat
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(kFormat, "kFormat");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "DEGREES");
    }
    
    public static KWindowFunctionColumn denseRank() {
        return new KWindowFunctionColumn(new StringBuilder("DENSE_RANK()"), true);
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kFormat, "kFormat");
        
        final KColumn encodeKColumn = kColumn.cloneMe();
        encodeKColumn.closed = true;
        
        encodeKColumn.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKColumn;
    }
    
    public static KValTextField encode(
        final KValTextField kValTextField,
        final KFormat kFormat
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(kFormat, "kFormat");
        
        final KValTextField encodeKValTextField = kValTextField.cloneMe();
        encodeKValTextField.closed = true;
        
        encodeKValTextField.sb.insert(0, "ENCODE(").append(", '").append(kFormat.toSql()).append("'").append(")");
        
        return encodeKValTextField;
    }
    
    public static KAggregateFunctionColumn every(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "EVERY");
    }
    
    public static KAggregateFunctionColumn every(
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        final KAggregateFunctionColumn kAggregateFunctionColumn = new KAggregateFunctionColumn(kCondition.sb, kCondition.params, false);
        
        return applyOneParameterAggregateFunction(kAggregateFunctionColumn, "EVERY");
    }
    
    public static KColumn excluded(
        final KTableColumn kTableColumn
    ) {
        final KTableColumn newKTableColumn = kTableColumn.cloneMe();
        
        if (newKTableColumn.kTable != null) {
            final String content = newKTableColumn.sb.toString().replace(newKTableColumn.kTable.alias + ".", "EXCLUDED.");
            
            newKTableColumn.sb.setLength(0);
            
            newKTableColumn.sb.append(content);
        }
        
        return newKTableColumn;
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "EXP");
    }
    
    public static KColumn extract(
        final KColumn kColumn,
        final KExtractField kExtractField
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kExtractField, "kExtractField");
        
        final KColumn extractKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        extractKColumn.sb.insert(0, " FROM ").insert(0, kExtractField.toSql()).insert(0, "EXTRACT(").append(")");
        
        return extractKColumn;
    }
    
    public static KColumn factorial(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "FACTORIAL");
    }
    
    public static KWindowFunctionColumn firstValue(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final StringBuilder sb = new StringBuilder("FIRST_VALUE(");
        
        sb.append(kColumn.sb).append(")");
        
        return new KWindowFunctionColumn(sb, new ArrayList() {{
            addAll(kColumn.params);
        }}, true);
    }
    
    public static KColumn format(
        final String formatString,
        final KColumn... kColumns
    ) {
        KUtils.assertNotNullNotEmpty(formatString, "formatString");
        KUtils.assertNotNullNotEmpty(kColumns, "kColumns");
        
        final KColumn formatKColumn = new KColumn();
        
        formatKColumn.sb.append("FORMAT('").append(formatString).append("'");
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }

            formatKColumn.sb.append(", ").append(kColumn.sb);
            formatKColumn.params.addAll(kColumn.params);
        }
        
        formatKColumn.sb.append(")");
        
        return formatKColumn;
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField genericTrimKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        genericTrimKValTextField.sb.insert(0, "(").insert(0, trimFunctionName);
                
        if (characters != null) {
            genericTrimKValTextField.sb.append(", ").append("?");
            genericTrimKValTextField.params.add(characters);
        }
                
        genericTrimKValTextField.sb.append(")");
        
        return genericTrimKValTextField;
    }
    
    public static KColumn genSalt(
        final KAlgorithm kAlgorithm
    ) {
        return genSalt(kAlgorithm, null);
    }
    
    public static KColumn genSalt(
        final KAlgorithm kAlgorithm,
        final Integer iterCount
    ) {
        KUtils.assertNotNull(kAlgorithm, "kAlgorithm");
        
        final KColumn genSaltKColumn = new KColumn();
        
        genSaltKColumn.sb.append("GEN_SALT(?");
        genSaltKColumn.params.add(kAlgorithm.toSql());
        
        if (iterCount != null) {
            genSaltKColumn.sb.append(", ").append(iterCount);
        }
        
        genSaltKColumn.sb.append(")");
        
        return genSaltKColumn;
    }
    
    /*
     * This method allows obtaining a KOptionalKColumnOrdered from a String value (orderBy), very useful for generic cases such as DTO methods.
     */
    public static KOptionalKColumnOrdered getKColumnOrderedByName(
        final String orderBy,
        final Integer order,
        final KTable... kTables
    ) {
        KUtils.assertNotNull(kTables, "kTables");
        
        if (orderBy == null || order == null) {
            return KOptionalKColumnOrdered.getNullInstance();
        }
        
        for (final KTable kTable : kTables) {
            final KOptionalKColumnOrdered kOptionalKColumnOrdered = kTable.getOrderBy(orderBy, order);
            
            if (kOptionalKColumnOrdered.isPresent()) {
                return kOptionalKColumnOrdered;
            }
        }
        
        return KOptionalKColumnOrdered.getNullInstance();
    }
    
    public static KColumn getJsonArray(
        final KColumn kColumn,
        final int index
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" -> ").append(index);
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonArrayAsText(
        final KColumn kColumn,
        final int index
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" ->> ").append(index);
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObject(
        final KColumn kColumn,
        final String name
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(name, "name");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" -> '").append(name).append("'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAsText(
        final KColumn kColumn,
        final String name
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(name, "name");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" ->> '").append(name).append("'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAtPath(
        final KColumn kColumn,
        final String path
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(path, "path");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" #> '{").append(path).append("}'");
        
        return jsonKColumn;
    }
    
    public static KColumn getJsonObjectAtPathAsText(
        final KColumn kColumn,
        final String path
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(path, "path");
        
        final KColumn jsonKColumn = new KColumn(kColumn.sb, kColumn.params, false);
        
        jsonKColumn.sb.append(" #>> '{").append(path).append("}'");
        
        return jsonKColumn;
    }
    
    public static KColumn greatest(
        final KBaseColumn... KBaseColumns
    ) {
        KUtils.assertNotNull(KBaseColumns, "KBaseColumns");
        
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
    
    public static KColumn grouping(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "GROUPING");
    }
    
    private static KColumn groupingSet(
        final String name,
        final KColumn... kColumns
    ) {
        KUtils.assertNotNullNotEmpty(kColumns, "kColumns");
        
        final KColumn groupingSetKColumn = new KColumn();
        
        boolean first = true;
        
        groupingSetKColumn.sb.append(name).append("(");
        
        for (final KColumn kColumn : kColumns) {
            if (kColumn == null) {
                continue;
            }
            
            if (!first) {
                groupingSetKColumn.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            groupingSetKColumn.sb.append(kColumn.sb);
            groupingSetKColumn.params.addAll(kColumn.params);
        }
        
        groupingSetKColumn.sb.append(")");
        
        return groupingSetKColumn;
    }
    
    public static KColumn groupingSet(
        final KColumn... kColumns
    ) {
        return groupingSet("", kColumns);
    }
    
    public static KColumn groupingSets(
        final KColumn... kColumns
    ) {
        return groupingSet("GROUPING SETS", kColumns);
    }
    
    public static KColumn initcap(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "INITCAP");
    }
    
    public static KColumn isempty(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "ISEMPTY");
    }
    
    public static String iso8601DateUTCFormat() {
        return "YYYY-MM-DD";
    }
    
    public static String iso8601DateAndTimeUTCFormat() {
        return "YYYY-MM-DD\"T\"HH24:MI:SS.MS\"Z\"";
    }
    
    public static KColumn isolate(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn isolatekColumn = kColumn.cloneMe();
        isolatekColumn.closed = true;
        
        isolatekColumn.sb.insert(0, "(").append(")");
        
        return isolatekColumn;
    }
    
    public static KValTextField isolate(
        final KValTextField kValTextField
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField isolateKValTextField = kValTextField.cloneMe();
        isolateKValTextField.closed = true;
        
        isolateKValTextField.sb.insert(0, "(").append(")");
        
        return isolateKValTextField;
    }
    
    public static KValNumberField isolate(
        final KValNumberField kValNumberField
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        final KValNumberField isolateKValNumberField = kValNumberField.cloneMe();
        isolateKValNumberField.closed = true;
        
        isolateKValNumberField.sb.insert(0, "(").append(")");
        
        return isolateKValNumberField;
    }
    
    public static KJsonAggColumn jsonAgg(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KJsonAggColumn kJsonAggColumn = new KJsonAggColumn(kColumn.sb, kColumn.params, true);
        
        kJsonAggColumn.sb.insert(0, "JSON_AGG(").append(")");
        
        return kJsonAggColumn;
    }
    
    public static KJsonbAggColumn jsonbAgg(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KJsonbAggColumn kJsonbAggColumn = new KJsonbAggColumn(kColumn.sb, kColumn.params, true);
        
        kJsonbAggColumn.sb.insert(0, "JSONB_AGG(").append(")");
        
        return kJsonbAggColumn;
    }
    
    public static KAggregateFunctionColumn jsonObjectAgg(
        final KColumn kColumnName,
        final KColumn kColumnValue
    ) {
        return applyTwoParameterAggregateFunction(kColumnName, kColumnValue, "JSON_OBJECT_AGG");
    }
    
    public static KWindowFunctionColumn lag(
        final KColumn kColumn
    ) {
        return lag(kColumn, null, null);
    }
    
    public static KWindowFunctionColumn lag(
        final KColumn kColumn,
        final int offset
    ) {
        return lag(kColumn, offset, null);
    }
    
    public static KWindowFunctionColumn lag(
        final KColumn kColumn,
        final Integer offset,
        final KBaseColumnCastable defaultValue
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final StringBuilder sb = new StringBuilder("LAG(");
        
        sb.append(kColumn.sb);
        
        if (offset != null) {
            sb.append(", ").append(offset);
            
            if (defaultValue != null) {
                sb.append(", ").append(defaultValue.sb);
            }
        }
        
        sb.append(")");
        
        return new KWindowFunctionColumn(sb, new ArrayList() {{
            addAll(kColumn.params);
            addAll(defaultValue == null ? new ArrayList() : defaultValue.params);
        }}, true);
    }
    
    public static KWindowFunctionColumn lastValue(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final StringBuilder sb = new StringBuilder("LAST_VALUE(");
        
        sb.append(kColumn.sb).append(")");
        
        return new KWindowFunctionColumn(sb, new ArrayList() {{
            addAll(kColumn.params);
        }}, true);
    }
    
    public static KWindowFunctionColumn lead(
        final KColumn kColumn
    ) {
        return lead(kColumn, null, null);
    }
    
    public static KWindowFunctionColumn lead(
        final KColumn kColumn,
        final int offset
    ) {
        return lead(kColumn, offset, null);
    }
    
    public static KWindowFunctionColumn lead(
        final KColumn kColumn,
        final Integer offset,
        final KBaseColumnCastable defaultValue
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final StringBuilder sb = new StringBuilder("LEAD(");
        
        sb.append(kColumn.sb);
        
        if (offset != null) {
            sb.append(", ").append(offset);
            
            if (defaultValue != null) {
                sb.append(", ").append(defaultValue.sb);
            }
        }
        
        sb.append(")");
        
        return new KWindowFunctionColumn(sb, new ArrayList() {{
            addAll(kColumn.params);
            addAll(defaultValue == null ? new ArrayList() : defaultValue.params);
        }}, true);
    }
    
    public static KColumn least(
        final KBaseColumn... KBaseColumns
    ) {
        KUtils.assertNotNull(KBaseColumns, "KBaseColumns");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyTwoParameterFunction(
            kColumn, 
            new KColumn(new StringBuilder(String.valueOf(n)), new ArrayList<>(), true),
            "LEFT"
        );
    }
    
    public static KColumn left(
        final KValTextField kValTextField,
        final int n
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        return applyTwoParameterFunction(
            kValTextField, 
            new KColumn(new StringBuilder(String.valueOf(n)), new ArrayList<>(), true),
            "LEFT"
        );
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
    
    public static KColumn length(
        final KColumn kColumn,
        final KEncoding encoding
    ) {
        KUtils.assertNotNullNotEmpty(kColumn, "kColumn");
        KUtils.assertNotNullNotEmpty(encoding, "encoding");

        final KColumn convertKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        convertKColumn.sb.insert(0, "LENGTH(").append(", '").append(encoding.toSql()).append("'").append(")");
        
        return convertKColumn;
    }
    
    public static KColumn length(
        final KValTextField kValTextField,
        final KEncoding encoding
    ) {
        KUtils.assertNotNullNotEmpty(kValTextField, "kColumn");
        KUtils.assertNotNullNotEmpty(encoding, "encoding");

        final KColumn convertKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
        convertKColumn.sb.insert(0, "LENGTH(").append(", '").append(encoding.toSql()).append("'").append(")");
        
        return convertKColumn;
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "LOG");
    }
    
    public static KColumn log(
        final KColumn kColumn,
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number1, "number1");
        KUtils.assertNotNull(number2, "number2");
        
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
        KUtils.assertNotNull(number, "number");
        
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
    
    public static KColumn lowerInc(
        final KColumn kColumnRange
    ) {
        return applyOneParameterFunction(kColumnRange, "LOWER_INC");
    }
    
    public static KColumn lowerInf(
        final KColumn kColumnRange
    ) {
        return applyOneParameterFunction(kColumnRange, "LOWER_INF");
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
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
    
    public static KAggregateFunctionColumn max(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "MAX");
    }
    
    public static KAggregateFunctionColumn max(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterAggregateFunction(kValNumberField, "MAX");
    }
    
    public static KAggregateFunctionColumn max(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterAggregateFunction(val(number), "MAX");
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
    
    public static KAggregateFunctionColumn min(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "MIN");
    }
    
    public static KAggregateFunctionColumn min(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterAggregateFunction(kValNumberField, "MIN");
    }
    
    public static KAggregateFunctionColumn min(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterAggregateFunction(val(number), "MIN");
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
    
    public static KWindowFunctionColumn nthValue(
        final KColumn kColumn,
        final int offset
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final StringBuilder sb = new StringBuilder("NTH_VALUE(");
        
        sb.append(kColumn.sb).append(", ").append(offset).append(")");
        
        return new KWindowFunctionColumn(sb, new ArrayList() {{
            addAll(kColumn.params);
        }}, true);
    }
    
    public static KWindowFunctionColumn ntile(
        int buckets
    ) {
        final StringBuilder sb = new StringBuilder("NTILE(");
        
        sb.append(buckets).append(")");
        
        return new KWindowFunctionColumn(sb, true);
    }
    
    public static KRaw nullValue() {
        return new KRaw("NULL");
    }
    
    public static KColumn nullif(
        final KBaseColumnCastable kBaseColumnCastable1,
        final KBaseColumnCastable kBaseColumnCastable2
    ) {
        
        KUtils.assertNotNull(kBaseColumnCastable1, "kBaseColumnCastable1");
        KUtils.assertNotNull(kBaseColumnCastable2, "kBaseColumnCastable2");
        
        final KColumn nullifkColumn = new KColumn(kBaseColumnCastable1.sb, kBaseColumnCastable1.params, true);
        
        nullifkColumn.sb.insert(0, "NULLIF(").append(", ").append(kBaseColumnCastable2.sb).append(")");
        nullifkColumn.params.addAll(kBaseColumnCastable2.params);
        
        return nullifkColumn;
    }
    
    public static KColumn noUseAlias(
        final KTableColumn kTableColumn
    ) {
        final KColumn newKTableColumn = kTableColumn.cloneMe();
        
        if (newKTableColumn.kTable != null) {
            final String content = newKTableColumn.sb.toString().replace(newKTableColumn.kTable.alias + ".", "");
            
            newKTableColumn.sb.setLength(0);
            
            newKTableColumn.sb.append(content);
        }
        
        return newKTableColumn;
    }
    
    public static KCondition not(
        final KCondition kCondition
    ) {
        kCondition.sb.insert(0, "NOT (").append(")");
        
        return kCondition;
    }
    
    public static KCondition not(
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition(kColumn.sb.toString(), kColumn.params);
        
        kCondition.sb.insert(0, "NOT (").append(")");
        
        return kCondition;
    }
    
    public static KColumn octetLength(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "OCTET_LENGTH");
    }
    
    public static KValTextField octetLength(
        final KValTextField kValTextField
    ) {
        return applyOneParameterFunction(kValTextField, "OCTET_LENGTH");
    }
    
    public static KOptionalKColumn optional(
        final KColumn kColumn
    ) {
        return KOptionalKColumn.getInstance(kColumn);
    }
    
    public static KOptionalKAliasedColumn optional(
        final KAliasedColumn kAliasedColumn
    ) {
        return KOptionalKAliasedColumn.getInstance(kAliasedColumn);
    }
    
    public static KOptionalKValNumberField optional(
        final KValNumberField kValNumberField
    ) {
        return KOptionalKValNumberField.getInstance(kValNumberField);
    }
    
    public static KOptionalKValTextField optional(
        final KValTextField kValTextField
    ) {
        return KOptionalKValTextField.getInstance(kValTextField);
    }
    
    public static KOptionalNumber optional(
        final Number number
    ) {
        return KOptionalNumber.getInstance(number);
    }
    
    public static KOptionalLong optional(
        final Long value
    ) {
        return KOptionalLong.getInstance(value);
    }
    
    public static KOptionalString optional(
        final String value
    ) {
        return KOptionalString.getInstance(value);
    }
    
    public static KOptionalUuid optional(
        final UUID value
    ) {
        return KOptionalUuid.getInstance(value);
    }
    
    public static KOptionalLocalDate optional(
        final LocalDate localDate
    ) {
        return KOptionalLocalDate.getInstance(localDate);
    }
    
    public static KOptionalLocalDateTime optional(
        final LocalDateTime localDateTime
    ) {
        return KOptionalLocalDateTime.getInstance(localDateTime);
    }
    
    public static KOptionalSpecialFunction optional(
        final KSpecialFunction kSpecialFunction
    ) {
        return KOptionalSpecialFunction.getInstance(kSpecialFunction);
    }
    
    public static KOptionalCollection optional(
        final Collection values,
        final boolean omitOnEmptyCollection
    ) {
        return KOptionalCollection.getInstance(values, omitOnEmptyCollection);
    }
    
    public static KOptionalArrayObject optional(
        final Object[] values,
        final boolean omitOnEmptyArray
    ) {
        return KOptionalArrayObject.getInstance(values, omitOnEmptyArray);
    }
    
    public static KOptionalKJoinDefinition optional(
        final KJoinDefinition kJoinDefinition
    ) {
        return KOptionalKJoinDefinition.getInstance(kJoinDefinition);
    }
    
    public static KOptionalKCondition optional(
        final KCondition kCondition
    ) {
        return KOptionalKCondition.getInstance(kCondition);
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(value, "value");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(value, "value");
        
        final KValTextField overlayKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        overlayKValTextField.sb.insert(0, "OVERLAY(").append(" PLACING ?").append(" from ").append(from);
        overlayKValTextField.params.add(value);
        
        if (for_ != null) {
            overlayKValTextField.sb.append(" for ").append(for_);
        }
                
        overlayKValTextField.sb.append(")");
        
        return overlayKValTextField;
    }
    
    public static KWindowFunctionColumn percentRank() {
        return new KWindowFunctionColumn(new StringBuilder("PERCENT_RANK()"), true);
    }
    
    public static KColumn pgClientEncoding() {
        return new KColumn(new StringBuilder("PG_CLIENT_ENCODING()"), true);
    }
    
    public static KColumn pgpSymEncrypt(
        final KColumn data,
        final String pws,
        final String options
    ) {
        KUtils.assertNotNull(data, "data");
        KUtils.assertNotNull(pws, "pws");
        
        final KColumn pgpKColumn = new KColumn();
        
        pgpKColumn.sb.append("PGP_SYM_ENCRYPT(").append(data.sb).append(", ?");
        
        pgpKColumn.params.addAll(data.params);
        pgpKColumn.params.add(pws);
        
        if (options != null) {
            pgpKColumn.sb.append(", ?");
            
            pgpKColumn.params.add(options);
        }
        
        pgpKColumn.sb.append(")");
        
        return pgpKColumn;
    }
    
    public static KColumn pgpSymEncrypt(
        final String data,
        final String pws,
        final String options
    ) {
        KUtils.assertNotNull(data, "data");
        KUtils.assertNotNull(pws, "pws");
        
        final KColumn pgpKColumn = new KColumn();
        
        pgpKColumn.sb.append("PGP_SYM_ENCRYPT(?, ?");
        
        pgpKColumn.params.add(data);
        pgpKColumn.params.add(pws);
        
        if (options != null) {
            pgpKColumn.sb.append(", ?");
            
            pgpKColumn.params.add(options);
        }
        
        pgpKColumn.sb.append(")");
        
        return pgpKColumn;
    }
    
    public static KColumn pgpSymEncryptBytea(
        final KColumn data,
        final String pws,
        final String options
    ) {
        KUtils.assertNotNull(data, "data");
        KUtils.assertNotNull(pws, "pws");
        
        final KColumn pgpKColumn = new KColumn();
        
        pgpKColumn.sb.append("PGP_SYM_ENCRYPT_BYTEA(").append(data.sb).append(", ?");
        
        pgpKColumn.params.addAll(data.params);
        pgpKColumn.params.add(pws);
        
        if (options != null) {
            pgpKColumn.sb.append(", ?");
            
            pgpKColumn.params.add(options);
        }
        
        pgpKColumn.sb.append(")");
        
        return pgpKColumn;
    }
    
    public static KColumn pgpSymDecrypt(
        final KColumn data,
        final String pws,
        final String options
    ) {
        KUtils.assertNotNull(data, "data");
        KUtils.assertNotNull(pws, "pws");
        
        final KColumn pgpKColumn = new KColumn();
        
        pgpKColumn.sb.append("PGP_SYM_DECRYPT(").append(data.sb).append(", ?");
        
        pgpKColumn.params.addAll(data.params);
        pgpKColumn.params.add(pws);
        
        if (options != null) {
            pgpKColumn.sb.append(", ?");
            
            pgpKColumn.params.add(options);
        }
        
        pgpKColumn.sb.append(")");
        
        return pgpKColumn;
    }
    
    public static KColumn pgpSymDecryptBytea(
        final KColumn data,
        final String pws,
        final String options
    ) {
        KUtils.assertNotNull(data, "data");
        KUtils.assertNotNull(pws, "pws");
        
        final KColumn pgpKColumn = new KColumn();
        
        pgpKColumn.sb.append("PGP_SYM_DECRYPT_BYTEA(").append(data.sb).append(", ?");
        
        pgpKColumn.params.addAll(data.params);
        pgpKColumn.params.add(pws);
        
        if (options != null) {
            pgpKColumn.sb.append(", ?");
            
            pgpKColumn.params.add(options);
        }
        
        pgpKColumn.sb.append(")");
        
        return pgpKColumn;
    }
    
    public static KColumn pi() {
        return new KColumn(new StringBuilder("PI()"), true);
    }
    
    public static KColumn position(
        final KColumn kColumn,
        final String valueToLocate
    ) {
        
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(valueToLocate, "valueToLocate");
        
        final KColumn positionKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        positionKColumn.sb.insert(0, "POSITION(? in ").append(")");
        positionKColumn.params.add(valueToLocate);
        
        return positionKColumn;
    }
    
    public static KValTextField position(
        final KValTextField kValTextField,
        final String valueToLocate
    ) {
        
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(valueToLocate, "valueToLocate");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "POWER");
    }
    
    public static KColumn power(
        final KColumn kColumn,
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number1, "number1");
        KUtils.assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "POWER");
    }
    
    protected static KColumn processAny(
        final KQuery kQuery
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        
        final KQueryGenericData subQuery = kQuery.generateSubQueryData();
        
        subQuery.sb.insert(0, "ANY (").append(")");
        
        return new KColumn(subQuery.sb, subQuery.params, true);
    }
    
    protected static KColumn processAll(
        final KQuery kQuery
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        
        final KQueryGenericData subQuery = kQuery.generateSubQueryData();
        
        subQuery.sb.insert(0, "ALL (").append(")");
        
        return new KColumn(subQuery.sb, subQuery.params, true);
    }
    
    private static KAggregateFunctionColumn processCount(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KAggregateFunctionColumn kAggregateFunctionColumn = new KAggregateFunctionColumn(true);
        
        kAggregateFunctionColumn.sb.append("COUNT(").append(kColumn.sb).append(")");
        
        kAggregateFunctionColumn.params.addAll(kColumn.params);
        
        return kAggregateFunctionColumn;
    }
    
    private static KColumn processCountDistinct(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn kColumnCount = new KColumn(true);
        
        kColumnCount.sb.append("COUNT(DISTINCT ").append(kColumn.sb).append(")");
        
        kColumnCount.params.addAll(kColumn.params);
        
        return kColumnCount;
    }
    
    private static KColumn processSome(
        final KQuery kQuery
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        
        final KQueryGenericData subQuery = kQuery.generateSubQueryData();
        
        subQuery.sb.insert(0, "SOME (").append(")");
        
        return new KColumn(subQuery.sb, subQuery.params, true);
    }
    
    public static KColumn quoteIdent(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "QUOTE_IDENT");
    }
    
    public static KColumn quoteLiteral(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "QUOTE_LITERAL");
    }
    
    public static KColumn quoteNullable(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "QUOTE_NULLABLE");
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "RADIANS");
    }
    
    public static KColumn random() {
        return new KColumn(new StringBuilder("RANDOM()"), true);
    }
    
    public static KColumn rangeMerge(
        final KColumn kColumnRange1,
        final KColumn kColumnRange2
    ) {
        return applyTwoParameterFunction(kColumnRange1, kColumnRange2, "RANGE_MERGE");
    }
    
    public static KWindowFunctionColumn rank() {
        return new KWindowFunctionColumn(new StringBuilder("RANK()"), true);
    }
    
    public static KRaw raw(
        final String content,
        final Object... args
    ) {
        return new KRaw(content, args);
    }
    
    public static KColumn repeat(
        final KColumn kColumn,
        final int n
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        final KColumn repeatKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        repeatKColumn.sb.insert(0, "REPEAT(").append(", ").append(n).append(")");
        
        return repeatKColumn;
    }
    
    public static KValTextField repeat(
        final KValTextField kValTextField,
        final int n
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        final KValTextField repeatKValTextField = new KValTextField(kValTextField.sb, kValTextField.params, true);
        
        repeatKValTextField.sb.insert(0, "REPEAT(").append(", ").append(n).append(")");
        
        return repeatKValTextField;
    }
    
    private static KColumn regexpGenericFunction(
        final KColumn kColumn,
        final String pattern,
        final String flags,
        final String function
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(pattern, "pattern");
        
        final KColumn regexpMatchesKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        regexpMatchesKColumn.sb.insert(0, "(").insert(0, function).append(", ?");
        regexpMatchesKColumn.params.add(pattern);
        
        if (flags != null) {
            regexpMatchesKColumn.sb.append(", ?");
            regexpMatchesKColumn.params.add(flags);
        }
        
        regexpMatchesKColumn.sb.append(")");
        
        return regexpMatchesKColumn;
    }
    
    private static KColumn regexpGenericFunction(
        final KValTextField kValTextField,
        final String pattern,
        final String flags,
        final String function
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(pattern, "pattern");
        
        final KColumn regexpMatchesKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
        regexpMatchesKColumn.sb.insert(0, "(").insert(0, function).append(", ?");
        regexpMatchesKColumn.params.add(pattern);
        
        if (flags != null) {
            regexpMatchesKColumn.sb.append(", ?");
            regexpMatchesKColumn.params.add(flags);
        }
        
        regexpMatchesKColumn.sb.append(")");
        
        return regexpMatchesKColumn;
    }
    
    public static KColumn regexpMatches(
        final KColumn kColumn,
        final String pattern
    ) {
        return regexpMatches(kColumn, pattern, null);
    }
    
    public static KColumn regexpMatches(
        final KColumn kColumn,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kColumn, pattern, flags, "REGEXP_MATCHES");
    }
    
    public static KColumn regexpMatches(
        final KValTextField kValTextField,
        final String pattern
    ) {
        return regexpMatches(kValTextField, pattern, null);
    }
    
    public static KColumn regexpMatches(
        final KValTextField kValTextField,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kValTextField, pattern, flags, "REGEXP_MATCHES");
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(pattern, "pattern");
        KUtils.assertNotNull(replacement, "replacement");
        
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
    
    public static KColumn regexpReplace(
        final KValTextField kValTextField,
        final String pattern,
        final String replacement
    ) {
        return regexpReplace(kValTextField, pattern, replacement, null);
    }
    
    public static KColumn regexpReplace(
        final KValTextField kValTextField,
        final String pattern,
        final String replacement,
        final String flags
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(pattern, "pattern");
        KUtils.assertNotNull(replacement, "replacement");
        
        final KColumn regexpReplaceKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
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
    
    public static KColumn regexpSplitToArray(
        final KColumn kColumn,
        final String pattern
    ) {
        return regexpSplitToArray(kColumn, pattern, null);
    }
    
    public static KColumn regexpSplitToArray(
        final KColumn kColumn,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kColumn, pattern, flags, "REGEXP_SPLIT_TO_ARRAY");
    }
    
    public static KColumn regexpSplitToArray(
        final KValTextField kValTextField,
        final String pattern
    ) {
        return regexpSplitToArray(kValTextField, pattern, null);
    }
    
    public static KColumn regexpSplitToArray(
        final KValTextField kValTextField,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kValTextField, pattern, flags, "REGEXP_SPLIT_TO_ARRAY");
    }
    
    public static KColumn regexpSplitToTable(
        final KColumn kColumn,
        final String pattern
    ) {
        return regexpSplitToTable(kColumn, pattern, null);
    }
    
    public static KColumn regexpSplitToTable(
        final KColumn kColumn,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kColumn, pattern, flags, "REGEXP_SPLIT_TO_TABLE");
    }
    
    public static KColumn regexpSplitToTable(
        final KValTextField kValTextField,
        final String pattern
    ) {
        return regexpSplitToTable(kValTextField, pattern, null);
    }
    
    public static KColumn regexpSplitToTable(
        final KValTextField kValTextField,
        final String pattern,
        final String flags
    ) {
        return regexpGenericFunction(kValTextField, pattern, flags, "REGEXP_SPLIT_TO_TABLE");
    }
    
    public static KColumn replace(
        final KColumn kColumn,
        final String from,
        final String to
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(to, "to");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(to, "to");
        
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
        KUtils.assertNotNull(value, "value");
        
        return applyOneParameterFunction(val(value), "REVERSE");
    }
    
    public static KColumn right(
        final KColumn kColumn,
        final int n
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        return applyTwoParameterFunction(
            kColumn,
            new KColumn(new StringBuilder(String.valueOf(n)), new ArrayList<>(), true),
            "RIGHT"
        );
    }
    
    public static KColumn right(
        final KValTextField kValTextField,
        final int n
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        return applyTwoParameterFunction(
            kValTextField,
            new KColumn(new StringBuilder(String.valueOf(n)), new ArrayList<>(), true),
            "RIGHT"
        );
    }
    
    public static KColumn rollup(
        final KColumn... kColumns
    ) {
        return groupingSet("ROLLUP", kColumns);
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyTwoParameterFunction(val(number), kColumn, "ROUND");
    }
    
    public static KColumn round(
        final KColumn kColumn,
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number1, "number1");
        KUtils.assertNotNull(number2, "number2");
        
        return applyTwoParameterFunction(val(number1), val(number2), "ROUND");
    }
    
    public static KWindowFunctionColumn rowNumber() {
        return new KWindowFunctionColumn(new StringBuilder("ROW_NUMBER()"), true);
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
        KUtils.assertNotNull(kColumn, "kColumn");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
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
    
    public static KColumn scale(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "SCALE");
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SINH");
    }
    
    public static KColumn some(
        final KQuery kQuery
    ) {
        return processSome(kQuery);
    }
    
    public static KColumn splitPart(
        final KColumn kColumn,
        final String delimiter,
        final int field
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(delimiter, "delimiter");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(delimiter, "delimiter");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "SQRT");
    }
    
    public static KStringAggColumn stringAgg(
        final KColumn kColumn,
        final KBaseColumnCastable kBaseColumnCastableDelimiter
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kBaseColumnCastableDelimiter, "kBaseColumnCastableDelimiter");
        
        final KStringAggColumn kStringAggColumn = new KStringAggColumn(kColumn.sb, kColumn.params, true);
        
        kStringAggColumn.sb.insert(0, "STRING_AGG(").append(", ").append(kBaseColumnCastableDelimiter.sb).append(")");
        kStringAggColumn.params.addAll(kBaseColumnCastableDelimiter.params);
        
        return kStringAggColumn;
    }
    
    public static KColumn stringToArray(
        final KBaseColumnCastable kBaseColumnCastable,
        final String delimiter
    ) {
        return stringToArray(kBaseColumnCastable, delimiter, null);
    }
    
    public static KColumn stringToArray(
        final KBaseColumnCastable kBaseColumnCastable,
        final String delimiter,
        final String nullString
    ) {
        KUtils.assertNotNull(kBaseColumnCastable, "kBaseColumnCastable");
        KUtils.assertNotNullNotEmpty(delimiter, "delimiter");
        
        final KColumn arrayToStringKColumn = new KColumn(kBaseColumnCastable.sb, kBaseColumnCastable.params, true);
        
        arrayToStringKColumn.sb.insert(0, "STRING_TO_ARRAY(").append(", ?");
        
        arrayToStringKColumn.params.add(delimiter);
        
        if (nullString != null) {
            arrayToStringKColumn.sb.append(", ?");
            
            arrayToStringKColumn.params.add(nullString);
        }
        
        arrayToStringKColumn.sb.append(")");
        
        return arrayToStringKColumn;
    }
    
    public static KColumn strpos(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        return applyTwoParameterFunction(kColumn1, kColumn2, "STRPOS");
    }
    
    public static KColumn strpos(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        return applyTwoParameterFunction(kColumn, kValTextField, "STRPOS");
    }
    
    public static KColumn substr(
        final KColumn kColumn,
        final int from
    ) {
        return substr(kColumn, from, null);
    }
    
    public static KColumn substr(
        final KColumn kColumn,
        final Integer from,
        final Integer count
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(from, "from");
        
        final KColumn substrKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substrKColumn.sb.insert(0, "SUBSTR(").append(", ?");
        substrKColumn.params.add(from);
        
        if (count != null) {
            substrKColumn.sb.append(", ?");
            substrKColumn.params.add(count);
        }
                
        substrKColumn.sb.append(")");
        
        return substrKColumn;
    }
    
    public static KColumn substr(
        final KValTextField kValTextField,
        final int from
    ) {
        return substr(kValTextField, from, null);
    }
    
    public static KColumn substr(
        final KValTextField kValTextField,
        final Integer from,
        final Integer count
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(from, "from");
        
        final KColumn substrKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
        substrKColumn.sb.insert(0, "SUBSTR(").append(", ?");
        substrKColumn.params.add(from);
        
        if (count != null) {
            substrKColumn.sb.append(", ?");
            substrKColumn.params.add(count);
        }
                
        substrKColumn.sb.append(")");
        
        return substrKColumn;
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final int from
    ) {
        return substring(kColumn, from, null);
    }
    
    public static KColumn substring(
        final KColumn kColumn,
        final Integer from,
        final Integer for_
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for_', at least one is required");
        }
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(");
        
        if (from != null) {
            substringKColumn.sb.append(" from ").append(from);
        }
        
        if (for_ != null) {
            substringKColumn.sb.append(" for ").append(for_);
        }
                
        substringKColumn.sb.append(")");
        
        return substringKColumn;
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final int from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final Integer from,
        final Integer for_
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        
        if (from == null && for_ == null) {
            throw KExceptionHelper.internalServerError("Between 'from' and 'for', at least one is required");
        }
        
        final KColumn substringKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(");
        
        if (from != null) {
            substringKColumn.sb.append(" from ").append(from);
        }
        
        if (for_ != null) {
            substringKColumn.sb.append(" for ").append(for_);
        }
                
        substringKColumn.sb.append(")");
        
        return substringKColumn;
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(from, "from");
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(").append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringKColumn.sb.append(" for '").append(for_).append("'");
        }
        
        substringKColumn.sb.append(")");
        
        return substringKColumn;
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final String from
    ) {
        return substring(kValTextField, from, null);
    }
    
    public static KColumn substring(
        final KValTextField kValTextField,
        final String from,
        final String for_
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(from, "from");
        
        final KColumn substringKColumn = new KColumn(kValTextField.sb, kValTextField.params, true);
        
        substringKColumn.sb.insert(0, "SUBSTRING(").append(" from '").append(from).append("'");
        
        if (for_ != null) {
            substringKColumn.sb.append(" for '").append(for_).append("'");
        }
        
        substringKColumn.sb.append(")");
        
        return substringKColumn;
    }
    
    public static KAggregateFunctionColumn sum(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "SUM");
    }
    
    public static KAggregateFunctionColumn sum(
        final KValNumberField kValNumberField
    ) {
        return applyOneParameterAggregateFunction(kValNumberField, "SUM");
    }
    
    public static KAggregateFunctionColumn sum(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterAggregateFunction(val(number), "SUM");
    }
    
    public static KColumn sumDistinct(
        final KColumn kColumn
    ) {
        final KColumn kColumnCloned = kColumn.cloneMe();
        
        kColumnCloned.sb.insert(0, "DISTINCT ");
        
        return applyOneParameterFunction(kColumnCloned, "SUM");
    }
    
    public static KColumn useTableNameAsAlias(
        final KTableColumn kTableColumn
    ) {
        final KTableColumn newKTableColumn = kTableColumn.cloneMe();
        
        if (newKTableColumn.kTable != null) {
            final String content = newKTableColumn.sb.toString().replace(newKTableColumn.kTable.alias + ".", newKTableColumn.kTable.schema + "." + newKTableColumn.kTable.name + ".");
            
            newKTableColumn.sb.setLength(0);
            
            newKTableColumn.sb.append(content);
        }
        
        return newKTableColumn;
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
        KUtils.assertNotNull(number, "number");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TANH");
    }
    
    public static KColumn toAscii(
        final KColumn kColumn
    ) {
        return toAscii(kColumn, null);
    }
    
    public static KColumn toAscii(
        final KColumn kColumn,
        final KEncoding encoding
    ) {
        KUtils.assertNotNullNotEmpty(kColumn, "kColumn");

        final KColumn convertKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        convertKColumn.sb.insert(0, "TO_ASCII(");
        
        if (encoding != null) {
            convertKColumn.sb.append(", '").append(encoding.toSql()).append("'");
        }
        
        convertKColumn.sb.append(")");
        
        return convertKColumn;
    }
    
    public static KColumn toDate(
        final KColumn kColumn,
        final String format
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(format, "format");
        
        final KColumn toDateKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        toDateKColumn.sb.insert(0, "TO_DATE(").append(", ?").append(")");
        toDateKColumn.params.add(format);
        
        return toDateKColumn;
    }
    
    public static KValTextField toDate(
        final KValTextField kValTextField,
        final String format
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(format, "format");
        
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
    
    public static KTotalCount totalCount() {
        return new KTotalCount();
    }
    
    public static KTotalCount totalCount(
        final String attributeName
    ) {
        return new KTotalCount(attributeName);
    }
    
    public static KColumn toTimestamp(
        final KColumn kColumn,
        final String format
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(format, "format");
        
        final KColumn toTimestampKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        toTimestampKColumn.sb.insert(0, "TO_TIMESTAMP(").append(", ?").append(")");
        toTimestampKColumn.params.add(format);
        
        return toTimestampKColumn;
    }
    
    public static KValTextField toTimestamp(
        final KValTextField kValTextField,
        final String format
    ) {
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(format, "format");
        
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
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(to, "to");
        
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
        KUtils.assertNotNull(kValTextField, "kValTextField");
        KUtils.assertNotNull(from, "from");
        KUtils.assertNotNull(to, "to");
        
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
    
    public static KTuple tuple(
        final KBaseColumnCastable... KBaseColumnCastables
    ) {
        KUtils.assertNotNull(KBaseColumnCastables, "KBaseColumnCastables");
        
        if (KBaseColumnCastables.length < 2) {
            throw KExceptionHelper.internalServerError("'TUPLE' function requires at least two KBaseColumnCastables");
        }
        
        final KTuple tuple = new KTuple();
        
        boolean first = true;
        
        tuple.sb.append("(");
        
        for (final KBaseColumnCastable kBaseColumnCastable : KBaseColumnCastables) {
            if (kBaseColumnCastable == null) {
                continue;
            }
            
            if (!first) {
                tuple.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            tuple.sb.append(kBaseColumnCastable.sb);
            tuple.params.addAll(kBaseColumnCastable.params);
        }
        
        tuple.sb.append(")");
        
        return tuple;
    }
    
    public static KTuple tuple(
        final List<Object> list,
        final KTupleFunction kTupleFunction
    ) {
        KUtils.assertNotNullNotEmpty(list, "list", false);
        
        if (list.size() < 2) {
            throw KExceptionHelper.internalServerError("'TUPLE' function requires at least two elements in list");
        }
        
        final KTuple tuple = new KTuple();
        
        boolean firstTuple = true;
        
        tuple.sb.append("(");
        
        for (final Object object : list) {
            if (!firstTuple) {
                tuple.sb.append(", ");
            }
            
            if (firstTuple) {
                firstTuple = false;
            }

            tuple.sb.append("(");
            
            boolean firstValue = true;
            
            for (final Object o : kTupleFunction.run(object)) {
                if (!firstValue) {
                    tuple.sb.append(", ");
                }

                if (firstValue) {
                    firstValue = false;
                }
                
                if (o == null) {
                    tuple.sb.append("NULL");
                } else if (o instanceof KRaw) {
                    tuple.sb.append(((KRaw) o).content);
                    tuple.params.addAll(((KRaw) o).params);
                } else {
                    tuple.sb.append("?");
                    tuple.params.add(o);
                }
            }
            
            tuple.sb.append(")");
        }
        
        tuple.sb.append(")");
        
        return tuple;
    }
    
    protected static KTuple tuple(
        final String... names
    ) {
        KUtils.assertNotNullNotEmpty(names, "names", false);
        
        final KTuple tuple = new KTuple();
        
        boolean first = true;
        
        tuple.sb.append("(");
        
        for (final String name : names) {
            if (name == null) {
                continue;
            }
            
            if (!first) {
                tuple.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            tuple.sb.append(name);
        }
        
        tuple.sb.append(")");
        
        return tuple;
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
    
    protected static KTable table(
        final KQuery kQuery,
        final String alias
    ) {
        return new KTable(kQuery, alias);
    }
    
    protected static KTable table(
        final KQuery kQuery,
        final String alias,
        final KTuple kTuple
    ) {
        return new KTable(kQuery, alias, kTuple);
    }
    
    public static KColumn toChar(
        final KColumn kColumn,
        final String format
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(format, "format");
        
        final KColumn substringKColumn = new KColumn(kColumn.sb, kColumn.params, true);
        
        substringKColumn.sb.insert(0, "TO_CHAR(").append(", ?").append(")");
        substringKColumn.params.add(format);
        
        return substringKColumn;
    }
    
    public static KValNumberField toChar(
        final KValNumberField kValNumberField,
        final String format
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        KUtils.assertNotNull(format, "format");
        
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
        KUtils.assertNotNull(number, "number");
        
        return applyOneParameterFunction(val(number), "TRUNC");
    }
    
    public static KColumn unnest(
        final KColumn kColumn
    ) {
        return applyOneParameterFunction(kColumn, "UNNEST");
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
    
    public static KColumn upperInc(
        final KColumn kColumnRange
    ) {
        return applyOneParameterFunction(kColumnRange, "UPPER_INC");
    }
    
    public static KColumn upperInf(
        final KColumn kColumnRange
    ) {
        return applyOneParameterFunction(kColumnRange, "UPPER_INF");
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
    
    public static KValLocalDateField val(
        final LocalDate val
    ) {
        return new KValLocalDateField(val);
    }
    
    public static KValLocalDateTimeField val(
        final LocalDateTime val
    ) {
        return new KValLocalDateTimeField(val);
    }
    
    public static KValUuidField val(
        final UUID val
    ) {
        return new KValUuidField(val);
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
        KUtils.assertNotNull(op, "op");
        KUtils.assertNotNull(b1, "b1");
        KUtils.assertNotNull(b2, "b2");
        KUtils.assertNotNull(count, "count");
        
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
        KUtils.assertNotNull(op, "op");
        KUtils.assertNotNull(b1, "b1");
        KUtils.assertNotNull(b2, "b2");
        
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
        
        KUtils.assertNotNull(op, "op");
        KUtils.assertNotNull(b1, "b1");
        KUtils.assertNotNull(b2, "b2");
        KUtils.assertNotNull(count, "count");
        
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
        
        KUtils.assertNotNull(op, "op");
        KUtils.assertNotNull(b1, "b1");
        KUtils.assertNotNull(b2, "b2");
        KUtils.assertNotNull(count, "count");
        
        final KColumn widthBucketkColumn = new KColumn();
        
        widthBucketkColumn.sb.append("WIDTH_BUCKET(").append(op).append(", ").append(b1).append(", ").append(b2).append(", ").append(count).append(")");
        
        return widthBucketkColumn;
    }
    
    public static KAggregateFunctionColumn xmlagg(
        final KColumn kColumn
    ) {
        return applyOneParameterAggregateFunction(kColumn, "XMLAGG");
    }
}