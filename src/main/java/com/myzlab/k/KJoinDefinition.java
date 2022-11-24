package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public class KJoinDefinition {

    protected final List<Object> params;
    protected final String table;
    protected final KCondition kCondition;
    
    private KJoinDefinition() {
        super();
        
        this.params = null;
        this.table = null;
        this.kCondition = null;
    }
    
    private KJoinDefinition(
        final String table,
        final KCondition kCondition
    ) {
        super();
        
        this.params = null;
        this.table = table;
        this.kCondition = kCondition;
    }
    
    private KJoinDefinition(
        final List<Object> params,
        final String table,
        final KCondition kCondition
    ) {
        super();
        
        this.params = params;
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
    
    public static KJoinDefinition getInstance(
        final List<Object> params,
        final String table,
        final KCondition kCondition
    ) {
        KUtils.assertNotNull(kCondition, "kCondition");
        
        if (kCondition.emptyCondition) {
            throw KExceptionHelper.internalServerError("'EmptyCondition' is not supported in 'JOIN' clause");
        }
        
        return new KJoinDefinition(params, table, kCondition);
    }
}
