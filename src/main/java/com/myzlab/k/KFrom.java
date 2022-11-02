package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KFrom extends KQuery {
    
    private KFrom() {
        super();
    }
    
    private KFrom(
        final KQueryData kQueryData,
        final KTable kTable
    ) {
        super(kQueryData);
        
        this.process(kTable);
    }
    
    public static KFrom getInstance(
        final KSelect kSelect,
        final KTable kTable
    ) {
        return new KFrom(kSelect.kQueryData, kTable);
    }
    
    public KFrom from(
        final KTable kTable
    ) {
        this.process(kTable);
        
        return this;
    }
    
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
    
    public KWhere where(
        final KCondition... kConditions
    ) {
        return KWhere.getInstance(this, kConditions);
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
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        if (this.kQueryData.tablesAdded == 0) {
            this.kQueryData.sb.append(" FROM ");
        } else {
            this.kQueryData.sb.append(", ");
        }
        
        this.kQueryData.tablesAdded++;
            
        this.kQueryData.sb.append(kTable.toSql());
    }
}
