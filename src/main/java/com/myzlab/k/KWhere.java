package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KWhere extends KQuery {
    
    private final KCondition kCondition;
    
    private KWhere() {
        super();
        
        this.kCondition = null;
    }
    
    private KWhere(
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        super(kQueryData);
        
        assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    public static KWhere getInstance(
        final KSelect kSelect,
        final KCondition kCondition
    ) {
        return new KWhere(kSelect.kQueryData, kCondition);
    }

    public static KWhere getInstance(
        final KFrom kFrom,
        final KCondition kCondition
    ) {
        return new KWhere(kFrom.kQueryData, kCondition);
    }
    
    public KWhere andNot(
        final KCondition kCondition
    ) {
        this.kCondition.andNot(kCondition);
        
        return this;
    }
    
    public KWhere orNot(
        final KCondition kCondition
    ) {
        this.kCondition.orNot(kCondition);
        
        return this;
    }

    public KWhere and(
        final KCondition kCondition
    ) {
        this.kCondition.and(kCondition);
        
        return this;
    }
    
    public KWhere or(
        final KCondition kCondition
    ) {
        this.kCondition.or(kCondition);
        
        return this;
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
    
    private void buildWhere() {
        assertNotNull(this.kCondition, "kCondition");
        
        this.kQueryData.sb.append(" WHERE ").append(this.kCondition.toSql());
        this.kQueryData.params.addAll(this.kCondition.params);
    }
    
    private static void assertNotNull(
        final Object o,
        final String name
    ) {
        if (o == null) {
            throw KExceptionHelper.internalServerError("The '" + name + "' param is required"); 
        }
        
        if (o instanceof Object[]) {
            for (final Object o_ : (Object[]) o) {
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
    }

    @Override
    public void single() {
        this.buildWhere();
        
        super.single();
    }
    
    
}
