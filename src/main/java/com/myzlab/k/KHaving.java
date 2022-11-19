package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import java.util.List;
import java.util.Map;

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
        this.buildhaving();
        
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
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
        
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        this.buildhaving();
        
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        this.buildhaving();
        
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        this.buildhaving();
        
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
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
    public Map<String, Object> single() {
        this.buildhaving();
        
        return super.single();
    }
}
