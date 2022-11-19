package com.myzlab.k;

public class KOffsetUnfetchable extends KQuery {
    
    private KOffsetUnfetchable() {
        super();
    }
    
    private KOffsetUnfetchable(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData, kInitializer);
        
        this.process(start);
    }
    
    public static KOffsetUnfetchable getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffsetUnfetchable(kInitializer, kQueryData, start);
    }
    
    private void process(
        final int start
    ) {
        this.kQueryData.sb.append(" OFFSET ").append(start);
    }
}
