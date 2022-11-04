package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
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
    
    protected KBaseValField cloneMe() {
        try {
            return (KBaseValField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KBaseValField object");
        }
    }
}
