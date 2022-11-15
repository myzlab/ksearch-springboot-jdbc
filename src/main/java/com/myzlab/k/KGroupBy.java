package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KGroupBy extends KQuery {
    
    private KGroupBy(
        final KQueryData kQueryData,
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        super(kQueryData);
        
        KUtils.assertNotNull(KColumnsAllowedToGroupBy, "KColumnsAllowedToGroupBy");
        
        this.process(KColumnsAllowedToGroupBy);
    }
    
    public static KGroupBy getInstance(
        final KQueryData kQueryData,
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return new KGroupBy(kQueryData, KColumnsAllowedToGroupBy);
    }

    public KHaving having(
        final KCondition kCondition
    ) {
        return KHaving.getInstance(this, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(kQueryData, KWindowDefinitionsAllowedToWindow);
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
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        if (KColumnsAllowedToGroupBy == null || KColumnsAllowedToGroupBy.length == 0) {
            throw KExceptionHelper.internalServerError("The 'KColumnsAllowedToGroupBy' param is required"); 
        }
        
        this.kQueryData.sb.append(" GROUP BY ");
        
        for (int i = 0; i < KColumnsAllowedToGroupBy.length; i++) {
            final KColumnAllowedToGroupBy kColumnAllowedToGroupBy = KColumnsAllowedToGroupBy[i];
            
            if (kColumnAllowedToGroupBy == null) {
                throw KExceptionHelper.internalServerError("'kColumnAllowedToGroupBy' is required");
            }
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.sb.append(kColumnAllowedToGroupBy.getSqlToGroupBy());
        }
    }
}
