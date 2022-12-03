package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;

public class KInsertIntoOnConflictDoNothing extends KQueryInsertInto {

    private KInsertIntoOnConflictDoNothing(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
 
    private KInsertIntoOnConflictDoNothing(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData
    ) {
        super(kQueryInsertIntoData, kInitializer);
        
        this.process();
    }
    
    protected static KInsertIntoOnConflictDoNothing getInstance(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData
    ) {
        return new KInsertIntoOnConflictDoNothing(kInitializer, kQueryInsertIntoData);
    }
    
    public KReturningOnConflictDoUpdate returning(
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return KReturningOnConflictDoUpdate.getInstance(this.k, this.kQueryInsertIntoData, kColumnsAllowedToReturning);
    }

    private void process() {
        this.kQueryInsertIntoData.sb.append(" DO NOTHING");
    }
    
    public int execute() {
        return super.executeSingle();
    }
}

