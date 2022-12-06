package com.myzlab.k;

public class KFetch extends KQuery {

    private KFetch() {
        super();
    }
    
    private KFetch(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int rowCount
    ) {
        super(kQueryData, kExecutor);
        
        this.process(rowCount);
    }
    
    public static KFetch getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int rowCount
    ) {
        return new KFetch(kExecutor, kQueryData, rowCount);
    }
    
    private void process(
        final int rowCount
    ) {
        
        this.kQueryData.sb.append(" FETCH FIRST ");
        
        if (rowCount > 1) {
            this.kQueryData.sb.append(rowCount).append(" ");
        }
        
        this.kQueryData.sb.append("ROW");
        
        if (rowCount > 1) {
            this.kQueryData.sb.append("S");
        }
        
        this.kQueryData.sb.append(" ONLY");
    }
}
