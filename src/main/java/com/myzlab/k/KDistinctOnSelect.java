package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;

public class KDistinctOnSelect {
    
    private final KExecutor k;
    private final KQueryData kQueryData;
    
    private KDistinctOnSelect(
        final KExecutor kExecutor
    ) {
        this.k = kExecutor;
        this.kQueryData = new KQueryData();
        this.kQueryData.distinctOn = true;
    }
    
    private KDistinctOnSelect(
        final KQueryData kQueryData,
        final KExecutor kExecutor
    ) {
        this.k = kExecutor;
        this.kQueryData = kQueryData;
        this.kQueryData.distinctOn = true;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final KColumn kColumn
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(kColumn);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final int n
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(n);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(new KColumn(new StringBuilder(kRaw.content), false));
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KColumn kColumn
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(kColumn);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final int n
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(n);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kExecutor);
        
        kDistinctOnSelect.processSelectDistinctOn(new KColumn(new StringBuilder(kRaw.content), false));
        
        return kDistinctOnSelect;
    }
    
    public KSelect select(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kColumnsAllowedToSelect);
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kQuery, alias);
    }
    
//    public KSelect select(
//        final KBaseColumn... kBaseColumns
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kBaseColumns);
//    }
//    
//    public KSelect select(
//        final KRaw... kRaws
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kRaws);
//    }
    
    private void processSelectDistinctOn(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        this.kQueryData.params.addAll(kColumn.params);
        this.kQueryData.sb.append(this.kQueryData.sb.length() > 0 ? " " : "").append("SELECT DISTINCT ON (").append(kColumn.sb).append(")");
    }
    
    private void processSelectDistinctOn(
        final int n
    ) {
        KUtils.assertNotNull(n, "n");
        
        this.kQueryData.sb.append("SELECT DISTINCT ON (").append(n).append(")");
    }
}
