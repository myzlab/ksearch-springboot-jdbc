package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KField<T> extends KBaseField<T> implements Cloneable {
    
    private final String name;
    
    public KField() {
        this.name = null;
    }
    
    public KField(
        final String name
    ) {
        this.name = name;
        sb.append(name);
    }
    
    public KField(
        final StringBuilder sb
    ) {
        this();
        this.sb.append(sb);
    }
    
    public KField(
        final String name,
        final StringBuilder sb
    ) {
        this(name);
        this.sb.append(sb);
    }
    
    public KField add(
        final KField kField
    ) {
        return KFunction.add(this, kField);
    }
    
    public KField add(
        final Number number
    ) {
        return KFunction.add(this, number);
    }

    public KAliasedField as(
        final String alias
    ) {
        return KFunction.as(this, alias);
    }
    
    public KField avg() {
        return KFunction.avg(this);
    }
    
    public KField cast(
        final KDataType kDataType    
    ) {
        return KFunction.cast(this, kDataType);
    }
    
    public KField div(
        final KField kField
    ) {
        return KFunction.div(this, kField);
    }
    
    public KField div(
        final Number number
    ) {
        return KFunction.div(this, number);
    }
    
    public KField isolate() {
        return KFunction.isolate(this);
    }
    
    public KField getJsonArray(final int index) {
        return KFunction.getJsonArray(this, index);
    }
    
    public KField getJsonArrayAsText(final int index) {
        return KFunction.getJsonArrayAsText(this, index);
    }
    
    public KField getJsonObject(final String name) {
        return KFunction.getJsonObject(this, name);
    }
    
    public KField getJsonObjectAsText(final String name) {
        return KFunction.getJsonObjectAsText(this, name);
    }
    
    public KField getJsonObjectAtPath(final String name) {
        return KFunction.getJsonObjectAtPath(this, name);
    }
    
    public KField getJsonObjectAtPathAsText(final String name) {
        return KFunction.getJsonObjectAtPathAsText(this, name);
    }
    
    public KField sub(
        final KField kField
    ) {
        return KFunction.sub(this, kField);
    }
    
    public KField sub(
        final Number number
    ) {
        return KFunction.sub(this, number);
    }
    
    public KField mod(
        final KField kField
    ) {
        return KFunction.mod(this, kField);
    }
    
    public KField mod(
        final Number number
    ) {
        return KFunction.mod(this, number);
    }
    
    public KField mul(
        final KField kField
    ) {
        return KFunction.mul(this, kField);
    }
    
    public KField mul(
        final Number number
    ) {
        return KFunction.mul(this, number);
    }
    
    protected KField cloneMe() {
        try {
            return (KField) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KField object");
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
