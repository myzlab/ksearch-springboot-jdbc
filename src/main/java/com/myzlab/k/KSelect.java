package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KColumnAllowedToSelect;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import java.util.ArrayList;
import java.util.List;

public class KSelect extends KQuery implements KQueryAllowedToCombining {
    
    private KSelect(
        final KExecutor kExecutor
    ) {
        super(kExecutor);
    }
    
    private KSelect(
        final KQueryData kQueryData,
        final KExecutor kExecutor
    ) {
        super(kQueryData, kExecutor);
    }
    
    protected static KSelect getInstance(
        final KExecutor kExecutor,
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        KUtils.assertNotNullNotEmpty(kColumnsAllowedToSelect, "kColumnsAllowedToSelect", false);
         
        final List<KBaseColumn> kBaseColumns = new ArrayList<>();
        
        for (final KColumnAllowedToSelect kColumnAllowedToSelect : kColumnsAllowedToSelect) {
            kBaseColumns.add(kColumnAllowedToSelect.getKBaseColumn());
        }
         
        final KSelect kSelect = new KSelect(kExecutor);
        kSelect.kQueryData.kBaseColumns.addAll(kBaseColumns);
        
        kSelect.process(false, kBaseColumns);
        
        return kSelect;
    }
    
//    protected static KSelect getInstance(
//        final KInitializer kBuilder,
//        final KBaseColumn... kBaseColumns
//    ) {
//        final KSelect kSelect = new KSelect(kExecutor);
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
//        
//        kSelect.process(false, kBaseColumns);
//        
//        return kSelect;
//    }
//    
//    protected static KSelect getInstance(
//        final KInitializer kBuilder,
//        final KRaw... kRaws
//    ) {
//        KUtils.assertNotNullNotEmpty(kRaws, "kRaws", false);
//        
//        final KSelect kSelect = new KSelect(kExecutor);
//        
//        final KColumn[] kColumns = new KColumn[kRaws.length];
//        
//        for (int i = 0; i < kRaws.length; i++) {
//            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
//        }
//        
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
//        
//        kSelect.process(false, kColumns);
//        
//        return kSelect;
//    }
    
    protected static KSelect getDistinctInstance(
        final KExecutor kExecutor,
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        KUtils.assertNotNullNotEmpty(kColumnsAllowedToSelect, "kColumnsAllowedToSelect", false);
         
        final List<KBaseColumn> kBaseColumns = new ArrayList<>();
        
        for (final KColumnAllowedToSelect kColumnAllowedToSelect : kColumnsAllowedToSelect) {
            kBaseColumns.add(kColumnAllowedToSelect.getKBaseColumn());
        }
         
        final KSelect kSelect = new KSelect(kExecutor);
        kSelect.kQueryData.kBaseColumns.addAll(kBaseColumns);
        
        kSelect.process(true, kBaseColumns);
        
        return kSelect;
    }
    
//    protected static KSelect getDistinctInstance(
//        final KInitializer kBuilder,
//        final KBaseColumn... kBaseColumns
//    ) {
//        final KSelect kSelect = new KSelect(kExecutor);
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
//        
//        kSelect.process(true, kBaseColumns);
//        
//        return kSelect;
//    }
//    
//    protected static KSelect getDistinctInstance(
//        final KInitializer kBuilder,
//        final KRaw... kRaws
//    ) {
//        KUtils.assertNotNullNotEmpty(kRaws, "kRaws", false);
//        
//        final KSelect kSelect = new KSelect(kExecutor);
//        
//        final KColumn[] kColumns = new KColumn[kRaws.length];
//        
//        for (int i = 0; i < kRaws.length; i++) {
//            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
//        }
//        
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
//        
//        kSelect.process(true, kColumns);
//        
//        return kSelect;
//    }
    
//    protected static KSelect getInstance(
//        final KInitializer kBuilder,
//        final KQueryData kQueryData,
//        final KBaseColumn... kBaseColumns
//    ) {
//        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
//        
//        kSelect.process(false, kBaseColumns);
//        
//        return kSelect;
//    }
    
    protected static KSelect getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        KUtils.assertNotNullNotEmpty(kColumnsAllowedToSelect, "kColumnsAllowedToSelect", false);
         
        final List<KBaseColumn> kBaseColumns = new ArrayList<>();
        
        for (final KColumnAllowedToSelect kColumnAllowedToSelect : kColumnsAllowedToSelect) {
            kBaseColumns.add(kColumnAllowedToSelect.getKBaseColumn());
        }
         
        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
        kSelect.kQueryData.kBaseColumns.addAll(kBaseColumns);
        
        kSelect.process(false, kBaseColumns);
        
        return kSelect;
    }
    
//    protected static KSelect getInstance(
//        final KInitializer kBuilder,
//        final KQueryData kQueryData,
//        final KRaw... kRaws
//    ) {
//        KUtils.assertNotNullNotEmpty(kRaws, "kRaws", false);
//        
//        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
//        
//        final KColumn[] kColumns = new KColumn[kRaws.length];
//        
//        for (int i = 0; i < kRaws.length; i++) {
//            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
//        }
//        
//        kSelect.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
//        
//        kSelect.process(false, kColumns);
//        
//        return kSelect;
//    }
    
    protected static KSelect getDistinctInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        KUtils.assertNotNullNotEmpty(kColumnsAllowedToSelect, "kColumnsAllowedToSelect", false);
        
        final List<KBaseColumn> kBaseColumns = new ArrayList<>();
        
        for (final KColumnAllowedToSelect kColumnAllowedToSelect : kColumnsAllowedToSelect) {
            kBaseColumns.add(kColumnAllowedToSelect.getKBaseColumn());
        }
        
        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
        kSelect.kQueryData.kBaseColumns.addAll(kBaseColumns);
        
        kSelect.process(true, kBaseColumns);
        
        return kSelect;
    }
    
    protected static KSelect getInstance(
        final KExecutor kExecutor,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kExecutor);
        
        kSelect.process(false, kQuery, alias);
        
        return kSelect;
    }
    
    protected static KSelect getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
        
        kSelect.process(false, kQuery, alias);
        
        return kSelect;
    }
    
    protected static KSelect getDistinctInstance(
        final KExecutor kExecutor,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kExecutor);
        
        kSelect.process(true, kQuery, alias);
        
        return kSelect;
    }
    
    protected static KSelect getDistinctInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KQuery kQuery,
        final String alias
    ) {
        final KSelect kSelect = new KSelect(kQueryData, kExecutor);
        
        kSelect.process(true, kQuery, alias);
        
        return kSelect;
    }
    
    public KSelect select(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        KUtils.assertNotNullNotEmpty(kColumnsAllowedToSelect, "kColumnsAllowedToSelect", false);
        
        final List<KBaseColumn> kBaseColumns = new ArrayList<>();
        
        for (final KColumnAllowedToSelect kColumnAllowedToSelect : kColumnsAllowedToSelect) {
            kBaseColumns.add(kColumnAllowedToSelect.getKBaseColumn());
        }
        
        this.kQueryData.kBaseColumns.addAll(kBaseColumns);
        
        this.process(false, kBaseColumns);
        
        return this;
    }
    
//    public KSelect select(
//        final KBaseColumn... kBaseColumns
//    ) {
//        this.kQueryData.kBaseColumns.addAll(Arrays.asList(kBaseColumns));
//        
//        this.process(false, kBaseColumns);
//        
//        return this;
//    }
//    
//    public KSelect select(
//        final KRaw... kRaws
//    ) {
//        KUtils.assertNotNullNotEmpty(kRaws, "kRaws", false);
//        
//        final KColumn[] kColumns = new KColumn[kRaws.length];
//        
//        for (int i = 0; i < kRaws.length; i++) {
//            kColumns[i] = new KColumn(new StringBuilder(kRaws[i].content), false);
//        }
//        
//        this.kQueryData.kBaseColumns.addAll(Arrays.asList(kColumns));
//        
//        this.process(false, kColumns);
//        
//        return this;
//    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        this.process(false, kQuery, alias);
        
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
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw", false);
        
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
    
    private void process(
        final boolean distinct,
        final List<KBaseColumn> kBaseColumns
    ) { 
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
    
    private void process(
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
