package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.helper.KExceptionHelper;

public class KOrderBy extends KQuery {
    
    private KOrderBy() {
        super();
    }
    
    private KOrderBy(
        final KQueryData kQueryData,
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        super(kQueryData);
        
        KUtils.assertNotNull(kColumnsAllowedToOrderBy, "kColumnsAllowedToOrderBy");
        
        this.process(kColumnsAllowedToOrderBy);
    }
    
    public static KOrderBy getInstance(
        final KQueryData kQueryData,
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        return new KOrderBy(kQueryData, kColumnsAllowedToOrderBy);
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
            
            this.kQueryData.sb.append(kColumnAllowedToOrderBy.getSqlToOrderBy());
        }
    }
}
