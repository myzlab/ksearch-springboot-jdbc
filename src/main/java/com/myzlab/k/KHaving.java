package com.myzlab.k;

import static com.myzlab.k.KFunction.*;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.optional.KOptionalLong;
import java.util.List;

public class KHaving extends KQuery implements KQueryAllowedToCombining {
    
    private final KCondition kCondition;

    private KHaving() {
        super();
        
        this.kCondition = null;
    }
    
    private KHaving(
        final KExecutor kExecutor,
        final List<KSpecialFunction> kSpecialFunctions,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        super(kQueryData, kExecutor, kSpecialFunctions);
        
        KUtils.assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    protected static KHaving getInstance(
        final KExecutor kExecutor,
        final List<KSpecialFunction> kSpecialFunctions,
        final KQueryData kQueryData,
        final KCondition kCondition
    ) {
        return new KHaving(kExecutor, kSpecialFunctions, kQueryData, kCondition);
    }
    
    public KTable as(
        final String alias
    ) {
        return table(this, alias);
    }
    
    public KTable as(
        final String alias,
        final String... names
    ) {
        return table(this, alias, tuple(names));
    }
    
    public KHaving andNot(
        final KCondition kCondition
    ) {
        this.kCondition.andNot(kCondition);
        
        return this;
    }
    
    public KHaving andNot(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content, kRaw.params);
        
        this.kCondition.andNot(kRawCondition);
        
        return this;
    }
    
    public KHaving orNot(
        final KCondition kCondition
    ) {
        this.kCondition.orNot(kCondition);
        
        return this;
    }
    
    public KHaving orNot(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content, kRaw.params);
        
        this.kCondition.orNot(kRawCondition);
        
        return this;
    }

    public KHaving and(
        final KCondition kCondition
    ) {
        this.kCondition.and(kCondition);
        
        return this;
    }
    
    public KHaving and(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content, kRaw.params);
        
        this.kCondition.and(kRawCondition);
        
        return this;
    }
    
    public KHaving or(
        final KCondition kCondition
    ) {
        this.kCondition.or(kCondition);
        
        return this;
    }
    
    
    public KHaving or(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content, kRaw.params);
        
        this.kCondition.or(kRawCondition);
        
        return this;
    }
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KWindow.getInstance(this.k, this.kSpecialFunctions, clone, KWindowDefinitionsAllowedToWindow);
    }
    
    public KCombining union(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "UNION", false);
    }
    
    public KCombining unionAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "UNION", true);
    }
    
    public KCombining intersect(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "INTERSECT", false);
    }
    
    public KCombining intersectAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "INTERSECT", true);
    }
    
    public KCombining except(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "EXCEPT", false);
    }
    
    public KCombining exceptAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KCombining.getInstance(this.k, this.kSpecialFunctions, clone, kQueryAllowedToCombining, "EXCEPT", true);
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KOrderBy.getInstance(this.k, this.kSpecialFunctions, clone, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KLimit.getInstance(this.k, this.kSpecialFunctions, clone, count);
    }
    
    public KLimit limit(
        final long count
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KLimit.getInstance(this.k, this.kSpecialFunctions, clone, count);
    }
    
    public KLimit limit(
        final KOptionalLong kOptionalLong
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        if (!kOptionalLong.isPresent()) {
            return KLimit.getInstance(this.k, this.kSpecialFunctions, clone);
        }
        
        return KLimit.getInstance(this.k, this.kSpecialFunctions, clone, kOptionalLong.get());
    }
    
    public KOffset offset(
        final int start
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KOffset.getInstance(this.k, this.kSpecialFunctions, clone, start);
    }
    
    public KOffset offset(
        final long start
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KOffset.getInstance(this.k, this.kSpecialFunctions, clone, start);
    }
    
    public KOffset offset(
        final KOptionalLong kOptionalLong
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        if (!kOptionalLong.isPresent()) {
            return KOffset.getInstance(this.k, this.kSpecialFunctions, clone);
        }
        
        return KOffset.getInstance(this.k, this.kSpecialFunctions, clone, kOptionalLong.get());
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KFetch.getInstance(this.k, this.kSpecialFunctions, clone, rowCount);
    }
    
    public KFetch fetch(
        final long rowCount
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        return KFetch.getInstance(this.k, this.kSpecialFunctions, clone, rowCount);
    }
    
    public KFetch fetch(
        final KOptionalLong kOptionalLong
    ) {
        final KQueryData clone = this.kQueryData.cloneMe();
        
        this.buildHaving(clone);
        
        if (!kOptionalLong.isPresent()) {
            return KFetch.getInstance(this.k, this.kSpecialFunctions, clone);
        }
        
        return KFetch.getInstance(this.k, this.kSpecialFunctions, clone, kOptionalLong.get());
    }
    
    private void buildHaving(
        final KQueryData kQueryData
    ) {
        KQueryUtils.buildHaving(
            kQueryData, 
            this.kCondition
        );
        
        for (final KSpecialFunction kSpecialFunction : this.kSpecialFunctions) {
            kSpecialFunction.onBuildHaving(this.kCondition);
        }
    }

    @Override
    public <T> T single(
        final Class<T> clazz
    ) {
        this.buildHaving(this.kQueryData);
        
        return super.single(clazz);
    }
    
    @Override
    public <T extends KRow> KCollection<T> multiple(
        final Class<T> clazz
    ) {
        this.buildHaving(this.kQueryData);
        
        return super.multiple(clazz);
    }
    
    @Override
    public KQueryData generateSubQueryData() {
        final KQueryData newKQueryData = this.kQueryData.cloneMe();
        
        KQueryUtils.buildHaving(
            newKQueryData, 
            this.kCondition
        );
        
        return newKQueryData;
    }
}
