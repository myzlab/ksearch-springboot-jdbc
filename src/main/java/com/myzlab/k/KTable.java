package com.myzlab.k;

import lombok.Data;

@Data
public class KTable {
    
    protected final String schema;
    protected final String name;
    protected final String alias;
    protected final KQueryData kQueryData;
    
    private KTable() {
        super();
        
        this.schema = null;
        this.name = null;
        this.alias = null;
        this.kQueryData = null;
    }
    
    public KTable(
        final String schema,
        final String name,
        final String alias
    ) {
        super();
        
        this.schema = schema;
        this.name = name;
        this.alias = alias;
        this.kQueryData = null;
    }
    
    public KTable(
        final KQuery kQuery,
        final String alias
    ) {
        super();
        
        this.schema = null;
        this.name = null;
        this.alias = alias;
        this.kQueryData = kQuery.generateSubQueryData();
    }
    
    public KColumn c(
        final String name
    ) {
        return this.processColumn(name);
    }
    
    public KColumn column(
        final String name
    ) {
        return this.processColumn(name);
    }
    
    public KJoinDefinition on(
        final KCondition kCondition
    ) {
        if (this.kQueryData == null) {
            return KJoinDefinition.getInstance(this.toSql(true), kCondition);
        }
        
        return KJoinDefinition.getInstance(this.kQueryData.params, this.toSql(true), kCondition);
    }
    
    public String toSql(
        final boolean alias
    ) {
        if (this.name != null) {
            return toSqlByName(alias);
        }
        
        return toSqlByKQuery(alias);
    }
    
    private String toSqlByName(
        final boolean alias
    ) {
        if (alias && this.alias != null) {
            return (this.schema != null ? this.schema + "." : "") + this.name + " " + this.alias;
        }
        
        return (this.schema != null ? this.schema + "." : "") + this.name;
    }
    
    private String toSqlByKQuery(
        final boolean alias
    ) {
        if (alias && this.alias != null) {
            return "(" + this.kQueryData.sb.toString() + ") " + this.alias;
        }
        
        return "(" + this.kQueryData.sb.toString() + ")";
    }
    
    private KColumn processColumn(
        final String name
    ) {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(this.alias).append(".").append(name);
        
        return new KColumn(sb, true);
    }
}
