package com.myzlab.k;

public class KFunction {

    public static KAliasedField<?> as(
        final KField kField,
        final String alias
    ) {
        return new KAliasedField(kField.sb, alias);
    }
    
    public static KField avg(
        final KField kField
    ) {
        kField.sb.insert(0, "AVG(").append(")");
        
        return kField;
    }
    
    public static KField count() {
        return new KField("COUNT(*)");
    }
    
    public static KField concat(
        final KField... kFields
    ) {
        final KField concatField = new KField();
        
        boolean first = true;
        
        for (final KField kField : kFields) {
            if (!first) {
                concatField.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KInlineField) {
                concatField.sb.append("'").append(kField.sb).append("'");
                
                continue;
            }
            
            concatField.sb.append(kField.sb);
        }
        
        return concatField;
    }
    
    public static KInlineField inline(
        final String inline
    ) {
        return new KInlineField(inline);
    }
    
    public static KInlineField inline(
        final Number inline
    ) {
        return new KInlineField(inline);
    }
    
    public static KInlineField isolate(
        final KInlineField kInlineField
    ) {
        kInlineField.sb.insert(0, "(").append(")");
        
        return kInlineField;
    }
    
    public static KField isolate(
        final KField kField
    ) {
        kField.sb.insert(0, "(").append(")");
        
        return kField;
    }
}