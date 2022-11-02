package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryData {

    protected final StringBuilder sb;
    protected int columnsAdded;
    protected int tablesAdded;
    protected int conditionsAdded;
    protected final List<Object> params; 
    
    public KQueryData() {
        super();
        
        this.sb = new StringBuilder();
        this.columnsAdded = 0;
        this.tablesAdded = 0;
        this.conditionsAdded = 0;
        this.params = new ArrayList<>();
        
    }
}
