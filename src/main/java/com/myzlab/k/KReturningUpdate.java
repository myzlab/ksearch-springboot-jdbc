package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.helper.KExceptionHelper;

public class KReturningUpdate extends KQueryUpdate {

    private KReturningUpdate() {
        super();
    }
    
    private KReturningUpdate(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        super(kQueryUpdateData, kInitializer);
        
        for (final KColumnAllowedToReturning kColumnAllowedToReturning : kColumnsAllowedToReturning) {
            if (kColumnAllowedToReturning == null) {
                throw KExceptionHelper.internalServerError("'kColumnAllowedToReturning' cannot be null"); 
            }
            
            if (kColumnAllowedToReturning instanceof KBaseColumn) {
                this.kQueryUpdateData.kBaseColumns.add((KBaseColumn) kColumnAllowedToReturning);
            } else {
                this.kQueryUpdateData.kBaseColumns.add(new KColumn(new StringBuilder(((KRaw) kColumnAllowedToReturning).content), false));
            }
        }
        
        this.process(kColumnsAllowedToReturning);
    }
    
    public static KReturningUpdate getInstance(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return new KReturningUpdate(kInitializer, kQueryUpdateData, kColumnsAllowedToReturning);
    }
    
    private void process(
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        if (kColumnsAllowedToReturning == null || kColumnsAllowedToReturning.length == 0) {
            throw KExceptionHelper.internalServerError("The 'kBaseColums' param is required"); 
        }
        
        this.kQueryUpdateData.sb.append(" RETURNING ");
        
        for (int i = 0; i < kColumnsAllowedToReturning.length; i++) {
            final KColumnAllowedToReturning kColumnAllowedToReturning = kColumnsAllowedToReturning[i];
            
            if (i > 0) {
                this.kQueryUpdateData.sb.append(", ");
            }
            
            this.kQueryUpdateData.params.addAll(kColumnAllowedToReturning.getParams());
            this.kQueryUpdateData.sb.append(kColumnAllowedToReturning.getSqlToReturning());
        }
    }
    
    @Override
    public <T extends KRow> KCollection<T> execute(
        final Class<T> clazz
    ) {
        return super.execute(clazz);
    }
}