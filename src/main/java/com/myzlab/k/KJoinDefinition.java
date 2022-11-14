package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KJoinDefinition {

    protected final String table;
    protected final KCondition kCondition;
    
    private KJoinDefinition() {
        super();
        
        this.table = null;
        this.kCondition = null;
    }
    
    private KJoinDefinition(
        final String table,
        final KCondition kCondition
    ) {
        super();
        
        this.table = table;
        this.kCondition = kCondition;
    }
    
    public static KJoinDefinition getInstance(
        final String table,
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        if (kCondition.emptyCondition) {
            throw KExceptionHelper.internalServerError("'EmptyCondition' is not supported in 'JOIN' clause");
        }
        
        return new KJoinDefinition(table, kCondition);
    }
}
