package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KColumn<T> extends KBaseColumn<T> implements Cloneable {
    
    private final String name;
    
    public KColumn() {
        this.name = null;
    }
    
    public KColumn(
        final String name
    ) {
        this.name = name;
        sb.append(name);
    }
    
    public KColumn(
        final StringBuilder sb
    ) {
        this();
        this.sb.append(sb);
    }
    
    public KColumn(
        final String name,
        final StringBuilder sb
    ) {
        this(name);
        this.sb.append(sb);
    }
    
    public KColumn add(
        final KColumn kColumn
    ) {
        return KFunction.add(this, kColumn);
    }
    
    public KColumn add(
        final Number number
    ) {
        return KFunction.add(this, number);
    }

    public KAliasedColumn as(
        final String alias
    ) {
        return KFunction.as(this, alias);
    }
    
    public KColumn avg() {
        return KFunction.avg(this);
    }
    
    public KColumn cast(
        final KDataType kDataType    
    ) {
        return KFunction.cast(this, kDataType);
    }
    
    public KColumn div(
        final KColumn kColumn
    ) {
        return KFunction.div(this, kColumn);
    }
    
    public KColumn div(
        final Number number
    ) {
        return KFunction.div(this, number);
    }
    
    public KCondition eq(
        final KColumn kColumn
    ) {
        return KCondition.eq(this, kColumn);
    }
    
    public KCondition eq(
        final KValField kValField
    ) {
        return KCondition.eq(this, kValField);
    }
    
    public KCondition eq(
        final Number number
    ) {
        return eq(KFunction.val(number));
    }
    
    public KCondition eq(
        final String string
    ) {
        return eq(KFunction.val(string));
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
    
    public KColumn sub(
        final KColumn kColumn
    ) {
        return KFunction.sub(this, kColumn);
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
        final Number number
    ) {
        return KFunction.mul(this, number);
    }
    
    public KCondition neq(
        final KColumn kColumn
    ) {
        return KCondition.neq(this, kColumn);
    }
    
    public KCondition neq(
        final KValField kValField
    ) {
        return KCondition.neq(this, kValField);
    }
    
    public KCondition neq(
        final Number number
    ) {
        return neq(KFunction.val(number));
    }
    
    public KCondition neq(
        final String string
    ) {
        return neq(KFunction.val(string));
    }
    
    protected KColumn cloneMe() {
        try {
            return (KColumn) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KColumn object");
        }
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
