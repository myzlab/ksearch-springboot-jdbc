package com.myzlab.k;

import java.util.Map;

public abstract class KRow {
    
    public Object[] o;
    public Map<String, Integer> ref;
    protected boolean isNull = false;
    
    public KRow() {}

    public KRow(
        final Object[] o,
        final Map<String, Integer> ref
    ) {
        this.o = o;
        this.ref = ref;
    }
    
    public static KRow getGenericInstance(
        final Object[] o,
        final Map<String, Integer> ref
    ) {
        return new KRow(o, ref) {
            
            @Override
            public String[] getWay(String target) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
    
    public Object get(final String c) {
        final Integer n = ref.get(c);
        
        if (n == null) {
            return null;
        }
        
        return this.get(n);
    }
    
    public Object get(final int n) {
        if (n >= o.length) {
            return null;
        }
        
        return o[n];
    }
    
    public boolean isNull() {
        return isNull;
    }
    
    public abstract String[] getWay(
        final String target
    );
}
