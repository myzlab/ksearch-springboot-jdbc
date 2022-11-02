package com.myzlab.k;

public class KTable {
    
    private final String name;
    private final String schema;
    
    private KTable() {
        this.name = null;
        this.schema = null;
    }
    
    public KTable(
        final String name
    ) {
        this(name, "public");
    }
    
    public KTable(
        final String name,
        final String schema
    ) {
        this.name = name;
        this.schema = schema;
    }
    
    protected String toSql() {
        return this.schema + "." + this.name;
    }
}
