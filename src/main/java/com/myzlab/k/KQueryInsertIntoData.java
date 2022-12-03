package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryInsertIntoData {
    
    protected final List<KBaseColumn> kBaseColumns;
    protected final StringBuilder sb;
    protected final List<Object> params;
//    protected int usingTablesAdded;
    protected int setValuesAdded;
    
    public KQueryInsertIntoData() {
        super();
        
        this.kBaseColumns = new ArrayList<>();
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
//        this.usingTablesAdded = 0;
        this.setValuesAdded = 0;
    }

    public KQueryInsertIntoData(
        final StringBuilder sb,
        final List<Object> params,
        final int setValuesAdded,
        final List<KBaseColumn> kBaseColumns
    ) {
        super();
        
        this.kBaseColumns = kBaseColumns;
        this.sb = sb;
        this.params = params;
//        this.usingTablesAdded = usingTablesAdded;
        this.setValuesAdded = setValuesAdded;
    }
    
    protected KQueryInsertIntoData cloneMe() {
        return new KQueryInsertIntoData(new StringBuilder(this.sb), new ArrayList<>(this.params), this.setValuesAdded, this.kBaseColumns);
    }
}
