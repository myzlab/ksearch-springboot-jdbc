package com.myzlab.k.optional;

import com.myzlab.k.KValNumberField;

public class KOptionalKValNumberField {

    private KValNumberField kValNumberField;
    
    private KOptionalKValNumberField() {
        super();
    }
    
    private KOptionalKValNumberField(
        final KValNumberField kValNumberField
    ) {
        super();
        
        this.kValNumberField = kValNumberField;
    }
    
    public static KOptionalKValNumberField getInstance(
        final KValNumberField kValNumberField
    ) {
        return new KOptionalKValNumberField(kValNumberField);
    }
    
    public KValNumberField get() {
        return kValNumberField;
    }
    
    public boolean isPresent() {
        return kValNumberField != null;
    }
}
