package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KFrom extends KQuery implements KQueryAllowedToCombining {
    
    private KFrom() {
        super();
    }
    
    private KFrom(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KTable kTable
    ) {
        super(kQueryData, kInitializer);
        
        this.process(kTable);
    }
    
    protected static KFrom getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KTable kTable
    ) {
        return new KFrom(kInitializer, kQueryData, kTable);
    }
    
    public KFrom crossJoin(
        final KTable kTable  
    ) {
        this.processCrossJoin(kTable);
        
        return this;
    }
    
    public KFrom crossJoin(
        final KRaw kRaw
    ) {
        this.processCrossJoin(kRaw);
        
        return this;
    }
    
    public KFrom from(
        final KTable kTable
    ) {
        this.process(kTable);
        
        return this;
    }
    
    public KFrom from(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        this.process(new KTable(null, kRaw.content, null));
        
        return this;
    }
    
    public KFrom from(
        final KCommonTableExpressionAliased kCommonTableExpressionAliased
    ) {
        this.process( new KTable(null, kCommonTableExpressionAliased.name, kCommonTableExpressionAliased.alias));
        
        return this;
    }
    
    public KFrom fullJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("FULL JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom fullJoin(
        final KRaw kRaw
    ) {
        this.processGeneralJoin("FULL JOIN", kRaw);
        
        return this;
    }
    
    public KFrom innerJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("INNER JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom innerJoin(
        final KRaw kRaw
    ) {
        this.processGeneralJoin("INNER JOIN", kRaw);
        
        return this;
    }
    
    public KFrom leftJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("LEFT JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom leftJoin(
        final KRaw kRaw
    ) {
        this.processGeneralJoin("LEFT JOIN", kRaw);
        
        return this;
    }
    
    public KFrom rightJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("RIGHT JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom rightJoin(
        final KRaw kRaw
    ) {
        this.processGeneralJoin("RIGHT JOIN", kRaw);
        
        return this;
    }
    
    public KWhere where(
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KWhere where(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kCondition = new KCondition(kRaw.content);
        
        return KWhere.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... kColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToGroupBy);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
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
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        if (this.kQueryData.tablesAdded == 0) {
            this.kQueryData.sb.append(" FROM ");
        } else {
            this.kQueryData.sb.append(", ");
        }
        
        this.kQueryData.tablesAdded++;
        
        if (kTable.kQueryData != null) {
            this.kQueryData.params.addAll(kTable.kQueryData.params);
        }
            
        this.kQueryData.sb.append(kTable.toSql(true));
    }
    
    private void processGeneralJoin(
        final String joinName,
        final KJoinDefinition kJoinDefinition
    ) {
        KUtils.assertNotNull(kJoinDefinition, "kJoinDefinition");
        
        if (kJoinDefinition.params != null) {
            this.kQueryData.params.addAll(kJoinDefinition.params);
        }
        
        this.kQueryData.sb.append(" ").append(joinName).append(" ").append(kJoinDefinition.table).append(" ON (").append(kJoinDefinition.kCondition.sb).append(")");
        this.kQueryData.params.addAll(kJoinDefinition.kCondition.params);
    }
    
    private void processGeneralJoin(
        final String joinName,
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        this.kQueryData.sb.append(" ").append(joinName).append(" ").append(kRaw.content);
    }
    
    private void processCrossJoin(
        final KTable kTable
    ) {
        KUtils.assertNotNull(kTable, "kTable");
        
        if (kTable.kQueryData != null) {
            this.kQueryData.params.addAll(kTable.kQueryData.params);
        }
        
        this.kQueryData.sb.append(" CROSS JOIN ").append(kTable.toSql(true));
    }
    
    private void processCrossJoin(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        this.kQueryData.sb.append(" CROSS JOIN ").append(kRaw.content);
    }
    
    @Override
    public KQueryData generateSubQueryData() {
        return this.kQueryData.cloneMe();
    }
}
