package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public class KColumn extends KBaseColumnCastable implements Cloneable, TextMethods {
    
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
        return KCondition.iLike(this, kColumn);
    }
    
    @Override
    public KCondition iLike(
        final KValTextField kValTextField
    ) {
        return KCondition.iLike(this, kValTextField);
    }
    
    @Override
    public KCondition iLike(
        final String value
    ) {
        return KCondition.iLike(this, KFunction.val(value));
    }
    
    @Override
    public KCondition iLikeAny(
        final KColumn kColumn
    ) {
        return KCondition.iLikeAny(this, kColumn);
    }
    
    @Override
    public KCondition iLikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.iLikeAny(this, kValTextField);
    }
    
    @Override
    public KCondition iLikeAny(
        final String value
    ) {
        return KCondition.iLikeAny(this, KFunction.val(value));
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
        return KCondition.like(this, kColumn);
    }
    
    @Override
    public KCondition like(
        final KValTextField kValTextField
    ) {
        return KCondition.like(this, kValTextField);
    }
    
    @Override
    public KCondition like(
        final String value
    ) {
        return KCondition.like(this, KFunction.val(value));
    }
    
    @Override
    public KCondition likeAny(
        final KColumn kColumn
    ) {
        return KCondition.likeAny(this, kColumn);
    }
    
    @Override
    public KCondition likeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.likeAny(this, kValTextField);
    }
    
    @Override
    public KCondition likeAny(
        final String value
    ) {
        return KCondition.likeAny(this, KFunction.val(value));
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
        return KCondition.notILike(this, kColumn);
    }
    
    @Override
    public KCondition notILike(
        final KValTextField kValTextField
    ) {
        return KCondition.notILike(this, kValTextField);
    }
    
    @Override
    public KCondition notILike(
        final String value
    ) {
        return KCondition.notILike(this, KFunction.val(value));
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
    public KCondition notLike(
        final KColumn kColumn
    ) {
        return KCondition.notLike(this, kColumn);
    }
    
    @Override
    public KCondition notLike(
        final KValTextField kValTextField
    ) {
        return KCondition.notLike(this, kValTextField);
    }
    
    @Override
    public KCondition notLike(
        final String value
    ) {
        return KCondition.notLike(this, KFunction.val(value));
    }
    
    @Override
    public KCondition notLikeAny(
        final KColumn kColumn
    ) {
        return KCondition.notLikeAny(this, kColumn);
    }
    
    @Override
    public KCondition notLikeAny(
        final KValTextField kValTextField
    ) {
        return KCondition.notLikeAny(this, kValTextField);
    }
    
    @Override
    public KCondition notLikeAny(
        final String value
    ) {
        return KCondition.notLikeAny(this, KFunction.val(value));
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
    protected KColumn cloneMe() {
        return new KColumn(this.name, this.sb, new ArrayList<>(this.params), this.operating, this.closed);
    }
    
//    
//    public KOverColumn over() {
//        return new KOverColumn();
//    }
//    
//    public KColumn over(
//        final KOrderedColumn... kOrderedColumn
//    ) {
//        return new KColumn();
//    }
//    
//    public KColumn over(
//        final KPartitionByColumn kPartitionByColumn,
//        final KOrderedColumn... kOrderedColumn
//    ) {
//        return new KColumn();
//    }
//    
//    public KPartitionByColumn partitionBy() {
//        return new KPartitionByColumn();
//    }
//    
//    public KOrderedColumn asc() {
//        return new KOrderedColumn();
//    }
//    
//    public KOrderedColumn desc() {
//        return new KOrderedColumn();
//    }
//    
//    public KColumn avg() {
//        return new KColumn();
//    }
//    
//    public KColumn sum() {
//        return new KColumn();
//    }
//    
//    public KColumn min() {
//        return new KColumn();
//    }
//    
//    public KColumn max() {
//        return new KColumn();
//    }
}
