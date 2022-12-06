package com.myzlab.k;

public class KLimit extends KQuery {
    
    private KLimit() {
        super();
    }
    
    private KLimit(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int count
    ) {
        super(kQueryData, kExecutor);
        
        this.process(count);
    }
    
    protected static KLimit getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int count
    ) {
        return new KLimit(kExecutor, kQueryData, count);
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
