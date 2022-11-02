package com.myzlab.k;

public abstract class KQuery {
    
    protected KQueryData kQueryData;

    public KQuery() {
        this.kQueryData = new KQueryData();
    }
    
    public KQuery(final KQueryData kQueryData) {
        this.kQueryData = kQueryData;
    }
    
    public void single() {
        System.out.println(kQueryData.sb.toString());
        System.out.println(kQueryData.params);
    }
    
}
