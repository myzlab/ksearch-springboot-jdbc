package com.myzlab.k;

public class KWhere {

    public KWhere and() {
        return new KWhere();
    }
    
    public KWhere or() {
        return new KWhere();
    }
    
    public KGroupBy groupBy() {
        return new KGroupBy();
    }
    
    public KWindow window() {
        return new KWindow();
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
}
