package com.myzlab.k;

public class KOffset extends KQuery {
    
    private KOffset() {
        super();
    }
    
    private KOffset(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData, kExecutor);
        
        this.process(start);
    }
    
    protected static KOffset getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffset(kExecutor, kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void process(
        final int start
    ) {
        this.kQueryData.sb.append(" OFFSET ").append(start);
    }
}
