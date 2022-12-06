package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryGenericData {

    protected final List<KBaseColumn> kBaseColumns;
    protected final StringBuilder sb;
    protected final List<Object> params;
    
    public KQueryGenericData() {
        super();
        
        this.kBaseColumns = new ArrayList<>();
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
    }

    public KQueryGenericData(
        final List<KBaseColumn> kBaseColumns, 
        final StringBuilder sb,
        final List<Object> params
    ) {
        super();
        
        this.kBaseColumns = kBaseColumns;
        this.sb = sb;
        this.params = params;
    }
    
}
