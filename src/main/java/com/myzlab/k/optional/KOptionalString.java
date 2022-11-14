package com.myzlab.k.optional;

public class KOptionalString {

    private String value;
    
    private KOptionalString() {
        super();
    }
    
    private KOptionalString(
        final String value
    ) {
        super();
        
        this.value = value;
    }
    
    public static KOptionalString getInstance(
        final String value
    ) {
        return new KOptionalString(value);
    }
    
    public String get() {
        return value;
    }
    
    public boolean isPresent() {
        return value != null;
    }
}
