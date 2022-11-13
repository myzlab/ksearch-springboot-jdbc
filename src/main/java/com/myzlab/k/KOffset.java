package com.myzlab.k;

public class KOffset extends KQuery {
    
    private KOffset() {
        super();
    }
    
    private KOffset(
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData);
        
        this.process(start);
    }
    
    public static KOffset getInstance(
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffset(kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(kQueryData, rowCount);
    }
    
    private void process(
        final int start
    ) {
        this.kQueryData.sb.append(" OFFSET ").append(start);
    }
}
