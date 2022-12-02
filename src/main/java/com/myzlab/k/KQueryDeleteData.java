package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryDeleteData {
    
    protected final List<KBaseColumn> kBaseColumns;
    protected final StringBuilder sb;
    protected final List<Object> params;
    protected int usingTablesAdded;
    
    public KQueryDeleteData() {
        super();
        
        this.kBaseColumns = new ArrayList<>();
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
        this.usingTablesAdded = 0;
    }

    public KQueryDeleteData(
        final StringBuilder sb,
        final List<Object> params,
        final int usingTablesAdded,
        final List<KBaseColumn> kBaseColumns
    ) {
        super();
        
        this.kBaseColumns = kBaseColumns;
        this.sb = sb;
        this.params = params;
        this.usingTablesAdded = usingTablesAdded;
    }
    
    protected KQueryDeleteData cloneMe() {
        return new KQueryDeleteData(new StringBuilder(this.sb), new ArrayList<>(this.params), this.usingTablesAdded, this.kBaseColumns);
    }
}
