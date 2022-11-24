package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;

public class KHaving extends KQuery {
    
    private final KCondition kCondition;

    private KHaving() {
        super();
        
        this.kCondition = null;
    }
    
    private KHaving(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        super(kQueryData, kInitializer);
        
        KUtils.assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    public static KHaving getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        return new KHaving(kInitializer, kQueryData, kCondition);
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
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        this.buildHaving();
        
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    public KUnion union() {
        this.buildHaving();
        
        return new KUnion();
    }
    
    public KIntersect intersect() {
        this.buildHaving();
        
        return new KIntersect();
    }
    
    public KExcept except() {
        this.buildHaving();
        
        return new KExcept();
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        this.buildHaving();
        
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        this.buildHaving();
        
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        this.buildHaving();
        
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        this.buildHaving();
        
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void buildHaving() {
        this.buildHaving(this.kQueryData);
    }
    
    private void buildHaving(
        final KQueryData kQueryData
    ) {
        KUtils.assertNotNull(this.kCondition, "kCondition");
        
        if (this.kCondition.emptyCondition) {
            return;
        }
        
        kQueryData.sb.append(" HAVING ").append(this.kCondition.toSql());
        kQueryData.params.addAll(this.kCondition.params);
    }

    @Override
    public <T> T single(
        final Class<T> clazz
    ) {
        this.buildHaving();
        
        return super.single(clazz);
    }
    
    @Override
    protected KQueryData generateSubQueryData() {
        final KQueryData newKQueryData = this.kQueryData.cloneMe();
        
        this.buildHaving(newKQueryData);
        
        return newKQueryData;
    }
}
