package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KSelect extends KQuery {
    
    private KSelect() {
        super();
    }
    
    public static KSelect getInstance(final KBaseColumn... kBaseColums) {
        final KSelect kSelect = new KSelect();
        
        kSelect.process(kBaseColums);
        
        return kSelect;
    }
    
    public KSelect select(final KBaseColumn... kBaseColums) {
        this.process(kBaseColums);
        
        return this;
    }
    
    public KFrom from(
        final KTable kTable
    ) {
        return KFrom.getInstance(this, kTable);
    }
    
    public KWhere where() {
        return KWhere.getInstance(this);
    }
    
    public KGroupBy groupBy() {
        return new KGroupBy();
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
            
            this.kQueryData.sb.append(kBaseColum.toSql());
        }
    }
    
}
