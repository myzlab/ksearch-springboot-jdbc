package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.helper.KExceptionHelper;

public class KOrderBy extends KQuery {
    
    private KOrderBy() {
        super();
    }
    
    private KOrderBy(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        super(kQueryData, kExecutor);
        
        KUtils.assertNotNull(kColumnsAllowedToOrderBy, "kColumnsAllowedToOrderBy");
        
        this.process(kColumnsAllowedToOrderBy);
    }
    
    protected static KOrderBy getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        return new KOrderBy(kExecutor, kQueryData, kColumnsAllowedToOrderBy);
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
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        if (kColumnsAllowedToOrderBy == null || kColumnsAllowedToOrderBy.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kColumnsAllowedToOrderBy' param is required"); 
        }
        
        this.kQueryData.sb.append(" ORDER BY ");
        
        for (int i = 0; i < kColumnsAllowedToOrderBy.length; i++) {
            final KColumnAllowedToOrderBy kColumnAllowedToOrderBy = kColumnsAllowedToOrderBy[i];
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.params.addAll(kColumnAllowedToOrderBy.getParams());
            this.kQueryData.sb.append(kColumnAllowedToOrderBy.getSqlToOrderBy());
        }
    }
}
