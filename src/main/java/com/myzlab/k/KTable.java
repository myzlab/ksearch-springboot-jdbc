package com.myzlab.k;

public class KTable {
    
    private final String schema;
    private final String name;
    private final String alias;
    
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
        return KJoinDefinition.getInstance(this.schema + "." + this.name, kCondition);
    }
    
    protected String toSql() {
        return this.schema + "." + this.name;
    }
}
