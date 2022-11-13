package com.myzlab.k;

public class KOffsetUnfetchable extends KQuery {
    
    private KOffsetUnfetchable() {
        super();
    }
    
    private KOffsetUnfetchable(
        final KQueryData kQueryData,
        final int start
    ) {
        super(kQueryData);
        
        this.process(start);
    }
    
    public static KOffsetUnfetchable getInstance(
        final KQueryData kQueryData,
        final int start
    ) {
        return new KOffsetUnfetchable(kQueryData, start);
    }
    
    private void process(
        final int start
    ) {
        this.kQueryData.sb.append(" OFFSET ").append(start);
    }
}
