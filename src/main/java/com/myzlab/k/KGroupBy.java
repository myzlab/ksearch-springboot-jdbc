package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KGroupBy extends KQuery implements KQueryAllowedToCombining {
    
    private KGroupBy(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KColumnAllowedToGroupBy... kColumnsAllowedToGroupBy
    ) {
        super(kQueryData, kInitializer);
        
        KUtils.assertNotNull(kColumnsAllowedToGroupBy, "kColumnsAllowedToGroupBy");
        
        this.process(kColumnsAllowedToGroupBy);
    }
    
    protected static KGroupBy getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KColumnAllowedToGroupBy... kColumnsAllowedToGroupBy
    ) {
        return new KGroupBy(kInitializer, kQueryData, kColumnsAllowedToGroupBy);
    }

    public KHaving having(
        final KCondition kCondition
    ) {
        return KHaving.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KHaving having(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kCondition = new KCondition(kRaw.content);
        
        return KHaving.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    public KCombining union(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", false);
    }
    
    public KCombining unionAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", true);
    }
    
    public KCombining intersect(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", false);
    }
    
    public KCombining intersectAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", true);
    }
    
    public KCombining except(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", false);
    }
    
    public KCombining exceptAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", true);
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void process(
        final KColumnAllowedToGroupBy... kColumnsAllowedToGroupBy
    ) {
        if (kColumnsAllowedToGroupBy == null || kColumnsAllowedToGroupBy.length == 0) {
            throw KExceptionHelper.internalServerError("The 'KColumnsAllowedToGroupBy' param is required"); 
        }
        
        this.kQueryData.sb.append(" GROUP BY ");
        
        for (int i = 0; i < kColumnsAllowedToGroupBy.length; i++) {
            final KColumnAllowedToGroupBy kColumnAllowedToGroupBy = kColumnsAllowedToGroupBy[i];
            
            if (kColumnAllowedToGroupBy == null) {
                throw KExceptionHelper.internalServerError("'kColumnAllowedToGroupBy' is required");
            }
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.params.addAll(kColumnAllowedToGroupBy.getParams());
            this.kQueryData.sb.append(kColumnAllowedToGroupBy.getSqlToGroupBy());
        }
    }
    
    @Override
    public KQueryData generateSubQueryData() {
        return this.kQueryData.cloneMe();
    }
}
