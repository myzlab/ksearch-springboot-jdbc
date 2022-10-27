package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KValField<T> extends KField<T> implements Cloneable {
    
    protected boolean isNumber = false;
    
    public KValField(
        final StringBuilder sb,
        final boolean isNumber
    ) {
        super();
        
        this.isNumber = isNumber;
        this.sb.append(sb);
    }
    
    public KValField(
        final String val,
        final boolean isNumber
    ) {
        super();
        
        this.isNumber = isNumber;
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
    public KValField div(
        final Number number
    ) {
        return this.div(new KValField(number));
    }
    
    public KValField div(
        final KValField kValField 
    ) {
        return this.doOperation("/", kValField);
    }
    
    @Override
    public KValField sub(
        final Number number
    ) {
        return this.sub(new KValField(number));
    }
    
    public KValField sub(
        final KValField kValField 
    ) {
        return this.doOperation("-", kValField);
    }
    
    @Override
    public KValField mod(
        final Number number
    ) {
        return this.mod(new KValField(number));
    }
    
    public KValField mod(
        final KValField kValField
    ) {
        return this.doOperation("%", kValField);
    }
    
    @Override
    public KValField mul(
        final Number number
    ) {
        return this.mul(new KValField(number));
    }
    
    public KValField mul(
        final KValField kValField 
    ) {
        return this.doOperation("*", kValField);
    }
    
    @Override
    public KValField add(
        final Number number
    ) {
        return this.add(new KValField(number));
    }
    
    public KValField add(
        final KValField kValField 
    ) {
        return this.doOperation("+", kValField);
    }
    
    private KValField doOperation(
        final String operation,
        final KValField kValField 
    ) {
        if (!this.isNumber) {
            throw KExceptionHelper.internalServerError("The '" + operation + "' method only can be used in 'val' of number type. Current value: [" + this.sb.toString() + "]");
        }
        
        if (!kValField.isNumber) {
            throw KExceptionHelper.internalServerError("The '" + operation + "' method only can be used in 'val' of number type. Current value: [" + kValField.sb.toString() + "]");
        }
        
        final KValField newKValField = new KValField(this.sb, true);
        
        if (!isCasteableToANumber(newKValField.sb.toString())) {
            newKValField.sb.insert(0, "(").append(")");
        }
        
        newKValField.sb.append(" ").append(operation).append(" ");
        
        final boolean kValFieldCasteableToANumber = isCasteableToANumber(kValField.sb.toString());
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append("(");
        }
        
        newKValField.sb.append(kValField.sb);
        
        if (!kValFieldCasteableToANumber) {
            newKValField.sb.append(")");
        }
        
        return newKValField;
    }
    
    @Override
    protected KValField cloneMe() {
        try {
            return (KValField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KValField object");
        }
    }
    
    private boolean isCasteableToANumber(
        final String text
    ) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
