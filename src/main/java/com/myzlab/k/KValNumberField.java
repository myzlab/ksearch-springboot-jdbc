package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public class KValNumberField extends KBaseValField implements Cloneable {
    
    protected KValNumberField() {
        super();
    }
    
    protected KValNumberField(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KValNumberField(
        final Number val
    ) {
        super(val);
    }
    
    public KValNumberField add(
        final Number number
    ) {
        return KFunction.add(this, new KValNumberField(number));
    }
    
    public KValNumberField add(
        final KValNumberField kValField 
    ) {
        return KFunction.add(this, kValField);
    }
    
    public KValNumberField div(
        final Number number
    ) {
        return KFunction.div(this, new KValNumberField(number));
    }
    
    public KValNumberField div(
        final KValNumberField kValField 
    ) {
        return KFunction.div(this, kValField);
    }
    
    public KValNumberField sub(
        final Number number
    ) {
        return KFunction.sub(this, new KValNumberField(number));
    }
    
    public KValNumberField sub(
        final KValNumberField kValField 
    ) {
        return KFunction.sub(this, kValField);
    }
    
    public KValNumberField mod(
        final Number number
    ) {
        return KFunction.mod(this, new KValNumberField(number));
    }
    
    public KValNumberField mod(
        final KValNumberField kValField
    ) {
        return KFunction.mod(this, kValField);
    }
    
    public KValNumberField mul(
        final Number number
    ) {
        return KFunction.mul(this, new KValNumberField(number));
    }
    
    public KValNumberField mul(
        final KValNumberField kValField 
    ) {
        return KFunction.mul(this, kValField);
    }
    
    protected KValNumberField cloneMe() {
        try {
            return (KValNumberField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KValNumberField object");
        }
    }
}
