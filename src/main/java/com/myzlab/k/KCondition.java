package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KCondition {
    
    public final static int AND_TYPE = 1;
    public final static int OR_TYPE = 2;
    public final static int UNDEFINED_TYPE = 3;
    
    protected final StringBuilder sb;
    protected int operator = 0;
    protected List<Object> params;
    protected int type = UNDEFINED_TYPE;
    
    private KCondition(
    ) {
        super();
        
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
        this.operator = 1;
    }
    
//    private KCondition(
//        final StringBuilder sb,
//        final List<Object> params,
//        final int operator,
//        final int type
//    ) {
//        super();
//        
//        this.sb = new StringBuilder(sb);
//        this.params = new ArrayList(params);
//        this.operator = operator;
//        this.type = type;
//    }
    
    public KCondition and(
        final KCondition kCondition
    ) {
        return applyLogicOperator(kCondition, "AND", AND_TYPE);
    }
    
    public KCondition andNot(
        final KCondition kCondition
    ) {
        return applyLogicOperator(KFunction.not(kCondition), "AND", AND_TYPE);
    }
    
    private KCondition applyLogicOperator(
        final KCondition kCondition,
        final String operator,
        final int type
    ) {
        if (this.type != UNDEFINED_TYPE && this.type != type) {
            this.sb.insert(0, "(").append(")");
        }
        
        this.sb.append(" ").append(operator).append(" ");
        
        final boolean closeNext = kCondition.type != UNDEFINED_TYPE && kCondition.type != type;
        
        if (closeNext) {
            this.sb.append("(");
        }
        
        this.sb.append(kCondition.sb);
        
        if (closeNext) {
            this.sb.append(")");
        }
        
        this.params.addAll(kCondition.params);
        this.operator += kCondition.operator;
        this.type = type;
        
        return this;
    }
    
    public static KCondition eq(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, "=");
        
        return kCondition;
    }
    
    public static KCondition gt(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, ">");
        
        return kCondition;
    }
    
    public static KCondition gte(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, ">=");
        
        return kCondition;
    }
    
    public static KCondition lt(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, "<");
        
        return kCondition;
    }
    
    public static KCondition lte(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, "<=");
        
        return kCondition;
    }
    
    public KCondition or(
        final KCondition kCondition
    ) {
        return applyLogicOperator(kCondition, "OR", OR_TYPE);
    }
    
    public KCondition orNot(
        final KCondition kCondition
    ) {
        return applyLogicOperator(KFunction.not(kCondition), "OR", OR_TYPE);
    }
    
    public static KCondition ieq(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, "=");
        
        return kCondition;
    }
    
    public static KCondition ieq(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, "=");
        
        return kCondition;
    }
    
    public static KCondition ieq(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, "=");
        
        return kCondition;
    }
    
    public static KCondition ieq(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, "=");
        
        return kCondition;
    }
    
    public static KCondition igt(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, ">");
        
        return kCondition;
    }
    
    public static KCondition igt(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, ">");
        
        return kCondition;
    }
    
    public static KCondition igt(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, ">");
        
        return kCondition;
    }
    
    public static KCondition igt(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, ">");
        
        return kCondition;
    }
    
    public static KCondition igte(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, ">=");
        
        return kCondition;
    }
    
    public static KCondition igte(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, ">=");
        
        return kCondition;
    }
    
    public static KCondition igte(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, ">=");
        
        return kCondition;
    }
    
    public static KCondition igte(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, ">=");
        
        return kCondition;
    }
    
    public static KCondition ilt(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, "<");
        
        return kCondition;
    }
    
    public static KCondition ilt(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, "<");
        
        return kCondition;
    }
    
    public static KCondition ilt(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, "<");
        
        return kCondition;
    }
    
    public static KCondition ilt(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, "<");
        
        return kCondition;
    }
    
    public static KCondition ilte(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, "<=");
        
        return kCondition;
    }
    
    public static KCondition ilte(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, "<=");
        
        return kCondition;
    }
    
    public static KCondition ilte(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, "<=");
        
        return kCondition;
    }
    
    public static KCondition ilte(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, "<=");
        
        return kCondition;
    }
    
    public static KCondition iLike(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn1, kColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLike(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kColumn, kValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLike(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField, kColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLike(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processIBinaryOperator(kValTextField1, kValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeAny(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeAny(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeAny(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeAny(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeEndWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append("')");
        
        kCondition.processIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeEndWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0));
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeEndWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(")");
        
        kCondition.processIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeEndWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0));
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeStartWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeStartWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeStartWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition iLikeStartWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition like(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeAny(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeAny(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeAny(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeAny(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeEndWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append("')");
        
        kCondition.processBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeEndWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0));
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeEndWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(")");
        
        kCondition.processBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeEndWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0));
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeStartWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeStartWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeStartWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition likeStartWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition neq(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, "=");
        
        return kCondition;
    }
    
    public static KCondition ngt(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, ">");
        
        return kCondition;
    }
    
    public static KCondition ngte(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, ">=");
        
        return kCondition;
    }
    
    public static KCondition nlt(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, "<");
        
        return kCondition;
    }
    
    public static KCondition nlte(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, "<=");
        
        return kCondition;
    }
    
    public static KCondition nieq(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, "=");
        
        return kCondition;
    }
    
    public static KCondition nieq(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, "=");
        
        return kCondition;
    }
    
    public static KCondition nieq(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, "=");
        
        return kCondition;
    }
    
    public static KCondition nieq(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, "=");
        
        return kCondition;
    }
    
    public static KCondition nigt(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, ">");
        
        return kCondition;
    }
    
    public static KCondition nigt(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, ">");
        
        return kCondition;
    }
    
    public static KCondition nigt(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, ">");
        
        return kCondition;
    }
    
    public static KCondition nigt(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, ">");
        
        return kCondition;
    }
    
    public static KCondition nigte(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, ">=");
        
        return kCondition;
    }
    
    public static KCondition nigte(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, ">=");
        
        return kCondition;
    }
    
    public static KCondition nigte(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, ">=");
        
        return kCondition;
    }
    
    public static KCondition nigte(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, ">=");
        
        return kCondition;
    }
    
    public static KCondition nilt(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, "<");
        
        return kCondition;
    }
    
    public static KCondition nilt(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, "<");
        
        return kCondition;
    }
    
    public static KCondition nilt(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, "<");
        
        return kCondition;
    }
    
    public static KCondition nilt(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, "<");
        
        return kCondition;
    }
    
    public static KCondition nilte(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, "<=");
        
        return kCondition;
    }
    
    public static KCondition nilte(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, "<=");
        
        return kCondition;
    }
    
    public static KCondition nilte(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, "<=");
        
        return kCondition;
    }
    
    public static KCondition nilte(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, "<=");
        
        return kCondition;
    }
    
    public static KCondition notILike(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn1, kColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILike(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kColumn, kValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILike(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField, kColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILike(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotIBinaryOperator(kValTextField1, kValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeAny(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processNotIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeAny(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processNotIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeAny(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processNotIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeAny(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processNotIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeEndWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append("')");
        
        kCondition.processNotIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeEndWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0));
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processNotIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeEndWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(")");
        
        kCondition.processNotIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeEndWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0));
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processNotIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeStartWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processNotIBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeStartWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processNotIBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeStartWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processNotIBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notILikeStartWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processNotIBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLike(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeAny(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processNotBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeAny(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processNotBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeAny(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(", '%')");
        
        kCondition.processNotBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeAny(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(", '%')");
        }
        
        kCondition.processNotBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeEndWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT('%', ").append("')");
        
        kCondition.processNotBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeEndWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, "%" + newValTextField.params.get(0));
        } else {
            newValTextField.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processNotBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeEndWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT('%', ").append(")");
        
        kCondition.processNotBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeEndWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, "%" + newValTextField2.params.get(0));
        } else {
            newValTextField2.sb.insert(0, "CONCAT('%', ").append(")");
        }
        
        kCondition.processNotBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeStartWith(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn2 = kColumn2.cloneMe();
        newKColumn2.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processNotBinaryOperator(kColumn1, newKColumn2, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeStartWith(
        final KColumn kColumn,
        final KValTextField kValTextField
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField = kValTextField.cloneMe();
        
        if (newValTextField.params.size() == 1) {
            newValTextField.params.set(0, newValTextField.params.get(0) + "%");
        } else {
            newValTextField.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processNotBinaryOperator(kColumn, newValTextField, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeStartWith(
        final KValTextField kValTextField,
        final KColumn kColumn
    ) {
        final KCondition kCondition = new KCondition();
        final KColumn newKColumn = kColumn.cloneMe();
        newKColumn.sb.insert(0, "CONCAT(").append(", '%')");
        
        kCondition.processNotBinaryOperator(kValTextField, newKColumn, "LIKE");
        
        return kCondition;
    }
    
    public static KCondition notLikeStartWith(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2
    ) {
        final KCondition kCondition = new KCondition();
        final KValTextField newValTextField2 = kValTextField2.cloneMe();
        
        if (newValTextField2.params.size() == 1) {
            newValTextField2.params.set(0, newValTextField2.params.get(0) + "%");
        } else {
            newValTextField2.sb.insert(0, "CONCAT(").append(", '%')");
        }
        
        kCondition.processNotBinaryOperator(kValTextField1, newValTextField2, "LIKE");
        
        return kCondition;
    }
    
    protected String toSql() {
        return sb.toString();
    }
    
    private void processBinaryOperator(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2,
        final String operator
    ) {        
        params.addAll(kBaseColumn1.params);
        params.addAll(kBaseColumn2.params);
        
        if (!kBaseColumn1.closed) {
            this.sb.append("(");
        }
        
        this.sb.append(kBaseColumn1.sb);
        
        if (!kBaseColumn1.closed) {
            this.sb.append(")");
        }
        
        this.sb.append(" ").append(operator).append(" ");
        
        if (!kBaseColumn2.closed) {
            this.sb.append("(");
        }
        
        this.sb.append(kBaseColumn2.sb);
        
        if (!kBaseColumn2.closed) {
            this.sb.append(")");
        }
    }
    
    private void processNotBinaryOperator(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2,
        final String operator
    ) {
        params.addAll(kBaseColumn1.params);
        params.addAll(kBaseColumn2.params);
        
        this.sb.append("NOT (").append(kBaseColumn1.sb).append(" ").append(operator).append(" ").append(kBaseColumn2.sb).append(")");
    }
    
    private void processNotIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        for (final Object param : kColumn1.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kColumn2.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (LOWER(").append(kColumn1.sb).append(") ").append(operator).append(" LOWER(").append(kColumn2.sb).append("))");
    }
    
    private void processNotIBinaryOperator(
        final KColumn kColumn,
        final KValTextField kValTextField,
        final String operator
    ) {
        for (final Object param : kColumn.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        for (final Object param : kValTextField.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (LOWER(").append(kColumn.sb).append(") ").append(operator).append(" ").append(kValTextField.sb).append(")");
    }
    
    private void processNotIBinaryOperator(
        final KValTextField kValTextField,
        final KBaseColumn kBaseColumn,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        for (final Object param : kBaseColumn.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (").append(kValTextField.sb).append(" ").append(operator).append(" LOWER(").append(kBaseColumn.sb).append("))");
    }
    
    private void processNotIBinaryOperator(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2,
        final String operator
    ) {
        for (final Object param : kValTextField1.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kValTextField2.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (").append(kValTextField1.sb).append(" ").append(operator).append(" ").append(kValTextField2.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        for (final Object param : kColumn1.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kColumn2.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("LOWER(").append(kColumn1.sb).append(") ").append(operator).append(" LOWER(").append(kColumn2.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KColumn kColumn,
        final KValTextField kValTextField,
        final String operator
    ) {
        for (final Object param : kColumn.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        for (final Object param : kValTextField.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("LOWER(").append(kColumn.sb).append(") ").append(operator).append(" ").append(kValTextField.sb);
    }
    
    private void processIBinaryOperator(
        final KValTextField kValTextField,
        final KColumn kColumn,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        for (final Object param : kColumn.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append(kValTextField.sb).append(" ").append(operator).append(" LOWER(").append(kColumn.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2,
        final String operator
    ) {
        for (final Object param : kValTextField1.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kValTextField2.params) {
            this.params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append(kValTextField1.sb).append(" ").append(operator).append(" ").append(kValTextField2.sb);
    }
}
