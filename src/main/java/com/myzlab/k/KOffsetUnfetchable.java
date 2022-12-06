package com.myzlab.k;

public class KOffsetUnfetchable extends KQuery {
    
    private KOffsetUnfetchable() {
        super();
    }
    
    private KOffsetUnfetchable(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData, kExecutor);
        
        this.process(start);
    }
    
    protected static KOffsetUnfetchable getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffsetUnfetchable(kExecutor, kQueryData, start);
    }
    
    private void process(
        final int start
    ) {
        this.kQueryData.sb.append(" OFFSET ").append(start);
    }
}
