package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;

public class KWhere extends KQuery {
    
    private final KCondition kCondition;
    
    private KWhere() {
        super();
        
        this.kCondition = null;
    }
    
    private KWhere(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        super(kQueryData, kInitializer);
        
        KUtils.assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    public static KWhere getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        return new KWhere(kInitializer, kQueryData, kCondition);
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
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        this.buildWhere();
        
        return KGroupBy.getInstance(this.k, this.kQueryData, KColumnsAllowedToGroupBy);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        this.buildWhere();
        
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    public KUnion union() {
        this.buildWhere();
        
        return new KUnion();
    }
    
    public KIntersect intersect() {
        this.buildWhere();
        
        return new KIntersect();
    }
    
    public KExcept except() {
        this.buildWhere();
        
        return new KExcept();
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        this.buildWhere();
        
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        this.buildWhere();
        
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        this.buildWhere();
        
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        this.buildWhere();
        
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void buildWhere() {
        this.buildWhere(this.kQueryData);
    }

    private void buildWhere(
        final KQueryData kQueryData
    ) {
        KUtils.assertNotNull(this.kCondition, "kCondition");
        
        if (this.kCondition.emptyCondition) {
            return;
        }
        
        kQueryData.sb.append(" WHERE ").append(this.kCondition.toSql());
        kQueryData.params.addAll(this.kCondition.params);
    }
    
    @Override
    public <T> T single(
        final Class<T> clazz
    ) {
        this.buildWhere();
        
        return super.single(clazz);
    }
    
    @Override
    public <T extends KRow> KCollection<T> multiple(
        final Class<T> clazz
    ) {
        this.buildWhere();
        
        return super.multiple(clazz);
    }
    
    @Override
    protected KQueryData generateSubQueryData() {
        final KQueryData newKQueryData = this.kQueryData.cloneMe();
        
        this.buildWhere(newKQueryData);
        
        return newKQueryData;
    }
}
