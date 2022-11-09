package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public class KValTextField extends KBaseValField implements Cloneable, TextMethods {
    
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
    }
    
    protected KValTextField(
        final String val
    ) {
        super(val);
    }
    
    @Override
    public KCondition ieq(
        final KColumn kColumn
    ) {
        return iEqual(kColumn);
    }
    
    @Override
    public KCondition ieq(
        final KValTextField kValTextField
    ) {
        return iEqual(kValTextField);
    }
    
    @Override
    public KCondition ieq(
        final String string
    ) {
        return iEqual(string);
    }
    
    @Override
    public KCondition iEqual(
        final KColumn kColumn
    ) {
        return KCondition.ieq(this, kColumn);
    }
    
    @Override
    public KCondition iEqual(
        final KValTextField kValTextField
    ) {
        return KCondition.ieq(this, kValTextField);
    }
    
    @Override
    public KCondition iEqual(
        final String string
    ) {
        return iEqual(KFunction.val(string));
    }
    
    @Override
    public KCondition nieq(
        final KColumn kColumn
    ) {
        return notIEqual(kColumn);
    }
    
    @Override
    public KCondition nieq(
        final KValTextField kValTextField
    ) {
        return notIEqual(kValTextField);
    }
    
    @Override
    public KCondition nieq(
        final String value
    ) {
        return notIEqual(value);
    }
    
    @Override
    public KCondition notIEqual(
        final KColumn kColumn
    ) {
        return KCondition.nieq(this, kColumn);
    }
    
    @Override
    public KCondition notIEqual(
        final KValTextField kValTextField
    ) {
        return KCondition.nieq(this, kValTextField);
    }
    
    @Override
    public KCondition notIEqual(
        final String value
    ) {
        return nieq(KFunction.val(value));
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
