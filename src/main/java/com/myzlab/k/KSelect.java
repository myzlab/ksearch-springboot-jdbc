package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KSelect extends KQuery {
    
    private KSelect() {
        super();
    }
    
    public static KSelect getInstance(
        final KBaseColumn... kBaseColums
    ) {
        final KSelect kSelect = new KSelect();
        
        kSelect.process(kBaseColums);
        
        return kSelect;
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColums
    ) {
        this.process(kBaseColums);
        
        return this;
    }
    
    public KFrom from(
        final KTable kTable
    ) {
        return KFrom.getInstance(this, kTable);
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.kQueryData, KColumnsAllowedToGroupBy);
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
    
    public KWhere where(
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.kQueryData, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    private void process(
        final KBaseColumn... kBaseColums
    ) {
        if (kBaseColums == null || kBaseColums.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kBaseColums' param is required"); 
        }
        
        if (this.kQueryData.columnsAdded == 0) {
            this.kQueryData.sb.append("SELECT ");
        }
        
        for (final KBaseColumn kBaseColum : kBaseColums) {
            if (this.kQueryData.columnsAdded > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.columnsAdded++;
            this.kQueryData.params.addAll(kBaseColum.params);
            
            this.kQueryData.sb.append(kBaseColum.toSql());
        }
    }
    
}
