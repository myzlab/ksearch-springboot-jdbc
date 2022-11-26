package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KWindow extends KQuery implements KQueryAllowedToCombining {
    
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

    public KCombining union(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", false);
    }
    
    public KCombining unionAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", true);
    }
    
    public KCombining intersect(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", false);
    }
    
    public KCombining intersectAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", true);
    }
    
    public KCombining except(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", false);
    }
    
    public KCombining exceptAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        this.buildWindow();
        
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", true);
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
        this.buildWindow(this.kQueryData);
    }
    
    private void buildWindow(
        final KQueryData kQueryData
    ) {
        kQueryData.sb.append(" WINDOW ");
        
        for (int i = 0; i < KWindowDefinitionsAllowedToWindow.size(); i++) {
            final KWindowDefinitionAllowedToWindow kWindowDefinitionAllowedToWindow = KWindowDefinitionsAllowedToWindow.get(i);

            if (kWindowDefinitionAllowedToWindow == null) {
                throw KExceptionHelper.internalServerError("'kWindowDefinitionAllowedToWindow' is required");
            }
            
            if (i > 0) {
                kQueryData.sb.append(", ");
            }
            
            kQueryData.sb.append(kWindowDefinitionAllowedToWindow.getName()).append(" AS (").append(kWindowDefinitionAllowedToWindow.getSql()).append(")");
        }
    }
    
    @Override
    public <T> T single(
        final Class<T> clazz
    ) {
        this.buildWindow();
        
        return super.single(clazz);
    }
    
    @Override
    public <T extends KRow> KCollection<T> multiple(
        final Class<T> clazz
    ) {
        this.buildWindow();
        
        return super.multiple(clazz);
    }
    
    @Override
    public KQueryData generateSubQueryData() {
        final KQueryData newKQueryData = this.kQueryData.cloneMe();
        
        this.buildWindow(newKQueryData);
        
        return newKQueryData;
    }
}
