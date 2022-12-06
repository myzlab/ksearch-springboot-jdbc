package com.myzlab.k;

public abstract class KQueryDelete {
    
    protected KInitializer k;
    protected KQueryDeleteData kQueryDeleteData;

    protected KQueryDelete() {
        this.kQueryDeleteData = new KQueryDeleteData();
    }
    
    protected KQueryDelete(
        final KInitializer kInitializer
    ) {
        this.kQueryDeleteData = new KQueryDeleteData();
        this.k = kInitializer;
    }
    
    public KQueryDelete(
        final KQueryDeleteData kQueryDeleteData,
        final KInitializer kInitializer
    ) {
        this.kQueryDeleteData = kQueryDeleteData;
        this.k = kInitializer;
    }
    
    protected int executeSingle() {
        return KQueryUtils.executeSingle(this.k, this.kQueryDeleteData);
    }
    
    protected KCollection<KRow> executeMapping() {
        return this.executeMapping(KRow.class);
    }
    
    protected <T extends KRow> KCollection<T> executeMapping(
        final Class<T> clazz
    ) {
        return KQueryUtils.multipleMapping(this.k, this.kQueryDeleteData, clazz);
    }
    
}
