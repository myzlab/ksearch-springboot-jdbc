package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryData {

    protected List<KBaseColumn> kBaseColumns;
    protected final StringBuilder sb;
    protected int columnsAdded;
    protected int tablesAdded;
    protected final List<Object> params;
    protected boolean distinctOn;
    
    public KQueryData() {
        super();
        
        this.kBaseColumns = new ArrayList<>();
        this.sb = new StringBuilder();
        this.columnsAdded = 0;
        this.tablesAdded = 0;
        this.params = new ArrayList<>();
        this.distinctOn = false;
    }

    public KQueryData(
        final List<KBaseColumn> kBaseColumns, 
        final StringBuilder sb,
        final int columnsAdded,
        final int tablesAdded,
        final List<Object> params,
        final boolean distinctOn
    ) {
        super();
        
        this.kBaseColumns = kBaseColumns;
        this.sb = sb;
        this.columnsAdded = columnsAdded;
        this.tablesAdded = tablesAdded;
        this.params = params;
        this.distinctOn = distinctOn;
    }
    
    protected KQueryData cloneMe() {
        return new KQueryData(new ArrayList<>(this.kBaseColumns), new StringBuilder(this.sb), this.columnsAdded, this.tablesAdded, new ArrayList<>(this.params), this.distinctOn);
    }
}
