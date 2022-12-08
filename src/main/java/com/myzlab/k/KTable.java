package com.myzlab.k;

import lombok.Data;

@Data
public class KTable {
    
    protected final String schema;
    protected final String name;
    protected String alias;
    protected final KQueryData kQueryData;
    protected final boolean isRoot;
    
    private KTable() {
        super();
        
        this.schema = null;
        this.name = null;
        this.alias = null;
        this.kQueryData = null;
        this.isRoot = false;
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
        this.isRoot = schema != null && name != null && alias != null;
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
        this.isRoot = false;
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
    
    public KJoinDefinition on(
        final KCondition kCondition,
        final KEdge kEdge
    ) {
        if (this.kQueryData == null) {
            return KJoinDefinition.getInstance(this.toSql(true), kCondition, kEdge);
        }
        
        return KJoinDefinition.getInstance(this.kQueryData.params, this.toSql(true), kCondition, kEdge);
    }
    
    public KJoinDefinition on(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return on(new KCondition(kRaw.content));
    }
    
    public KJoinDefinition on(
        final KRaw kRaw,
        final KEdge kEdge
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return on(new KCondition(kRaw.content), kEdge);
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
    
    protected Class<? extends KRow> getKRowClass() {
        return null;
    }
}
