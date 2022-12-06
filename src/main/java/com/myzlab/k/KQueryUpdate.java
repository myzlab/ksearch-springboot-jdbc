package com.myzlab.k;

public abstract class KQueryUpdate {
    
    protected KInitializer k;
    protected KQueryUpdateData kQueryUpdateData;

    protected KQueryUpdate() {
        this.kQueryUpdateData = new KQueryUpdateData();
    }
    
    protected KQueryUpdate(
        final KInitializer kInitializer
    ) {
        this.kQueryUpdateData = new KQueryUpdateData();
        this.k = kInitializer;
    }
    
    public KQueryUpdate(
        final KQueryUpdateData kQueryUpdateData,
        final KInitializer kInitializer
    ) {
        this.kQueryUpdateData = kQueryUpdateData;
        this.k = kInitializer;
    }
    
    protected int executeSingle() {
        return KQueryUtils.executeSingle(this.k, this.kQueryUpdateData);
    }
    
    protected KCollection<KRow> executeMapping() {
        return this.executeMapping(KRow.class);
    }
    
    protected <T extends KRow> KCollection<T> executeMapping(
        final Class<T> clazz
    ) {
        return KQueryUtils.multipleMapping(this.k, this.kQueryUpdateData, clazz);
    }
    
}
