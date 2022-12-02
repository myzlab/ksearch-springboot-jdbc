package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KDeleteFrom extends KQueryDelete {

    private KDeleteFrom(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
 
    private KDeleteFrom(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        super(kInitializer);
        
        this.process(kTable);
    }
    
    public static KDeleteFrom getInstance(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        return new KDeleteFrom(kInitializer, kTable);
    }
    
    public KUsing using(
        final KTable kTable
    ) {
        return KUsing.getInstance(this.k, this.kQueryDeleteData, kTable);
    }
    
    public KUsing using(
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KUsing.getInstance(this.k, this.kQueryDeleteData, new KTable(null, kRaw.content, null));
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
        
        this.kQueryDeleteData.sb.append("DELETE FROM ").append(kTable.toSql(true));
        
        if (kTable.kQueryData != null) {
            this.kQueryDeleteData.params.addAll(kTable.kQueryData.params);
        }
    }
}
