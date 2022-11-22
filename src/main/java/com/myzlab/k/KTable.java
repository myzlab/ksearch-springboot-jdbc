package com.myzlab.k;

import lombok.Data;

@Data
public class KTable {
    
    protected final String schema;
    protected final String name;
    protected final String alias;
    
    private KTable() {
        this.schema = null;
        this.name = null;
        this.alias = null;
    }
    
    public KTable(
        final String schema,
        final String name,
        final String alias
    ) {
        this.schema = schema;
        this.name = name;
        this.alias = alias;
    }
    
    public KJoinDefinition on(
        final KCondition kCondition
    ) {
        return KJoinDefinition.getInstance(this.toSql(true), kCondition);
    }
    
    public String toSql(
        final boolean alias
    ) {
        if (alias) {
            return (this.schema != null ? this.schema + "." : "") + this.name + " " + this.alias;
        }
        
        return this.schema + "." + this.name;
    }
}
