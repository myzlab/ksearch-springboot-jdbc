package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryUpdateData {
    
    protected List<KBaseColumn> kBaseColumns;
    protected final StringBuilder sb;
    protected final List<Object> params;
    protected int fromTablesAdded;
    protected int setValuesAdded;
    
    public KQueryUpdateData() {
        super();
        
        this.kBaseColumns = new ArrayList<>();
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
        this.fromTablesAdded = 0;
        this.setValuesAdded = 0;
    }

    public KQueryUpdateData(
        final StringBuilder sb,
        final List<Object> params,
        final int fromTablesAdded,
        final int setValuesAdded,
        final List<KBaseColumn> kBaseColumns
    ) {
        super();
        
        this.kBaseColumns = kBaseColumns;
        this.sb = sb;
        this.params = params;
        this.fromTablesAdded = fromTablesAdded;
    }
    
    protected KQueryUpdateData cloneMe() {
        return new KQueryUpdateData(new StringBuilder(this.sb), new ArrayList<>(this.params), this.fromTablesAdded, this.setValuesAdded, this.kBaseColumns);
    }
}
