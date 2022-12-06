package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryDeleteData extends KQueryGenericData {
    
    protected int usingTablesAdded;
    
    public KQueryDeleteData() {
        super();
        
        this.usingTablesAdded = 0;
    }

    public KQueryDeleteData(
        final StringBuilder sb,
        final List<Object> params,
        final int usingTablesAdded,
        final List<KBaseColumn> kBaseColumns
    ) {
        super(kBaseColumns, sb, params);
        
        this.usingTablesAdded = usingTablesAdded;
    }
    
    protected KQueryDeleteData cloneMe() {
        return new KQueryDeleteData(new StringBuilder(this.sb), new ArrayList<>(this.params), this.usingTablesAdded, this.kBaseColumns);
    }
}
