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
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kColumn1, kColumn2, "=", false);
        
        return kCondition;
    }

    public KCondition and() {
        return new KCondition();
    }
    
    public KCondition or() {
        return new KCondition();
    }
    
    public static KCondition neq(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processBinaryOperator(kColumn1, kColumn2, "=", true);
        
        return kCondition;
    }
    
    protected String toSql() {
        return sb.toString();
    }
    
    private void processBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator,
        final boolean not
    ) {
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        if (kColumn1IsVal) {
            params.addAll(((KValField) kColumn1).params);
        }

        if (kColumn2IsVal) {
            params.addAll(((KValField) kColumn2).params);
        }
        
        if (not) {
            this.sb.append("NOT (");
        }
        
        this.sb
            .append(kColumn1IsVal ? ((KValField) kColumn1).sbParam : kColumn1.sb)
            .append(" ").append(operator).append(" ")
            .append(kColumn2IsVal ? ((KValField) kColumn2).sbParam : kColumn2.sb);
        
        if (not) {
            this.sb.append(")");
        }
    }
}
