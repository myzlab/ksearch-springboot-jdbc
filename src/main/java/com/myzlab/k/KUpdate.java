package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSetUpdate;
import com.myzlab.k.helper.KExceptionHelper;

public class KUpdate extends KQueryUpdate {

    private KUpdate(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
 
    private KUpdate(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        super(kInitializer);
        
        this.process(kTable);
    }
    
    public static KUpdate getInstance(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        return new KUpdate(kInitializer, kTable);
    }
    
    public KSetUpdate set(
        final KColumn kColumn,
        final KColumnAllowedToSetUpdate kColumnAllowedToSetUpdate
    ) {
        return KSetUpdate.getInstance(this.k, this.kQueryUpdateData, kColumn, kColumnAllowedToSetUpdate);
    }
    
    public KSetUpdate set(
        final KColumn kColumn,
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KSetUpdate.getInstance(this.k, this.kQueryUpdateData, kColumn, new KColumn(new StringBuilder(((KRaw) kRaw).content), false));
    }
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        this.kQueryUpdateData.sb.append("UPDATE ").append(kTable.toSql(true));
        
        if (kTable.kQueryData != null) {
            this.kQueryUpdateData.params.addAll(kTable.kQueryData.params);
        }
    }
}
