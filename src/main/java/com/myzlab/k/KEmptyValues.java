package com.myzlab.k;

import java.util.ArrayList;
import java.util.List;
import com.myzlab.k.functions.KValuesFunction;

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
    
    public KValues append(
        final Object... value
    ) {
        KUtils.assertNotNullNotEmpty(value, "value", false);
        
        final List<Object> v = new ArrayList<>();
        
        for (final Object o : value) {
            v.add(o);
        }
        
        return KValues.getInstance(v);
    }
    
    public KValues append(
        final List<Object> list,
        final KValuesFunction kAppendValuesFunction
    ) {
        KUtils.assertNotNullNotEmpty(list, "list", false);
        
        final List<List<Object>> values = new ArrayList<>();
                
        for (final Object o : list) {
            values.add(kAppendValuesFunction.run(o));
        }
        
        final KValues kValues = KValues.getInstance(values.get(0));
        
        for (int i = 1; i < values.size(); i++) {
            kValues.append(values.get(i));
        }
        
        return kValues;
    }
}