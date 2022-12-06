package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSetUpdate;
import com.myzlab.k.helper.KExceptionHelper;

public class KUpdate extends KQueryUpdate {

    private KUpdate(
        final KExecutor kExecutor
    ) {
        super(kExecutor);
    }
 
    private KUpdate(
        final KExecutor kExecutor,
        final KTable kTable
    ) {
        super(kExecutor);
        
        this.process(kTable);
    }
    
    private KUpdate(
        final KExecutor kExecutor,
        final KQueryUpdateData kQueryUpdateData,
        final KTable kTable
    ) {
        super(kQueryUpdateData, kExecutor);
        
        this.process(kTable);
    }
    
    public static KUpdate getInstance(
        final KExecutor kExecutor,
        final KTable kTable
    ) {
        return new KUpdate(kExecutor, kTable);
    }
    
    public static KUpdate getInstance(
        final KExecutor kExecutor,
        final KQueryUpdateData kQueryUpdateData,
        final KTable kTable
    ) {
        return new KUpdate(kExecutor, kQueryUpdateData, kTable);
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
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KSetUpdate.getInstance(this.k, this.kQueryUpdateData, kColumn, new KColumn(new StringBuilder(((KRaw) kRaw).content), false));
    }
    
    public KSetUpdate set(
        final KColumn kColumn,
        final KQuery kQuery
    ) {
        return KSetUpdate.getInstance(this.k, this.kQueryUpdateData, kColumn, kQuery);
    }
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        this.kQueryUpdateData.sb.append(kQueryUpdateData.sb.length() > 0 ? " " : "").append("UPDATE ").append(kTable.toSql(true));
        
        if (kTable.kQueryData != null) {
            this.kQueryUpdateData.params.addAll(kTable.kQueryData.params);
        }
    }
}
