package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public abstract class KBaseColumnCastable extends KBaseColumn implements Cloneable {
    
    protected KBaseColumnCastable() {
        super();
    }
    
    protected KBaseColumnCastable(
        final boolean closed
    ) {
        super(closed);
    }
    
    protected KBaseColumnCastable(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KBaseColumnCastable(
        final StringBuilder sb
    ) {
        super(sb);
    }
    
    protected KBaseColumnCastable(
        final String val
    ) {
        super(val);
    }
    
    protected KBaseColumnCastable(
        final Number val
    ) {
        super(val);
    }
    
    public KAliasedColumn as(
        final String alias
    ) {
        return KFunction.as(this, alias);
    }
    
    public KColumn cast(
        final KDataType kDataType    
    ) {
        return KFunction.cast(this, kDataType);
    }
    
    public KCondition eq(
        final KColumn kColumn
    ) {
        return equal(kColumn);
    }
    
    public KCondition eq(
        final KValNumberField kValNumberField
    ) {
        return equal(kValNumberField);
    }
    
    public KCondition eq(
        final KValTextField kValTextField
    ) {
        return equal(kValTextField);
    }
    
    public KCondition eq(
        final Number number
    ) {
        return equal(number);
    }
    
    public KCondition eq(
        final String value
    ) {
        return equal(value);
    }
    
    public KCondition equal(
        final KColumn kColumn
    ) {
        return KCondition.eq(this, kColumn);
    }
    
    public KCondition equal(
        final KValNumberField kValNumberField
    ) {
        return KCondition.eq(this, kValNumberField);
    }
    
    public KCondition equal(
        final KValTextField kValTextField
    ) {
        return KCondition.eq(this, kValTextField);
    }
    
    public KCondition equal(
        final Number number
    ) {
        return eq(KFunction.val(number));
    }
    
    public KCondition equal(
        final String value
    ) {
        return eq(KFunction.val(value));
    }
    
    public KCondition neq(
        final KColumn kColumn
    ) {
        return notEqual(kColumn);
    }
    
    public KCondition neq(
        final KValTextField kValTextField
    ) {
        return notEqual(kValTextField);
    }
    
    public KCondition neq(
        final KValNumberField kValNumberField
    ) {
        return notEqual(kValNumberField);
    }
    
    public KCondition neq(
        final Number number
    ) {
        return notEqual(number);
    }
    
    public KCondition neq(
        final String value
    ) {
        return notEqual(value);
    }
    
    public KCondition notEqual(
        final KColumn kColumn
    ) {
        return KCondition.neq(this, kColumn);
    }
    
    public KCondition notEqual(
        final KValTextField kValTextField
    ) {
        return KCondition.neq(this, kValTextField);
    }
    
    public KCondition notEqual(
        final KValNumberField kValNumberField
    ) {
        return KCondition.neq(this, kValNumberField);
    }
    
    public KCondition notEqual(
        final Number number
    ) {
        return neq(KFunction.val(number));
    }
    
    public KCondition notEqual(
        final String value
    ) {
        return neq(KFunction.val(value));
    }
    
    @Override
    protected KBaseColumnCastable cloneMe() {
        try {
            return (KBaseColumnCastable) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KBaseColumnCastable object");
        }
    }
}
