package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public class KValTextField extends KBaseValField implements Cloneable {
    
    protected KValTextField() {
        super();
    }
    
    protected KValTextField(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
        
        this.sb.append(sb);
        this.params.addAll(params);
    }
    
    protected KValTextField(
        final String val
    ) {
        super(val);
    }
    
    @Override
    protected KValTextField cloneMe() {
        try {
            return (KValTextField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KValTextField object");
        }
    }
}
