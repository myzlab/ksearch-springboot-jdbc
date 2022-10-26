package com.myzlab.k;

public class KFrom {
    
    public KInnerJoin join() {
        return new KInnerJoin();
    }
    
    public KInnerJoin innerJoin() {
        return new KInnerJoin();
    }
    
    public KLeftJoin leftJoin() {
        return new KLeftJoin();
    }
    
    public KRightJoin rightJoin() {
        return new KRightJoin();
    }
    
    public KFullJoin fullJoin() {
        return new KFullJoin();
    }
    
    public KCrossJoin crossJoin() {
        return new KCrossJoin();
    }

    public KWhere where() {
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
