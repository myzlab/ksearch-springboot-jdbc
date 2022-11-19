package com.myzlab.k;

import java.util.List;

public abstract class KBaseValField extends KBaseColumnCastable {
    
    protected KBaseValField() {
        super();
    }
    
    protected KBaseValField(
        final StringBuilder sb,
        final List<Object> params,
        final boolean closed
    ) {
        super(sb, params, closed);
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
