package com.myzlab.k;

public class KSelect extends KQuery {
    
    public KSelect(
        final KBaseField... kBaseColumns
    ) {
        this.process(kBaseColumns);
    }

    public KSelect select() {
        return new KSelect();
    }
    
    public KFrom from() {
        return new KFrom();
    }
    
    public KWhere where() {
        return new KWhere();
    }
    
    public KGroupBy groupBy() {
        return new KGroupBy();
    }
    
    public KUnion union() {
        return new KUnion();
    }
    
    public KIntersect intersect() {
        return new KIntersect();
    }
    
    public KExcept except() {
        return new KExcept();
    }
    
    public KOrderBy orderBy() {
        return new KOrderBy();
    }
    
    public KLimit limit() {
        return new KLimit();
    }
    
    public KOffset offset() {
        return new KOffset();
    }
    
    public KFetch fetch() {
        return new KFetch();
    }
    
    private void process(
        final KBaseField... kBaseColumns
    ) {
        this.sb.append("SELECT ");
        
        boolean first = true;
        
        for (final KBaseField kBaseColumn : kBaseColumns) {
            if (!first) {
                this.sb.append(", ");
            }
            
            if (first) {
                first = false;
            }
            
            this.sb.append(kBaseColumn.toSql());
        }
    }
}
