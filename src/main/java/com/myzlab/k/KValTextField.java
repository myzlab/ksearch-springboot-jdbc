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
        return KCondition.ieq(this, kColumn);
    }
    
    @Override
    public KCondition ieq(
        final KValTextField kValTextField
    ) {
        return KCondition.ieq(this, kValTextField);
    }
    
    @Override
    public KCondition ieq(
        final String value
    ) {
        return KCondition.ieq(this, KFunction.val(value));
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
        final String value
    ) {
        return KCondition.ieq(this, KFunction.val(value));
    }
    
    @Override
    public KCondition igt(
        final KColumn kColumn
    ) {
        return KCondition.igt(this, kColumn);
    }
    
    @Override
    public KCondition igt(
        final KValTextField kValTextField
    ) {
        return KCondition.igt(this, kValTextField);
    }
    
    @Override
    public KCondition igt(
        final String value
    ) {
        return KCondition.igt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition igte(
        final KColumn kColumn
    ) {
        return KCondition.igte(this, kColumn);
    }
    
    @Override
    public KCondition igte(
        final KValTextField kValTextField
    ) {
        return KCondition.igte(this, kValTextField);
    }
    
    @Override
    public KCondition igte(
        final String value
    ) {
        return KCondition.igte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iGreaterThan(
        final KColumn kColumn
    ) {
        return KCondition.igt(this, kColumn);
    }
    
    @Override
    public KCondition iGreaterThan(
        final KValTextField kValTextField
    ) {
        return KCondition.igt(this, kValTextField);
    }
    
    @Override
    public KCondition iGreaterThan(
        final String value
    ) {
        return KCondition.igt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iGreaterThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.igte(this, kColumn);
    }
    
    @Override
    public KCondition iGreaterThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.igte(this, kValTextField);
    }
    
    @Override
    public KCondition iGreaterThanOrEqualTo(
        final String value
    ) {
        return KCondition.igte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nieq(
        final KColumn kColumn
    ) {
        return KCondition.nieq(this, kColumn);
    }
    
    @Override
    public KCondition nieq(
        final KValTextField kValTextField
    ) {
        return KCondition.nieq(this, kValTextField);
    }
    
    @Override
    public KCondition nieq(
        final String value
    ) {
        return KCondition.nieq(this, KFunction.val(value));
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
        return KCondition.nieq(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nigt(
        final KColumn kColumn
    ) {
        return KCondition.nigt(this, kColumn);
    }
    
    @Override
    public KCondition nigt(
        final KValTextField kValTextField
    ) {
        return KCondition.nigt(this, kValTextField);
    }
    
    @Override
    public KCondition nigt(
        final String value
    ) {
        return KCondition.nigt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nigte(
        final KColumn kColumn
    ) {
        return KCondition.nigte(this, kColumn);
    }
    
    @Override
    public KCondition nigte(
        final KValTextField kValTextField
    ) {
        return KCondition.nigte(this, kValTextField);
    }
    
    @Override
    public KCondition nigte(
        final String value
    ) {
        return KCondition.nigte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notIGreaterThan(
        final KColumn kColumn
    ) {
        return KCondition.nigt(this, kColumn);
    }
    
    @Override
    public KCondition notIGreaterThan(
        final KValTextField kValTextField
    ) {
        return KCondition.nigt(this, kValTextField);
    }
    
    @Override
    public KCondition notIGreaterThan(
        final String value
    ) {
        return KCondition.nigt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notIGreaterThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.nigte(this, kColumn);
    }
    
    @Override
    public KCondition notIGreaterThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.nigte(this, kValTextField);
    }
    
    @Override
    public KCondition notIGreaterThanOrEqualTo(
        final String value
    ) {
        return KCondition.nigte(this, KFunction.val(value));
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
