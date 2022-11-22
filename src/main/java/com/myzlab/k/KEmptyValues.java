package com.myzlab.k;

import java.util.List;

public class KEmptyValues {

    private KEmptyValues() {
        super();
    }
    
    public static KEmptyValues getInstance() {
        return new KEmptyValues();
    }
    
    public KValues append(
        final List<Object> value
    ) {
        return KValues.getInstance(value);
    }
}
