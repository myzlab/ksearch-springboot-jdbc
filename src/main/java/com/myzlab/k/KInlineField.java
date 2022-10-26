package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KInlineField<T> extends KField<T> {
    
    private boolean isNumber = false;
    
    public KInlineField(
        final String inline,
        final boolean isNumber
    ) {
        super();
        
        this.isNumber = isNumber;
        this.sb.append(inline);
    }
    
    public KInlineField(
        final String inline
    ) {
        this(inline, false);
    }
    
    public KInlineField(
        final Number inline
    ) {
        this(inline.toString(), true);
    }
    
    public KInlineField divide(
        final Number number
    ) {
        return this.divide(new KInlineField(number));
    }
    
    public KInlineField divide(
        final KInlineField kInlineField 
    ) {
        return this.doOperation("/", kInlineField);
    }
    
    public KInlineField minus(
        final Number number
    ) {
        return this.minus(new KInlineField(number));
    }
    
    public KInlineField minus(
        final KInlineField kInlineField 
    ) {
        return this.doOperation("-", kInlineField);
    }
    
    public KInlineField multiply(
        final Number number
    ) {
        return this.multiply(new KInlineField(number));
    }
    
    public KInlineField multiply(
        final KInlineField kInlineField 
    ) {
        return this.doOperation("*", kInlineField);
    }
    
    public KInlineField plus(
        final Number number
    ) {
        return this.plus(new KInlineField(number));
    }
    
    public KInlineField plus(
        final KInlineField kInlineField 
    ) {
        return this.doOperation("+", kInlineField);
    }
    
    private KInlineField doOperation(
        final String operation,
        final KInlineField kInlineField 
    ) {
        if (!this.isNumber) {
            throw KExceptionHelper.internalServerError("The 'plus' method only can be used in 'inline' of number type. Current value: [" + this.sb.toString() + "]");
        }
        
        if (!kInlineField.isNumber) {
            throw KExceptionHelper.internalServerError("The 'plus' method only can be used in 'inline' of number type. Current value: [" + kInlineField.sb.toString() + "]");
        }
        
        return new KInlineField(this.sb.append(" ").append(operation).append(" ").append(kInlineField.sb).toString(), true);
    }
}
