package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KInsertInto extends KQueryInsertInto {

    private KInsertInto(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
    
    private KInsertInto(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KTable kTable
    ) {
        super(kQueryInsertIntoData, kInitializer);
        
        this.process(kTable);
    }
 
    private KInsertInto(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        super(kInitializer);
        
        this.process(kTable);
    }
    
    protected static KInsertInto getInstance(
        final KInitializer kInitializer,
        final KTable kTable
    ) {
        return new KInsertInto(kInitializer, kTable);
    }
    
    protected static KInsertInto getInstance(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KTable kTable
    ) {
        return new KInsertInto(kInitializer, kQueryInsertIntoData, kTable);
    }
    
    public KInsertIntoColumned columns(
        final KColumn... kColumns
    ) {
        return KInsertIntoColumned.getInstance(this.k, this.kQueryInsertIntoData, kColumns);
    }
    
    private void process(
        final KTable kTable
    ) {
        if (kTable == null) {
            throw KExceptionHelper.internalServerError("The 'kTable' param is required"); 
        }
        
        this.kQueryInsertIntoData.sb.append(kQueryInsertIntoData.sb.length() > 0 ? " " : "").append("INSERT INTO ").append(kTable.toSql(false));
        
        if (kTable.kQueryData != null) {
            this.kQueryInsertIntoData.params.addAll(kTable.kQueryData.params);
        }
    }
}
