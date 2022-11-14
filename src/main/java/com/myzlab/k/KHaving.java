package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;

public class KHaving extends KQuery {
    
    private final KCondition kCondition;

    private KHaving() {
        super();
        
        this.kCondition = null;
    }
    
    private KHaving(
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        super(kQueryData);
        
        KUtils.assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    public static KHaving getInstance(
        final KGroupBy kGroupBy,
        final KCondition kCondition
    ) {
        return new KHaving(kGroupBy.kQueryData, kCondition);
    }
    
    public KHaving andNot(
        final KCondition kCondition
    ) {
        this.kCondition.andNot(kCondition);
        
        return this;
    }
    
    public KHaving orNot(
        final KCondition kCondition
    ) {
        this.kCondition.orNot(kCondition);
        
        return this;
    }

    public KHaving and(
        final KCondition kCondition
    ) {
        this.kCondition.and(kCondition);
        
        return this;
    }
    
    public KHaving or(
        final KCondition kCondition
    ) {
        this.kCondition.or(kCondition);
        
        return this;
    }
    
    public KWindow window(
        final KWindowDefinition... kWindowDefinitions
    ) {
        this.buildhaving();
        
        return KWindow.getInstance(kQueryData, kWindowDefinitions);
    }
    
    public KUnion union() {
        this.buildhaving();
        
        return new KUnion();
    }
    
    public KIntersect intersect() {
        this.buildhaving();
        
        return new KIntersect();
    }
    
    public KExcept except() {
        this.buildhaving();
        
        return new KExcept();
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        this.buildhaving();
        
        return KOrderBy.getInstance(kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        this.buildhaving();
        
        return KLimit.getInstance(kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        this.buildhaving();
        
        return KOffset.getInstance(kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        this.buildhaving();
        
        return KFetch.getInstance(kQueryData, rowCount);
    }
    
    private void buildhaving() {
        KUtils.assertNotNull(this.kCondition, "kCondition");
        
        if (this.kCondition.emptyCondition) {
            return;
        }
        
        this.kQueryData.sb.append(" HAVING ").append(this.kCondition.toSql());
        this.kQueryData.params.addAll(this.kCondition.params);
    }

    @Override
    public void single() {
        this.buildhaving();
        
        super.single();
    }
}
