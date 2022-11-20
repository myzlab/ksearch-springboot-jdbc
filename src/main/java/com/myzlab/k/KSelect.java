package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.Arrays;

public class KSelect extends KQuery {
    
    private KSelect(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
    
    private KSelect(
        final KQueryData kQueryData,
        final KInitializer kInitializer
    ) {
        super(kQueryData, kInitializer);
    }
    
    public static KSelect getSelectInstance(
        final KInitializer kInitializer,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(false, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getSelectDistinctInstance(
        final KInitializer kInitializer,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(true, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getSelectInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kQueryData, kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processWithPreviousSelect(kBaseColumns);
        
        return kSelect;
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        this.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        this.processSelect(false, kBaseColumns);
        
        return this;
    }
    
    public KFrom from(
        final KTable kTable
    ) {
        return KFrom.getInstance(this.k, this.kQueryData, kTable);
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.k, this.kQueryData, KColumnsAllowedToGroupBy);
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
        return KOrderBy.getInstance(this.k, kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        return KLimit.getInstance(this.k, kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        return KOffset.getInstance(this.k, kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(this.k, kQueryData, rowCount);
    }
    
    public KWhere where(
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(this.k, kQueryData, KWindowDefinitionsAllowedToWindow);
    }
    
    private void processSelect(
        final boolean distinct,
        final KBaseColumn... kBaseColumns
    ) {
        if (kBaseColumns == null || kBaseColumns.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kBaseColums' param is required"); 
        }
        
        if (this.kQueryData.columnsAdded == 0) {
            this.kQueryData.sb.append("SELECT ").append(distinct ? "DISTINCT " : "");
        }
        
        for (final KBaseColumn kBaseColumn : kBaseColumns) {
            if (this.kQueryData.columnsAdded > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.columnsAdded++;
            this.kQueryData.params.addAll(kBaseColumn.params);
            
            this.kQueryData.sb.append(kBaseColumn.toSql());
        }
    }
    
    private void processWithPreviousSelect(
        final KBaseColumn... kBaseColumns
    ) {
        if (kBaseColumns == null || kBaseColumns.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kBaseColums' param is required"); 
        }
        
        this.kQueryData.sb.append(" ");
        
        for (final KBaseColumn kBaseColumn : kBaseColumns) {
            if (this.kQueryData.columnsAdded > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.columnsAdded++;
            this.kQueryData.params.addAll(kBaseColumn.params);
            
            this.kQueryData.sb.append(kBaseColumn.toSql());
        }
    }
}
