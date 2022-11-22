package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;

public class KValues {

    protected final List<List<Object>> values;

    private KValues(
        final List<Object> value
    ) {
        super();
        
        KUtils.assertNotNullNotEmpty(value, "value");
        
        this.values = new ArrayList<>();
        this.values.add(value);
    }
    
    public static KValues getInstance(
        final List<Object> value
    ) {
        return new KValues(value);
    }
    
    public KValues append(
        final List<Object> value
    ) {
        KUtils.assertNotNullNotEmpty(value, "value");
        
        this.values.add(value);
        
        return this;
    }
}
