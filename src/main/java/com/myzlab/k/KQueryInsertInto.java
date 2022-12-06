package com.myzlab.k;

public abstract class KQueryInsertInto {
    
    protected KInitializer k;
    protected KQueryInsertIntoData kQueryInsertIntoData;

    protected KQueryInsertInto() {
        this.kQueryInsertIntoData = new KQueryInsertIntoData();
    }
    
    protected KQueryInsertInto(
        final KInitializer kInitializer
    ) {
        this.kQueryInsertIntoData = new KQueryInsertIntoData();
        this.k = kInitializer;
    }
    
    public KQueryInsertInto(
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KInitializer kInitializer
    ) {
        this.kQueryInsertIntoData = kQueryInsertIntoData;
        this.k = kInitializer;
    }
    
    protected int executeSingle() {
        return KQueryUtils.executeSingle(this.k, this.kQueryInsertIntoData);
    }
    
    protected KCollection<KRow> executeMapping() {
        return this.executeMapping(KRow.class);
    }
    
    protected <T extends KRow> KCollection<T> executeMapping(
        final Class<T> clazz
    ) {
        return KQueryUtils.multipleMapping(this.k, this.kQueryInsertIntoData, clazz);
    }
    
}
