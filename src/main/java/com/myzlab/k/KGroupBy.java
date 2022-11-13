package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.regex.Pattern;

public class KGroupBy extends KQuery {
    
    private KGroupBy(
        final KQueryData kQueryData,
        final KBaseColumnCastable... KBaseColumnCastables
    ) {
        super(kQueryData);
        
        assertNotNull(KBaseColumnCastables, "KBaseColumnCastables");
        
        this.process(KBaseColumnCastables);
    }
    
    public static KGroupBy getInstance(
        final KQueryData kQueryData,
        final KBaseColumnCastable... KBaseColumnCastables
    ) {
        return new KGroupBy(kQueryData, KBaseColumnCastables);
    }

    public KHaving having(
        final KCondition kCondition
    ) {
        return KHaving.getInstance(this, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinition... kWindowDefinitions
    ) {
        return KWindow.getInstance(kQueryData, kWindowDefinitions);
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
        final KBaseColumnCastable... KBaseColumnCastables
    ) {
        if (KBaseColumnCastables == null || KBaseColumnCastables.length == 0) {
            throw KExceptionHelper.internalServerError("The 'KBaseColumnCastables' param is required"); 
        }
        
        this.kQueryData.sb.append(" GROUP BY ");
        
        for (int i = 0; i < KBaseColumnCastables.length; i++) {
            final KBaseColumnCastable kBaseColumnCastable = KBaseColumnCastables[i];
            
            if (kBaseColumnCastable == null) {
                throw KExceptionHelper.internalServerError("'kBaseColumnCastable' is required");
            }
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            if (kBaseColumnCastable instanceof KValTextField) {
                throw KExceptionHelper.internalServerError("'KValTextField' values not allowed in GROUP BY clause");
            }
            
            String value = kBaseColumnCastable.sb.toString();
            
            for (final Object o : kBaseColumnCastable.params) {
                if (o == null) {
                    throw KExceptionHelper.internalServerError("'o' is required");
                }
                
                final String quote = o instanceof String ? "'" : "";
                
                value = value.replaceFirst(Pattern.quote("?"), quote + o.toString() + quote);
            }
            
            this.kQueryData.sb.append(value);
        }
    }
    
    private static void assertNotNull(
        final Object o,
        final String name
    ) {
        if (o == null) {
            throw KExceptionHelper.internalServerError("The '" + name + "' param is required"); 
        }
        
        if (o instanceof Object[]) {
            for (final Object o_ : (Object[]) o) {
                if (o_ == null) {
                    throw KExceptionHelper.internalServerError("The '" + name + "' param cannot contain null values"); 
                }
            }
        }
    }
}
