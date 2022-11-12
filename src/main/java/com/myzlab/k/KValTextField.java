package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
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
    public KCondition iBetween(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.ibt(this, kColumnLow, kColumnHigh);
    }
    
    @Override
    public KCondition iBetween(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.ibt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    @Override
    public KCondition iBetween(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.ibt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
    }
    
    @Override
    public KCondition ibt(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.ibt(this, kColumnLow, kColumnHigh);
    }
    
    @Override
    public KCondition ibt(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.ibt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    @Override
    public KCondition ibt(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.ibt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
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
    public KCondition iLessThan(
        final KColumn kColumn
    ) {
        return KCondition.ilt(this, kColumn);
    }
    
    @Override
    public KCondition iLessThan(
        final KValTextField kValTextField
    ) {
        return KCondition.ilt(this, kValTextField);
    }
    
    @Override
    public KCondition iLessThan(
        final String value
    ) {
        return KCondition.ilt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLessThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.ilte(this, kColumn);
    }
    
    @Override
    public KCondition iLessThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.ilte(this, kValTextField);
    }
    
    @Override
    public KCondition iLessThanOrEqualTo(
        final String value
    ) {
        return KCondition.ilte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLike(
        final KColumn kColumn
    ) {
        return KCondition.ilk(this, kColumn);
    }
    
    @Override
    public KCondition iLike(
        final KValTextField kValTextField
    ) {
        return KCondition.ilk(this, kValTextField);
    }
    
    @Override
    public KCondition iLike(
        final String value
    ) {
        return KCondition.ilk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLikeAny(
        final KColumn kColumn
    ) {
        return KCondition.ilka(this, kColumn);
    }
    
    @Override
    public KCondition iLikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.ilka(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeAny(
        final String value
    ) {
        return KCondition.ilka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLikeEndWith(
        final KColumn kColumn
    ) {
        return KCondition.iLikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition iLikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.iLikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeEndWith(
        final String value
    ) {
        return KCondition.iLikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.iLikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition iLikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.iLikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeStartWith(
        final String value
    ) {
        return KCondition.iLikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilk(
        final KColumn kColumn
    ) {
        return KCondition.ilk(this, kColumn);
    }
    
    @Override
    public KCondition ilk(
        final KValTextField kValTextField
    ) {
        return KCondition.ilk(this, kValTextField);
    }
    
    @Override
    public KCondition ilk(
        final String value
    ) {
        return KCondition.ilk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilka(
        final KColumn kColumn
    ) {
        return KCondition.ilka(this, kColumn);
    }
    
    @Override
    public KCondition ilka(
        final KValTextField kValTextField
    ) {
        return KCondition.ilka(this, kValTextField);
    }
    
    @Override
    public KCondition ilka(
        final String value
    ) {
        return KCondition.ilka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilkew(
        final KColumn kColumn
    ) {
        return KCondition.iLikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition ilkew(
        final KValTextField kValTextField
    ) {
        return KCondition.iLikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition ilkew(
        final String value
    ) {
        return KCondition.iLikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilksw(
        final KColumn kColumn
    ) {
        return KCondition.iLikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition ilksw(
        final KValTextField kValTextField
    ) {
        return KCondition.iLikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition ilksw(
        final String value
    ) {
        return KCondition.iLikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilt(
        final KColumn kColumn
    ) {
        return KCondition.ilt(this, kColumn);
    }
    
    @Override
    public KCondition ilt(
        final KValTextField kValTextField
    ) {
        return KCondition.ilt(this, kValTextField);
    }
    
    @Override
    public KCondition ilt(
        final String value
    ) {
        return KCondition.ilt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilte(
        final KColumn kColumn
    ) {
        return KCondition.ilte(this, kColumn);
    }
    
    @Override
    public KCondition ilte(
        final KValTextField kValTextField
    ) {
        return KCondition.ilte(this, kValTextField);
    }
    
    @Override
    public KCondition ilte(
        final String value
    ) {
        return KCondition.ilte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition like(
        final KColumn kColumn
    ) {
        return KCondition.lk(this, kColumn);
    }
    
    @Override
    public KCondition like(
        final KValTextField kValTextField
    ) {
        return KCondition.lk(this, kValTextField);
    }
    
    @Override
    public KCondition like(
        final String value
    ) {
        return KCondition.lk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition likeAny(
        final KColumn kColumn
    ) {
        return KCondition.lka(this, kColumn);
    }
    
    @Override
    public KCondition likeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.lka(this, kValTextField);
    }
    
    @Override
    public KCondition likeAny(
        final String value
    ) {
        return KCondition.lka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition likeEndWith(
        final KColumn kColumn
    ) {
        return KCondition.likeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition likeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.likeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition likeEndWith(
        final String value
    ) {
        return KCondition.likeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition likeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.likeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition likeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.likeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition likeStartWith(
        final String value
    ) {
        return KCondition.likeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition lk(
        final KColumn kColumn
    ) {
        return KCondition.lk(this, kColumn);
    }
    
    @Override
    public KCondition lk(
        final KValTextField kValTextField
    ) {
        return KCondition.lk(this, kValTextField);
    }
    
    @Override
    public KCondition lk(
        final String value
    ) {
        return KCondition.lk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition lka(
        final KColumn kColumn
    ) {
        return KCondition.lka(this, kColumn);
    }
    
    @Override
    public KCondition lka(
        final KValTextField kValTextField
    ) {
        return KCondition.lka(this, kValTextField);
    }
    
    @Override
    public KCondition lka(
        final String value
    ) {
        return KCondition.lka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition lkew(
        final KColumn kColumn
    ) {
        return KCondition.likeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition lkew(
        final KValTextField kValTextField
    ) {
        return KCondition.likeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition lkew(
        final String value
    ) {
        return KCondition.likeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition lksw(
        final KColumn kColumn
    ) {
        return KCondition.likeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition lksw(
        final KValTextField kValTextField
    ) {
        return KCondition.likeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition lksw(
        final String value
    ) {
        return KCondition.likeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilk(
        final KColumn kColumn
    ) {
        return KCondition.nilk(this, kColumn);
    }
    
    @Override
    public KCondition nilk(
        final KValTextField kValTextField
    ) {
        return KCondition.nilk(this, kValTextField);
    }
    
    @Override
    public KCondition nilk(
        final String value
    ) {
        return KCondition.nilk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilka(
        final KColumn kColumn
    ) {
        return KCondition.notILikeAny(this, kColumn);
    }
    
    @Override
    public KCondition nilka(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeAny(this, kValTextField);
    }
    
    @Override
    public KCondition nilka(
        final String value
    ) {
        return KCondition.notILikeAny(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilkew(
        final KColumn kColumn
    ) {
        return KCondition.notILikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition nilkew(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition nilkew(
        final String value
    ) {
        return KCondition.notILikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilksw(
        final KColumn kColumn
    ) {
        return KCondition.notILikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition nilksw(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition nilksw(
        final String value
    ) {
        return KCondition.notILikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notIBetween(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.nibt(this, kColumnLow, kColumnHigh);
    }
    
    @Override
    public KCondition notIBetween(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.nibt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    @Override
    public KCondition notIBetween(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.nibt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
    }
    
    @Override
    public KCondition nibt(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.nibt(this, kColumnLow, kColumnHigh);
    }
    
    @Override
    public KCondition nibt(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.nibt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    @Override
    public KCondition nibt(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.nibt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
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
    public KCondition nilt(
        final KColumn kColumn
    ) {
        return KCondition.nilt(this, kColumn);
    }
    
    @Override
    public KCondition nilt(
        final KValTextField kValTextField
    ) {
        return KCondition.nilt(this, kValTextField);
    }
    
    @Override
    public KCondition nilt(
        final String value
    ) {
        return KCondition.nilt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilte(
        final KColumn kColumn
    ) {
        return KCondition.nilte(this, kColumn);
    }
    
    @Override
    public KCondition nilte(
        final KValTextField kValTextField
    ) {
        return KCondition.nilte(this, kValTextField);
    }
    
    @Override
    public KCondition nilte(
        final String value
    ) {
        return KCondition.nilte(this, KFunction.val(value));
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
    public KCondition notILessThan(
        final KColumn kColumn
    ) {
        return KCondition.nilt(this, kColumn);
    }
    
    @Override
    public KCondition notILessThan(
        final KValTextField kValTextField
    ) {
        return KCondition.nilt(this, kValTextField);
    }
    
    @Override
    public KCondition notILessThan(
        final String value
    ) {
        return KCondition.nilt(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILessThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.nilte(this, kColumn);
    }
    
    @Override
    public KCondition notILessThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.nilte(this, kValTextField);
    }
    
    @Override
    public KCondition notILessThanOrEqualTo(
        final String value
    ) {
        return KCondition.nilte(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILike(
        final KColumn kColumn
    ) {
        return KCondition.nilk(this, kColumn);
    }
    
    @Override
    public KCondition notILike(
        final KValTextField kValTextField
    ) {
        return KCondition.nilk(this, kValTextField);
    }
    
    @Override
    public KCondition notILike(
        final String value
    ) {
        return KCondition.nilk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILikeAny(
        final KColumn kColumn
    ) {
        return KCondition.notILikeAny(this, kColumn);
    }
    
    @Override
    public KCondition notILikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeAny(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeAny(
        final String value
    ) {
        return KCondition.notILikeAny(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILikeEndWith(
        final KColumn kColumn
    ) {
        return KCondition.notILikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition notILikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeEndWith(
        final String value
    ) {
        return KCondition.notILikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.notILikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition notILikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.notILikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeStartWith(
        final String value
    ) {
        return KCondition.notILikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nlk(
        final KColumn kColumn
    ) {
        return KCondition.nlk(this, kColumn);
    }
    
    @Override
    public KCondition nlk(
        final KValTextField kValTextField
    ) {
        return KCondition.nlk(this, kValTextField);
    }
    
    @Override
    public KCondition nlk(
        final String value
    ) {
        return KCondition.nlk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nlka(
        final KColumn kColumn
    ) {
        return KCondition.nlka(this, kColumn);
    }
    
    @Override
    public KCondition nlka(
        final KValTextField kValTextField
    ) {
        return KCondition.nlka(this, kValTextField);
    }
    
    @Override
    public KCondition nlka(
        final String value
    ) {
        return KCondition.nlka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nlkew(
        final KColumn kColumn
    ) {
        return KCondition.notLikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition nlkew(
        final KValTextField kValTextField
    ) {
        return KCondition.notLikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition nlkew(
        final String value
    ) {
        return KCondition.notLikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nlksw(
        final KColumn kColumn
    ) {
        return KCondition.notLikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition nlksw(
        final KValTextField kValTextField
    ) {
        return KCondition.notLikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition nlksw(
        final String value
    ) {
        return KCondition.notLikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLike(
        final KColumn kColumn
    ) {
        return KCondition.nlk(this, kColumn);
    }
    
    @Override
    public KCondition notLike(
        final KValTextField kValTextField
    ) {
        return KCondition.nlk(this, kValTextField);
    }
    
    @Override
    public KCondition notLike(
        final String value
    ) {
        return KCondition.nlk(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLikeAny(
        final KColumn kColumn
    ) {
        return KCondition.nlka(this, kColumn);
    }
    
    @Override
    public KCondition notLikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.nlka(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeAny(
        final String value
    ) {
        return KCondition.nlka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLikeEndWith(
        final KColumn kColumn
    ) {
        return KCondition.notLikeEndWith(this, kColumn);
    }
    
    @Override
    public KCondition notLikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.notLikeEndWith(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeEndWith(
        final String value
    ) {
        return KCondition.notLikeEndWith(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.notLikeStartWith(this, kColumn);
    }
    
    @Override
    public KCondition notLikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.notLikeStartWith(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeStartWith(
        final String value
    ) {
        return KCondition.notLikeStartWith(this, KFunction.val(value));
    }
    
    @Override
    protected KValTextField cloneMe() {
        return new KValTextField(this.sb, new ArrayList<>(this.params), this.operating, this.closed);
    }
}
