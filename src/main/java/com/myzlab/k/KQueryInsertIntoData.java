package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KQueryInsertIntoData extends KQueryGenericData {
    
    protected int setValuesAdded;
    
    public KQueryInsertIntoData() {
        super();
        
        this.setValuesAdded = 0;
    }

    public KQueryInsertIntoData(
        final StringBuilder sb,
        final List<Object> params,
        final int setValuesAdded,
        final List<KBaseColumn> kBaseColumns
    ) {
        super(kBaseColumns, sb, params);
        
        this.setValuesAdded = setValuesAdded;
    }
    
    protected KQueryInsertIntoData cloneMe() {
        return new KQueryInsertIntoData(new StringBuilder(this.sb), new ArrayList<>(this.params), this.setValuesAdded, this.kBaseColumns);
    }
}
