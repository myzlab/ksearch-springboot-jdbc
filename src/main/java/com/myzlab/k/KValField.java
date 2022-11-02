package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public class KValField<T> extends KColumn<T> implements Cloneable {
    
    protected final StringBuilder sbParam;
    
    protected final boolean isNumber;
    protected final boolean isText;
    protected final List<Object> params;
    
    private KValField(
        final boolean isNumber,
        final boolean isText
    ) {
        super();
        
        this.isNumber = isNumber;
        this.isText = isText;
        this.params = new ArrayList<>();
        this.sbParam = new StringBuilder();
    }
    
    public KValField(
        final StringBuilder sb,
        final StringBuilder sbParam,
        final List<Object> params,
        final boolean isNumber
    ) {
        this(isNumber, !isNumber);
        
        this.sb.append(sb);
        this.sbParam.append(sbParam);
        this.params.addAll(params);
    }
    
    public KValField(
        final String val
    ) {
        this(false, true);
        
        this.sb.append(val);
        this.sbParam.append("?");
        this.params.add(val);
    }
    
    public KValField(
        final Number val
    ) {
        this(true, false);
        
        this.sb.append(val);
        this.sbParam.append("?");
        this.params.add(val);
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
