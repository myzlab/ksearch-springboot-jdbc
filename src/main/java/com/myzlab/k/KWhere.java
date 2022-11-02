package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KWhere extends KQuery {
    
    private KWhere() {
        super();
    }
    
    private KWhere(
        final KQueryData kQueryData,
        final KCondition... kConditions
    ) {
        super(kQueryData);
        
        this.process(kConditions);
    }
    
    public static KWhere getInstance(
        final KSelect kSelect,
        final KCondition... kConditions
    ) {
        return new KWhere(kSelect.kQueryData, kConditions);
    }

    public static KWhere getInstance(
        final KFrom kFrom,
        final KCondition... kConditions
    ) {
        return new KWhere(kFrom.kQueryData, kConditions);
    }
    
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
    
    private void process(
        final KCondition... kConditions
    ) {
        if (kConditions == null || kConditions.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kConditions' param is required"); 
        }
        
        this.kQueryData.sb.append(" WHERE ");
        
        for (final KCondition kCondition : kConditions) {
            if (this.kQueryData.conditionsAdded > 0) {
//                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.conditionsAdded++;
            
            this.kQueryData.sb.append(kCondition.toSql());
            this.kQueryData.params.addAll(kCondition.params);
        }
    }
}
