package com.myzlab.k;

import java.util.List;

public abstract class KBaseColumnUncastable extends KBaseColumn implements Cloneable {
    
    protected KBaseColumnUncastable() {
        super();
    }
    
    protected KBaseColumnUncastable(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KBaseColumnUncastable(
        final String val
    ) {
        super(val);
    }
    
    protected KBaseColumnUncastable(
        final Number val
    ) {
        super(val);
    }
}
