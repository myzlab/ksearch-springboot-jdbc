package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KFrom extends KQuery {
    
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
    
    public static KFrom getInstance(
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
    
    public KFrom from(
        final KTable kTable
    ) {
        this.process(kTable);
        
        return this;
    }
    
    public KFrom fullJoin(
        final KJoinDefinition kJoinDefinition    
    ) {
        this.processGeneralJoin("FULL JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom innerJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("INNER JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom leftJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("LEFT JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KFrom rightJoin(
        final KJoinDefinition kJoinDefinition
    ) {
        this.processGeneralJoin("RIGHT JOIN", kJoinDefinition);
        
        return this;
    }
    
    public KWhere where(
        final KCondition kCondition
    ) {
        return KWhere.getInstance(this.k, this.kQueryData, kCondition);
    }
    
    public KGroupBy groupBy(
        final KColumnAllowedToGroupBy... KColumnsAllowedToGroupBy
    ) {
        return KGroupBy.getInstance(this.k, this.kQueryData, KColumnsAllowedToGroupBy);
    }
    
    public KWindow window(
        final KWindowDefinitionAllowedToWindow... KWindowDefinitionsAllowedToWindow
    ) {
        return KWindow.getInstance(this.k, this.kQueryData, KWindowDefinitionsAllowedToWindow);
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
            
        this.kQueryData.sb.append(kTable.toSql(true));
    }
    
    private void processGeneralJoin(
        final String joinName,
        final KJoinDefinition kJoinDefinition
    ) {
        KUtils.assertNotNull(kJoinDefinition, "kJoinDefinition");
        
        this.kQueryData.sb.append(" ").append(joinName).append(" ").append(kJoinDefinition.table).append(" ON (").append(kJoinDefinition.kCondition.sb).append(")");
        this.kQueryData.params.addAll(kJoinDefinition.kCondition.params);
    }
    
    private void processCrossJoin(
        final KTable kTable
    ) {
        KUtils.assertNotNull(kTable, "kTable");
        
        this.kQueryData.sb.append(" CROSS JOIN ").append(kTable.toSql(true));
    }
}
