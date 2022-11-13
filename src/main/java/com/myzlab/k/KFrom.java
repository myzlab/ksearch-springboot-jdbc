package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
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
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.kQueryData, kCondition);
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.kQueryData, KColumnsAllowedToGroupBy);
    }
    
    public KWindow window(
        final KWindowDefinition... kWindowDefinitions
    ) {
        return KWindow.getInstance(kQueryData, kWindowDefinitions);
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
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        return KOrderBy.getInstance(kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        return KLimit.getInstance(kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        return KOffset.getInstance(kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(kQueryData, rowCount);
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
