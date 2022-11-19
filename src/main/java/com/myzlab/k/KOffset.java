package com.myzlab.k;

public class KOffset extends KQuery {
    
    private KOffset() {
        super();
    }
    
    private KOffset(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData, kInitializer);
        
        this.process(start);
    }
    
    public static KOffset getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffset(kInitializer, kQueryData, start);
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
