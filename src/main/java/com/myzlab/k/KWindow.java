package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KWindow extends KQuery {
    
    final List<KWindowDefinitionAllowedToWindow> KWindowDefinitionsAllowedToWindow = new ArrayList<>();
    
    private KWindow(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        super(kQueryData, kInitializer);
        
        KUtils.assertNotNull(KWindowDefinitionsAllowedToWindow, "KWindowDefinitionsAllowedToWindow");
        
        this.KWindowDefinitionsAllowedToWindow.addAll(Arrays.asList(KWindowDefinitionsAllowedToWindow));
    }
    
    public static KWindow getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return new KWindow(kInitializer, kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        KUtils.assertNotNull(KWindowDefinitionsAllowedToWindow, "KWindowDefinitionsAllowedToWindow");
        
        this.KWindowDefinitionsAllowedToWindow.addAll(Arrays.asList(KWindowDefinitionsAllowedToWindow));
        
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
        
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        this.buildWindow();
        
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        this.buildWindow();
        
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        this.buildWindow();
        
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void buildWindow() {
        this.kQueryData.sb.append(" WINDOW ");
        
        for (int i = 0; i < KWindowDefinitionsAllowedToWindow.size(); i++) {
            final KWindowDefinitionAllowedToWindow kWindowDefinitionAllowedToWindow = KWindowDefinitionsAllowedToWindow.get(i);

            if (kWindowDefinitionAllowedToWindow == null) {
                throw KExceptionHelper.internalServerError("'kWindowDefinitionAllowedToWindow' is required");
            }
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.sb.append(kWindowDefinitionAllowedToWindow.getName()).append(" AS (").append(kWindowDefinitionAllowedToWindow.getSql()).append(")");
        }
    }
    
    @Override
    public <T> T single(
        final Class<T> clazz
    ) {
        this.buildWindow();
        
        return super.single(clazz);
    }
}
