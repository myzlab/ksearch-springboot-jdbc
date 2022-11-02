package com.myzlab.k;

public class KInitializer {
    
    public KWith with() {
        return new KWith();
    }
    
    public KSelect select(final KBaseColumn... kBaseColumns) {
        return KSelect.getInstance(kBaseColumns);
    }
    
    public void insertInto() {
        
    }
    
    public void update() {
        
    }
    
    public void delete() {
        
    }
}
