package com.myzlab.k;

public class KLimit extends KQuery {
    
    private KLimit() {
        super();
    }
    
    private KLimit(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int count
    ) {
        super(kQueryData, kInitializer);
        
        this.process(count);
    }
    
    protected static KLimit getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final int count
    ) {
        return new KLimit(kInitializer, kQueryData, count);
    }
    
    public KOffsetUnfetchable offset(
        final int start
    ) {
        return KOffsetUnfetchable.getInstance(this.k, this.kQueryData, start);
    }
    
    private void process(
        final int count
    ) {
        this.kQueryData.sb.append(" LIMIT ").append(count);
    }
}
