package com.myzlab.k.optional;

import com.myzlab.k.KColumn;

public class KOptionalKColumn {

    private KColumn kColumn;
    
    private KOptionalKColumn() {
        super();
    }
    
    private KOptionalKColumn(
        final KColumn kColumn
    ) {
        super();
        
        this.kColumn = kColumn;
    }
    
    public static KOptionalKColumn getInstance(
        final KColumn kColumn
    ) {
        return new KOptionalKColumn(kColumn);
    }
    
    public KColumn get() {
        return kColumn;
    }
    
    public boolean isPresent() {
        return kColumn != null;
    }
}
