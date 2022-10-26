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
        final KField avgKField = new KField(kField.sb);
        
        avgKField.sb.insert(0, "AVG(").append(")");
        
        return avgKField;
    }
    
    public static KField cast(
        final KField kField,
        final KDataType kDataType    
    ) {
        final KField castKField = new KField(kField.sb);
        
        castKField.sb.insert(0, "CAST(").append(" AS ").append(kDataType.toSql()).append(")");
        
        return castKField;
    }
    
    public static KField count() {
        return new KField("COUNT(*)");
    }
    
    public static KField concat(
        final KField... kFields
    ) {
        final KField concatKField = new KField();
        
        boolean first = true;
        
        for (final KField kField : kFields) {
            if (!first) {
                concatKField.sb.append(" || ");
            }
            
            if (first) {
                first = false;
            }
            
            if (kField instanceof KInlineField) {
                concatKField.sb.append("'").append(kField.sb).append("'");
                
                continue;
            }
            
            concatKField.sb.append(kField.sb);
        }
        
        return concatKField;
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
        final KInlineField isolateKInlineField = kInlineField.cloneMe();
        
        isolateKInlineField.sb.insert(0, "(").append(")");
        
        return isolateKInlineField;
    }
    
    public static KField isolate(
        final KField kField
    ) {
        final KField isolateKField = kField.cloneMe();
        
        isolateKField.sb.insert(0, "(").append(")");
        
        return isolateKField;
    }
    
    public static KField getJsonArray(
        final KField kField,
        final int index
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonArrayAsText(
        final KField kField,
        final int index
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>").append(index);
        
        return jsonKField;
    }
    
    public static KField getJsonObject(
        final KField kField,
        final String name
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAsText(
        final KField kField,
        final String name
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("->>'").append(name).append("'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPath(
        final KField kField,
        final String path
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>'{").append(path).append("}'");
        
        return jsonKField;
    }
    
    public static KField getJsonObjectAtPathAsText(
        final KField kField,
        final String path
    ) {
        final KField jsonKField = new KField(kField.sb);
        
        jsonKField.sb.append("#>>'{").append(path).append("}'");
        
        return jsonKField;
    }
}
/*//#>
 //Get JSON object at specified path--#>
*/