package com.myzlab.k;

import java.util.List;

public abstract class KBaseValField extends KBaseColumnCastable implements Cloneable {
    
    protected KBaseValField() {
        super();
    }
    
    protected KBaseValField(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KBaseValField(
        final String val
    ) {
        super(val);
    }
    
    protected KBaseValField(
        final Number val
    ) {
        super(val);
    }
}
