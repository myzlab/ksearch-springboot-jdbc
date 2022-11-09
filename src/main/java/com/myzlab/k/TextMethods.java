package com.myzlab.k;

public interface TextMethods {

    public KCondition ieq(
        final KColumn kColumn
    );
    
    public KCondition ieq(
        final KValTextField kValTextField
    );
    
    public KCondition ieq(
        final String string
    );
    
    public KCondition iEqual(
        final KColumn kColumn
    );
    
    public KCondition iEqual(
        final KValTextField kValTextField
    );
    
    public KCondition iEqual(
        final String string
    );
    
    public KCondition nieq(
        final KColumn kColumn
    );
    
    public KCondition nieq(
        final KValTextField kValTextField
    );
    
    public KCondition nieq(
        final String value
    );
    
    public KCondition notIEqual(
        final KColumn kColumn
    );
    
    public KCondition notIEqual(
        final KValTextField kValTextField
    );
    
    public KCondition notIEqual(
        final String value
    );
    
}
