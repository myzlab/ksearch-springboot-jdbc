package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.Arrays;

public class KSelect extends KQuery implements KQueryAllowedToCombining {
    
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
    
    public static KSelect getInstance(
        final KInitializer kInitializer,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(false, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getInstance(
        final KInitializer kInitializer,
        final KRaw... kRaws
    ) {
        KUtils.assertNotNullNotEmpty(kRaws, "kRaws");
        
        final KSelect kSelect = new KSelect(kInitializer);
        
        final KColumn[] kColumns = new KColumn[kRaws.length];
        
        for (int i = 0; i < kRaws.length; i++) {
            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
        }
        
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
        
        kSelect.processSelect(false, kColumns);
        
        return kSelect;
    }
    
    public static KSelect getDistinctInstance(
        final KInitializer kInitializer,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(true, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kQueryData, kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(false, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KRaw... kRaws
    ) {
        KUtils.assertNotNullNotEmpty(kRaws, "kRaws");
        
        final KSelect kSelect = new KSelect(kQueryData, kInitializer);
        
        final KColumn[] kColumns = new KColumn[kRaws.length];
        
        for (int i = 0; i < kRaws.length; i++) {
            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
        }
        
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
        
        kSelect.processSelect(false, kColumns);
        
        return kSelect;
    }
    
    public static KSelect getDistinctInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KBaseColumn... kBaseColumns
    ) {
        final KSelect kSelect = new KSelect(kQueryData, kInitializer);
        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        kSelect.processSelect(true, kBaseColumns);
        
        return kSelect;
    }
    
    public static KSelect getInstance(
        final KInitializer kInitializer,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        
        kSelect.processSelect(false, kQuery, alias);
        
        return kSelect;
    }
    
    public static KSelect getDistinctInstance(
        final KInitializer kInitializer,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kInitializer);
        
        kSelect.processSelect(true, kQuery, alias);
        
        return kSelect;
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        this.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
        
        this.processSelect(false, kBaseColumns);
        
        return this;
    }
    
    public KSelect select(
        final KRaw... kRaws
    ) {
        KUtils.assertNotNullNotEmpty(kRaws, "kRaws");
        
        final KColumn[] kColumns = new KColumn[kRaws.length];
        
        for (int i = 0; i < kRaws.length; i++) {
            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
        }
        
        this.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
        
        this.processSelect(false, kColumns);
        
        return this;
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        this.processSelect(false, kQuery, alias);
        
        return this;
    }

    public KFrom from(
        final KTable kTable
    ) {
        return KFrom.getInstance(this.k, this.kQueryData, kTable);
    }
    
    public KFrom from(
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KFrom.getInstance(this.k, this.kQueryData, new KTable(null, kRaw.content, null));
    }
    
    public KFrom from(
        final KCommonTableExpressionAliased kCommonTableExpressionAliased
    ) {
        return KFrom.getInstance(this.k, this.kQueryData, new KTable(null, kCommonTableExpressionAliased.name, kCommonTableExpressionAliased.alias));
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.k, this.kQueryData, KColumnsAllowedToGroupBy);
    }
    
    public KCombining union(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", false);
    }
    
    public KCombining unionAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", true);
    }
    
    public KCombining intersect(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", false);
    }
    
    public KCombining intersectAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", true);
    }
    
    public KCombining except(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", false);
    }
    
    public KCombining exceptAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", true);
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
        
        if (this.kQueryData.sb.length() > 0 && this.kQueryData.columnsAdded == 0) {
            this.kQueryData.sb.append(" ");
        }
        
        if (this.kQueryData.columnsAdded == 0 && !this.kQueryData.distinctOn) {
            this.kQueryData.sb.append("SELECT ").append(distinct ? "DISTINCT " : "");
        }
        
        for (final KBaseColumn kBaseColumn : kBaseColumns) {
            if (this.kQueryData.columnsAdded > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.columnsAdded++;
            this.kQueryData.params.addAll(kBaseColumn.params);
            
            this.kQueryData.sb.append(kBaseColumn.sb);
        }
    }
    
    private void processSelect(
        final boolean distinct,
        final KQuery kQuery,
        final String alias
    ) {
        KUtils.assertNotNull(kQuery, "kQuery");
        KUtils.assertNotNull(alias, "alias");
        
        final KQueryData subQuery = kQuery.generateSubQueryData();
        
        this.kQueryData.kBaseColumns.add(new KColumn(subQuery.sb, subQuery.params, false).as(alias));
        
        if (this.kQueryData.sb.length() > 0 && this.kQueryData.columnsAdded == 0) {
            this.kQueryData.sb.append(" ");
        }
        
        if (this.kQueryData.columnsAdded == 0 && !this.kQueryData.distinctOn) {
            this.kQueryData.sb.append("SELECT ").append(distinct ? "DISTINCT " : "");
        }
        
        if (this.kQueryData.columnsAdded > 0) {
            this.kQueryData.sb.append(", ");
        }

        this.kQueryData.columnsAdded++;
        this.kQueryData.params.addAll(subQuery.params);

        this.kQueryData.sb.append("(").append(subQuery.sb).append(") AS ").append(alias);
    }

    @Override
    public KQueryData generateSubQueryData() {
        return this.kQueryData.cloneMe();
    }
    
}
