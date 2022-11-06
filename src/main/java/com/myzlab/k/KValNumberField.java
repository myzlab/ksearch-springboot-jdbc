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
    
    public KColumn add(
        final KColumn kColumn
    ) {
        return KFunction.add(this, kColumn);
    }
    
    public KValNumberField add(
        final KValNumberField kValNumberField
    ) {
        return KFunction.add(this, kValNumberField);
    }
    
    public KValNumberField add(
        final Number number
    ) {
        return KFunction.add(this, number);
    }
    
    public KColumn div(
        final KColumn kColumn
    ) {
        return KFunction.div(this, kColumn);
    }
    
    public KValNumberField div(
        final KValNumberField kValNumberField
    ) {
        return KFunction.div(this, kValNumberField);
    }
    
    public KValNumberField div(
        final Number number
    ) {
        return KFunction.div(this, number);
    }
    
    public KColumn sub(
        final KColumn kColumn
    ) {
        return KFunction.sub(this, kColumn);
    }
    
    public KValNumberField sub(
        final KValNumberField kValNumberField
    ) {
        return KFunction.sub(this, kValNumberField);
    }
    
    public KValNumberField sub(
        final Number number
    ) {
        return KFunction.sub(this, new KValNumberField(number));
    }
    
    public KColumn mod(
        final KColumn kColumn
    ) {
        return KFunction.mod(this, kColumn);
    }
    
    public KValNumberField mod(
        final KValNumberField kValNumberField
    ) {
        return KFunction.mod(this, kValNumberField);
    }
    
    public KValNumberField mod(
        final Number number
    ) {
        return KFunction.mod(this, new KValNumberField(number));
    }
    
    public KColumn mul(
        final KColumn kColumn
    ) {
        return KFunction.mul(this, kColumn);
    }
    
    public KValNumberField mul(
        final KValNumberField kValNumberField
    ) {
        return KFunction.mul(this, kValNumberField);
    }
    
    public KValNumberField mul(
        final Number number
    ) {
        return KFunction.mul(this, new KValNumberField(number));
    }
    
    protected KValNumberField cloneMe() {
        try {
            return (KValNumberField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KValNumberField object");
        }
    }
}
