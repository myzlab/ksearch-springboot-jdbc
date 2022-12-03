package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.helper.KExceptionHelper;

public class KReturningDelete extends KQueryDelete {

    private KReturningDelete() {
        super();
    }
    
    private KReturningDelete(
        final KInitializer kInitializer,
        final KQueryDeleteData kQueryDeleteData,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        super(kQueryDeleteData, kInitializer);
        
        for (final KColumnAllowedToReturning kColumnAllowedToReturning : kColumnsAllowedToReturning) {
            if (kColumnAllowedToReturning == null) {
                throw KExceptionHelper.internalServerError("'kColumnAllowedToReturning' cannot be null"); 
            }
            
            if (kColumnAllowedToReturning instanceof KBaseColumn) {
                this.kQueryDeleteData.kBaseColumns.add((KBaseColumn) kColumnAllowedToReturning);
            } else {
                this.kQueryDeleteData.kBaseColumns.add(new KColumn(new StringBuilder(((KRaw) kColumnAllowedToReturning).content), false));
            }
        }
        
        this.process(kColumnsAllowedToReturning);
    }
    
    public static KReturningDelete getInstance(
        final KInitializer kInitializer,
        final KQueryDeleteData kQueryDeleteData,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return new KReturningDelete(kInitializer, kQueryDeleteData, kColumnsAllowedToReturning);
    }
    
    private void process(
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        if (kColumnsAllowedToReturning == null || kColumnsAllowedToReturning.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kBaseColums' param is required"); 
        }
        
        this.kQueryDeleteData.sb.append(" RETURNING ");
        
        for (int i = 0; i < kColumnsAllowedToReturning.length; i++) {
            final KColumnAllowedToReturning kColumnAllowedToReturning = kColumnsAllowedToReturning[i];
            
            if (i > 0) {
                this.kQueryDeleteData.sb.append(", ");
            }
            
            this.kQueryDeleteData.params.addAll(kColumnAllowedToReturning.getParams());
            this.kQueryDeleteData.sb.append(kColumnAllowedToReturning.getSqlToReturning());
        }
    }
    
    public <T extends KRow> KCollection<T> execute(
        final Class<T> clazz
    ) {
        return super.executeMapping(clazz);
    }
    
    public KCollection<KRow> execute() {
        return super.executeMapping();
    }
}