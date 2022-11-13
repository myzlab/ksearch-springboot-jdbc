package com.myzlab.k;

public class KLimit extends KQuery {
    
    private KLimit() {
        super();
    }
    
    private KLimit(
        final KQueryData kQueryData,
        final int count
    ) {
        super(kQueryData);
        
        this.process(count);
    }
    
    public static KLimit getInstance(
        final KQueryData kQueryData,
        final int count
    ) {
        return new KLimit(kQueryData, count);
    }
    
    public KOffsetUnfetchable offset(
        final int start
    ) {
        return KOffsetUnfetchable.getInstance(kQueryData, start);
    }
    
    private void process(
        final int count
    ) {
        this.kQueryData.sb.append(" LIMIT ").append(count);
    }
}
