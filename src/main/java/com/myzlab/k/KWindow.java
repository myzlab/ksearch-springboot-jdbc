package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KWindow extends KQuery {
    
    final List<KWindowDefinition> kWindowDefinitions = new ArrayList<>();
    
    private KWindow(
        final KQueryData kQueryData,
        final KWindowDefinition... kWindowDefinitions
    ) {
        super(kQueryData);
        
        assertNotNull(kWindowDefinitions, "kWindowDefinitions");
        
        this.kWindowDefinitions.addAll(Arrays.asList(kWindowDefinitions));
    }
    
    public static KWindow getInstance(
        final KQueryData kQueryData,
        final KWindowDefinition... kWindowDefinitions
    ) {
        return new KWindow(kQueryData, kWindowDefinitions);
    }
    
    public KWindow window(
        final KWindowDefinition... kWindowDefinitions
    ) {
        assertNotNull(kWindowDefinitions, "kWindowDefinitions");
        
        this.kWindowDefinitions.addAll(Arrays.asList(kWindowDefinitions));
        
        return this;
    }

    public KUnion union() {
        this.buildWindow();
        
        return new KUnion();
    }
    
    public KIntersect intersect() {
        this.buildWindow();
        
        return new KIntersect();
    }
    
    public KExcept except() {
        this.buildWindow();
        
        return new KExcept();
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        this.buildWindow();
        
        return KOrderBy.getInstance(kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit() {
        this.buildWindow();
        
        return new KLimit();
    }
    
    public KOffset offset() {
        this.buildWindow();
        
        return new KOffset();
    }
    
    public KFetch fetch() {
        this.buildWindow();
        
        return new KFetch();
    }
    
    private void buildWindow() {
        this.kQueryData.sb.append(" WINDOW ");
        
        for (int i = 0; i < kWindowDefinitions.size(); i++) {
            final KWindowDefinition kWindowDefinition = kWindowDefinitions.get(i);

            if (kWindowDefinition == null) {
                throw KExceptionHelper.internalServerError("'kWindowDefinition' is required");
            }
            
            if (kWindowDefinition.name == null) {
                throw KExceptionHelper.internalServerError("'name' is required");
            }
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.sb.append(kWindowDefinition.name).append(" AS (").append(kWindowDefinition.sb.toString()).append(")");
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
    
    @Override
    public void single() {
        this.buildWindow();
        
        super.single();
    }
}
