package com.myzlab.k;

public class KInitializer {
    
    public KWith with() {
        return new KWith();
    }

    public KSelect select() {
        return new KSelect();
    }
    
    public KSelect select(final KBaseField... kBaseColumns) {
        return new KSelect(kBaseColumns);
    }
    
    public void insertInto() {
        
    }
    
    public void update() {
        
    }
    
    public void delete() {
        
    }
}
