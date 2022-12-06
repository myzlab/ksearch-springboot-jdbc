package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KDeleteFrom extends KQueryDelete {

    private KDeleteFrom(
        final KExecutor kExecutor
    ) {
        super(kExecutor);
    }
 
    private KDeleteFrom(
        final KExecutor kExecutor,
        final KTable kTable
    ) {
        super(kExecutor);
        
        this.process(kTable);
    }
    
    private KDeleteFrom(
        final KExecutor kExecutor,
        final KQueryDeleteData kQueryDeleteData,
        final KTable kTable
    ) {
        super(kQueryDeleteData, kExecutor);
        
        this.process(kTable);
    }
    
    protected static KDeleteFrom getInstance(
        final KExecutor kExecutor,
        final KTable kTable
    ) {
        return new KDeleteFrom(kExecutor, kTable);
    }
    
    protected static KDeleteFrom getInstance(
        final KExecutor kExecutor,
        final KQueryDeleteData kQueryDeleteData,
        final KTable kTable
    ) {
        return new KDeleteFrom(kExecutor, kQueryDeleteData, kTable);
    }
    
    public KUsing using(
        final KTable kTable
    ) {
        return KUsing.getInstance(this.k, this.kQueryDeleteData, kTable);
    }
    
    public KUsing using(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KUsing.getInstance(this.k, this.kQueryDeleteData, new KTable(null, kRaw.content, null));
    }
    
    public KUsing using(
        final KCommonTableExpressionAliased kCommonTableExpressionAliased
    ) {
        return KUsing.getInstance(this.k, this.kQueryDeleteData, new KTable(null, kCommonTableExpressionAliased.name, kCommonTableExpressionAliased.alias));
    }
    
    public KWhereDelete where(
        final KCondition kCondition
    ) {
        return KWhereDelete.getInstance(this.k, this.kQueryDeleteData, kCondition);
    }
    
    public KWhereDelete where(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kCondition = new KCondition(kRaw.content);
        
        return KWhereDelete.getInstance(this.k, this.kQueryDeleteData, kCondition);
    }
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        this.kQueryDeleteData.sb.append(kQueryDeleteData.sb.length() > 0 ? " " : "").append("DELETE FROM ").append(kTable.toSql(true));
        
        if (kTable.kQueryData != null) {
            this.kQueryDeleteData.params.addAll(kTable.kQueryData.params);
        }
    }
}
