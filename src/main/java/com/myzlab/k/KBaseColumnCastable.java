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
        final String string
    ) {
        return equal(string);
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
        final String string
    ) {
        return eq(KFunction.val(string));
    }
    
    protected KBaseColumnCastable cloneMe() {
        try {
            return (KBaseColumnCastable) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KBaseColumnCastable object");
        }
    }
}
