package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import java.util.ArrayList;
import java.util.List;

public class KColumn extends KBaseColumnCastable implements TextMethods, KColumnAllowedToOrderBy {
    
    private final String name;
    
    protected KColumn() {
        super();
        
        this.name = null;
    }
    
    protected KColumn(
        final boolean closed
    ) {
        super(closed);
        
        this.name = null;
    }
    
    public KColumn(
        final String name
    ) {
        super();
        
        this.name = name;
        this.sb.append(name);
        this.operating = 1;
    }
    
    protected KColumn(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
        
        this.name = null;
    }
    
    protected KColumn(
        final StringBuilder sb,
        final int operating,
        final boolean closed
    ) {
        super(sb, new ArrayList<>(), operating, closed);
        
        this.name = null;
    }
    
    protected KColumn(
        final String name,
        final String val
    ) {
        super(val);
        
        this.name = name;
    }
    
    protected KColumn(
        final String name,
        final Number val
    ) {
        super(val);
        
        this.name = name;
    }
    
    protected KColumn(
        final String name,
        final StringBuilder sb
    ) {
        this(name);
        this.sb.append(sb);
    }
    
    protected KColumn(
        final String name,
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
        
        this.name = name;
    }
    
    public KColumn add(
        final KColumn kColumn
    ) {
        return KFunction.add(this, kColumn);
    }
    
    public KColumn add(
        final KValNumberField kValNumberField
    ) {
        return KFunction.add(this, kValNumberField);
    }
    
    public KColumn add(
        final Number number
    ) {
        return KFunction.add(this, number);
    }
    
    public KColumn avg() {
        return KFunction.avg(this);
    }
    
    public KColumn div(
        final KColumn kColumn
    ) {
        return KFunction.div(this, kColumn);
    }
    
    public KColumn div(
        final KValNumberField kValNumberField
    ) {
        return KFunction.div(this, kValNumberField);
    }
    
    public KColumn div(
        final Number number
    ) {
        return KFunction.div(this, number);
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
        return KCondition.ilkew(this, kColumn);
    }
    
    @Override
    public KCondition iLikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.ilkew(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeEndWith(
        final String value
    ) {
        return KCondition.ilkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.ilksw(this, kColumn);
    }
    
    @Override
    public KCondition iLikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.ilksw(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeStartWith(
        final String value
    ) {
        return KCondition.ilksw(this, KFunction.val(value));
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
        return KCondition.ilkew(this, kColumn);
    }
    
    @Override
    public KCondition ilkew(
        final KValTextField kValTextField
    ) {
        return KCondition.ilkew(this, kValTextField);
    }
    
    @Override
    public KCondition ilkew(
        final String value
    ) {
        return KCondition.ilkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition ilksw(
        final KColumn kColumn
    ) {
        return KCondition.ilksw(this, kColumn);
    }
    
    @Override
    public KCondition ilksw(
        final KValTextField kValTextField
    ) {
        return KCondition.ilksw(this, kValTextField);
    }
    
    @Override
    public KCondition ilksw(
        final String value
    ) {
        return KCondition.ilksw(this, KFunction.val(value));
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
    
    public KColumn isolate() {
        return KFunction.isolate(this);
    }
    
    public KColumn getJsonArray(final int index) {
        return KFunction.getJsonArray(this, index);
    }
    
    public KColumn getJsonArrayAsText(final int index) {
        return KFunction.getJsonArrayAsText(this, index);
    }
    
    public KColumn getJsonObject(final String name) {
        return KFunction.getJsonObject(this, name);
    }
    
    public KColumn getJsonObjectAsText(final String name) {
        return KFunction.getJsonObjectAsText(this, name);
    }
    
    public KColumn getJsonObjectAtPath(final String name) {
        return KFunction.getJsonObjectAtPath(this, name);
    }
    
    public KColumn getJsonObjectAtPathAsText(final String name) {
        return KFunction.getJsonObjectAtPathAsText(this, name);
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
        return KCondition.lkew(this, kColumn);
    }
    
    @Override
    public KCondition likeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.lkew(this, kValTextField);
    }
    
    @Override
    public KCondition likeEndWith(
        final String value
    ) {
        return KCondition.lkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition likeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.lksw(this, kColumn);
    }
    
    @Override
    public KCondition likeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.lksw(this, kValTextField);
    }
    
    @Override
    public KCondition likeStartWith(
        final String value
    ) {
        return KCondition.lksw(this, KFunction.val(value));
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
        return KCondition.lkew(this, kColumn);
    }
    
    @Override
    public KCondition lkew(
        final KValTextField kValTextField
    ) {
        return KCondition.lkew(this, kValTextField);
    }
    
    @Override
    public KCondition lkew(
        final String value
    ) {
        return KCondition.lkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition lksw(
        final KColumn kColumn
    ) {
        return KCondition.lksw(this, kColumn);
    }
    
    @Override
    public KCondition lksw(
        final KValTextField kValTextField
    ) {
        return KCondition.lksw(this, kValTextField);
    }
    
    @Override
    public KCondition lksw(
        final String value
    ) {
        return KCondition.lksw(this, KFunction.val(value));
    }
    
    public KColumn sub(
        final KColumn kColumn
    ) {
        return KFunction.sub(this, kColumn);
    }
    
    public KColumn sub(
        final KValNumberField kValNumberField
    ) {
        return KFunction.sub(this, kValNumberField);
    }
    
    public KColumn sub(
        final Number number
    ) {
        return KFunction.sub(this, number);
    }
    
    public KColumn mod(
        final KColumn kColumn
    ) {
        return KFunction.mod(this, kColumn);
    }
    
    public KColumn mod(
        final KValNumberField kValNumberField
    ) {
        return KFunction.mod(this, kValNumberField);
    }
    
    public KColumn mod(
        final Number number
    ) {
        return KFunction.mod(this, number);
    }
    
    public KColumn mul(
        final KColumn kColumn
    ) {
        return KFunction.mul(this, kColumn);
    }
    
    public KColumn mul(
        final KValNumberField kValNumberField
    ) {
        return KFunction.mul(this, kValNumberField);
    }
    
    public KColumn mul(
        final Number number
    ) {
        return KFunction.mul(this, number);
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
        return KCondition.nilka(this, kColumn);
    }
    
    @Override
    public KCondition nilka(
        final KValTextField kValTextField
    ) {
        return KCondition.nilka(this, kValTextField);
    }
    
    @Override
    public KCondition nilka(
        final String value
    ) {
        return KCondition.nilka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilkew(
        final KColumn kColumn
    ) {
        return KCondition.nilkew(this, kColumn);
    }
    
    @Override
    public KCondition nilkew(
        final KValTextField kValTextField
    ) {
        return KCondition.nilkew(this, kValTextField);
    }
    
    @Override
    public KCondition nilkew(
        final String value
    ) {
        return KCondition.nilkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nilksw(
        final KColumn kColumn
    ) {
        return KCondition.nilksw(this, kColumn);
    }
    
    @Override
    public KCondition nilksw(
        final KValTextField kValTextField
    ) {
        return KCondition.nilksw(this, kValTextField);
    }
    
    @Override
    public KCondition nilksw(
        final String value
    ) {
        return KCondition.nilksw(this, KFunction.val(value));
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
        return KCondition.nilka(this, kColumn);
    }
    
    @Override
    public KCondition notILikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.nilka(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeAny(
        final String value
    ) {
        return KCondition.nilka(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILikeEndWith(
        final KColumn kColumn
    ) {
        return KCondition.nilkew(this, kColumn);
    }
    
    @Override
    public KCondition notILikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.nilkew(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeEndWith(
        final String value
    ) {
        return KCondition.nilkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notILikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.nilksw(this, kColumn);
    }
    
    @Override
    public KCondition notILikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.nilksw(this, kValTextField);
    }
    
    @Override
    public KCondition notILikeStartWith(
        final String value
    ) {
        return KCondition.nilksw(this, KFunction.val(value));
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
        return KCondition.nlkew(this, kColumn);
    }
    
    @Override
    public KCondition nlkew(
        final KValTextField kValTextField
    ) {
        return KCondition.nlkew(this, kValTextField);
    }
    
    @Override
    public KCondition nlkew(
        final String value
    ) {
        return KCondition.nlkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition nlksw(
        final KColumn kColumn
    ) {
        return KCondition.nlksw(this, kColumn);
    }
    
    @Override
    public KCondition nlksw(
        final KValTextField kValTextField
    ) {
        return KCondition.nlksw(this, kValTextField);
    }
    
    @Override
    public KCondition nlksw(
        final String value
    ) {
        return KCondition.nlksw(this, KFunction.val(value));
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
        return KCondition.nlkew(this, kColumn);
    }
    
    @Override
    public KCondition notLikeEndWith(
        final KValTextField kValTextField
    ) {
        return KCondition.nlkew(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeEndWith(
        final String value
    ) {
        return KCondition.nlkew(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLikeStartWith(
        final KColumn kColumn
    ) {
        return KCondition.nlksw(this, kColumn);
    }
    
    @Override
    public KCondition notLikeStartWith(
        final KValTextField kValTextField
    ) {
        return KCondition.nlksw(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeStartWith(
        final String value
    ) {
        return KCondition.nlksw(this, KFunction.val(value));
    }
    
    public KColumnOvered over(
        final KWindowDefinitionOrdered kWindowDefinitionOrdered
    ) {
        return new KColumnOvered(sb, params, operating, closed, kWindowDefinitionOrdered);
    }
    
    public KColumnOvered over(
        final KWindowDefinitionNamed kWindowDefinitionNamed
    ) {
        return new KColumnOvered(sb, params, operating, closed, kWindowDefinitionNamed);
    }
    
    public KColumnOvered over(
        final KWindowDefinitionUnnamed kWindowDefinitionUnnamed
    ) {
        return new KColumnOvered(sb, params, operating, closed, kWindowDefinitionUnnamed);
    }
    
    public KColumnOvered over(
        final KWindowDefinitionPartitioned kWindowDefinitionPartitioned
    ) {
        return new KColumnOvered(sb, params, operating, closed, kWindowDefinitionPartitioned);
    }
    
    public KColumnOrdered asc() {
        return new KColumnOrdered(sb, params, operating, closed, 1);
    }
    
    public KColumnOrdered desc() {
        return new KColumnOrdered(sb, params, operating, closed, -1);
    }
    
    @Override
    protected KColumn cloneMe() {
        return new KColumn(this.name, this.sb, new ArrayList<>(this.params), this.operating, this.closed);
    }
    
    @Override
    public String getSqlToOrderBy() {
        return KUtils.reverseParams(this);
    }
    
}
