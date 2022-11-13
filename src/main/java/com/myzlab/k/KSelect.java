package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
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
    
    public KGroupBy groupBy(//KCOLUMNALLOWEDTOGROUPBY
        final KBaseColumnCastable... KBaseColumnCastables
    ) {
        return KGroupBy.getInstance(this.kQueryData, KBaseColumnCastables);
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
    
    public KWhere where(
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.kQueryData, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinition... kWindowDefinitions
    ) {
        return KWindow.getInstance(kQueryData, kWindowDefinitions);
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
