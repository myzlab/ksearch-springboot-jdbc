package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KValField<T> extends KField<T> implements Cloneable {
    
    protected boolean isNumber = false;
    protected boolean isText = false;
    
    public KValField(
        final StringBuilder sb,
        final boolean isNumber
    ) {
        super();
        
        this.isNumber = isNumber;
        this.isText = !isNumber;
        this.sb.append(sb);
    }
    
    public KValField(
        final String val,
        final boolean isNumber
    ) {
        super();
        
        this.isNumber = isNumber;
        this.isText = !isNumber;
        this.sb.append(val);
    }
    
    public KValField(
        final String val
    ) {
        this(val, false);
    }
    
    public KValField(
        final Number val
    ) {
        this(val.toString(), true);
    }
    
    @Override
    public KValField add(
        final Number number
    ) {
        return KFunction.add(this, new KValField(number));
    }
    
    public KValField add(
        final KValField kValField 
    ) {
        return KFunction.add(this, kValField);
    }
    
    @Override
    public KValField div(
        final Number number
    ) {
        return KFunction.div(this, new KValField(number));
    }
    
    public KValField div(
        final KValField kValField 
    ) {
        return KFunction.div(this, kValField);
    }
    
    @Override
    public KValField sub(
        final Number number
    ) {
        return KFunction.sub(this, new KValField(number));
    }
    
    public KValField sub(
        final KValField kValField 
    ) {
        return KFunction.sub(this, kValField);
    }
    
    @Override
    public KValField mod(
        final Number number
    ) {
        return KFunction.mod(this, new KValField(number));
    }
    
    public KValField mod(
        final KValField kValField
    ) {
        return KFunction.mod(this, kValField);
    }
    
    @Override
    public KValField mul(
        final Number number
    ) {
        return KFunction.mul(this, new KValField(number));
    }
    
    public KValField mul(
        final KValField kValField 
    ) {
        return KFunction.mul(this, kValField);
    }
    
    @Override
    protected KValField cloneMe() {
        try {
            return (KValField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KValField object");
        }
    }
    
}
