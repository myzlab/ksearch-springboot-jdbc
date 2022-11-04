package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KCondition {
    
    protected final StringBuilder sb;
//    private int level = 0;
    protected List<Object> params; 
    
    private KCondition() {
        super();
        
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
    }
    
    public static KCondition eq(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kBaseColumn1, kBaseColumn2, "=");
        
        return kCondition;
    }

    public KCondition and() {
        return new KCondition();
    }
    
    public KCondition or() {
        return new KCondition();
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
    
    public static KCondition neq(
        final KBaseColumn kBaseColumn1,
        final KBaseColumn kBaseColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kBaseColumn1, kBaseColumn2, "=");
        
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
        
        this.sb
            .append("NOT (")
            .append(kBaseColumn1.sb)
            .append(" ").append(operator).append(" ")
            .append(kBaseColumn2.sb)
            .append(")");
    }
    
    private void processNotIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        this.sb.append("NOT (LOWER(").append(kColumn1.sb).append(") ").append(operator).append(" LOWER(").append(kColumn2.sb).append("))");
    }
    
    private void processNotIBinaryOperator(
        final KColumn kColumn,
        final KValTextField kValTextField,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (LOWER(").append(kColumn.sb).append(") ").append(operator).append(" ").append(kValTextField.sb).append(")");
    }
    
    private void processNotIBinaryOperator(
        final KValTextField kValTextField,
        final KBaseColumn kBaseColumn,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (").append(kValTextField.sb).append(" ").append(operator).append(" LOWER(").append(kBaseColumn.sb).append("))");
    }
    
    private void processNotIBinaryOperator(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2,
        final String operator
    ) {
        for (final Object param : kValTextField1.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kValTextField2.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("NOT (").append(kValTextField1.sb).append(" ").append(operator).append(" ").append(kValTextField2.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) { 
        this.sb.append("LOWER(").append(kColumn1.sb).append(") ").append(operator).append(" LOWER(").append(kColumn2.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KColumn kColumn,
        final KValTextField kValTextField,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append("LOWER(").append(kColumn.sb).append(") ").append(operator).append(" ").append(kValTextField.sb);
    }
    
    private void processIBinaryOperator(
        final KValTextField kValTextField,
        final KColumn kColumn,
        final String operator
    ) {
        for (final Object param : kValTextField.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append(kValTextField.sb).append(" ").append(operator).append(" LOWER(").append(kColumn.sb).append(")");
    }
    
    private void processIBinaryOperator(
        final KValTextField kValTextField1,
        final KValTextField kValTextField2,
        final String operator
    ) {
        for (final Object param : kValTextField1.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }

        for (final Object param : kValTextField2.params) {
            params.add(param instanceof String ? param.toString().toLowerCase() : param);
        }
        
        this.sb.append(kValTextField1.sb).append(" ").append(operator).append(" ").append(kValTextField2.sb);
    }
}
