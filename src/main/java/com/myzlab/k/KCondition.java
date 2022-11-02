package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
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
        
        kCondition.processBinaryOperator(kColumn1, kColumn2, "=");
        
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
    
    public static KCondition neq(
        final KColumn kColumn1,
        final KColumn kColumn2
    ) {
        final KCondition kCondition = new KCondition();
        
        kCondition.processNotBinaryOperator(kColumn1, kColumn2, "=");
        
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
    
    protected String toSql() {
        return sb.toString();
    }
    
    private void processBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        if (kColumn1IsVal) {
            params.addAll(((KValField) kColumn1).params);
        }

        if (kColumn2IsVal) {
            params.addAll(((KValField) kColumn2).params);
        }
        
        this.sb
            .append(kColumn1IsVal ? ((KValField) kColumn1).sbParam : kColumn1.sb)
            .append(" ").append(operator).append(" ")
            .append(kColumn2IsVal ? ((KValField) kColumn2).sbParam : kColumn2.sb);
    }
    
    private void processNotBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        if (kColumn1IsVal) {
            params.addAll(((KValField) kColumn1).params);
        }

        if (kColumn2IsVal) {
            params.addAll(((KValField) kColumn2).params);
        }
        
        this.sb
            .append("NOT (")
            .append(kColumn1IsVal ? ((KValField) kColumn1).sbParam : kColumn1.sb)
            .append(" ").append(operator).append(" ")
            .append(kColumn2IsVal ? ((KValField) kColumn2).sbParam : kColumn2.sb)
            .append(")");
    }
    
    private void processNotIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        if (kColumn1IsVal) {
            final boolean isText = ((KValField) kColumn1).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError("The 'NOT' + 'I' + '" + operator + "' operator only can be used with a column or with a 'val' of text type");
            }
            
            for (final Object param : ((KValField) kColumn1).params) {
                params.add(param instanceof String ? param.toString().toLowerCase() : param);
            }
        }

        if (kColumn2IsVal) {
            final boolean isText = ((KValField) kColumn2).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError("The 'NOT' + 'I' + '" + operator + "' operator only can be used with a column or with a 'val' of text type");
            }
            
            for (final Object param : ((KValField) kColumn2).params) {
                params.add(param instanceof String ? param.toString().toLowerCase() : param);
            }
        }
        
        this.sb
            .append("NOT (")
            .append(kColumn1IsVal ? ((KValField) kColumn1).sbParam : ("LOWER(" + kColumn1.sb + ")"))
            .append(" ").append(operator).append(" ")
            .append(kColumn2IsVal ? ((KValField) kColumn2).sbParam : ("LOWER(" + kColumn2.sb + ")"))
            .append(")");
    }
    
    private void processIBinaryOperator(
        final KColumn kColumn1,
        final KColumn kColumn2,
        final String operator
    ) {
        final boolean kColumn1IsVal = kColumn1 instanceof KValField;
        final boolean kColumn2IsVal = kColumn2 instanceof KValField;
        
        if (kColumn1IsVal) {
            final boolean isText = ((KValField) kColumn1).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError("The 'I' + '" + operator + "' operator only can be used with a column or with a 'val' of text type");
            }
            
            for (final Object param : ((KValField) kColumn1).params) {
                params.add(param instanceof String ? param.toString().toLowerCase() : param);
            }
        }

        if (kColumn2IsVal) {
            final boolean isText = ((KValField) kColumn2).isText;
            
            if (!isText) {
                throw KExceptionHelper.internalServerError("The 'I' + '" + operator + "' operator only can be used with a column or with a 'val' of text type");
            }
            
            for (final Object param : ((KValField) kColumn2).params) {
                params.add(param instanceof String ? param.toString().toLowerCase() : param);
            }
        }
        
        this.sb
            .append(kColumn1IsVal ? ((KValField) kColumn1).sbParam : ("LOWER(" + kColumn1.sb + ")"))
            .append(" ").append(operator).append(" ")
            .append(kColumn2IsVal ? ((KValField) kColumn2).sbParam : ("LOWER(" + kColumn2.sb + ")"));
    }
}
