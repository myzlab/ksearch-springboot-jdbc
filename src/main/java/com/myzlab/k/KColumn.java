package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public class KColumn extends KBaseColumnCastable implements Cloneable {
    
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
    
    public KCondition ieq(
        final KColumn kColumn
    ) {
        return iEqual(kColumn);
    }
    
    public KCondition ieq(
        final KValTextField kValTextField
    ) {
        return iEqual(kValTextField);
    }
    
    public KCondition ieq(
        final String string
    ) {
        return iEqual(string);
    }
    
    public KCondition iEqual(
        final KColumn kColumn
    ) {
        return KCondition.ieq(this, kColumn);
    }
    
    public KCondition iEqual(
        final KValTextField kValTextField
    ) {
        return KCondition.ieq(this, kValTextField);
    }
    
    public KCondition iEqual(
        final String string
    ) {
        return iEqual(KFunction.val(string));
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
        final String string
    ) {
        return notEqual(string);
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
        final String string
    ) {
        return neq(KFunction.val(string));
    }
    
    public KCondition nieq(
        final KColumn kColumn
    ) {
        return notIEqual(kColumn);
    }
    
    public KCondition nieq(
        final KValTextField kValTextField
    ) {
        return notIEqual(kValTextField);
    }
    
    public KCondition nieq(
        final String string
    ) {
        return notIEqual(string);
    }
    
    public KCondition notIEqual(
        final KColumn kColumn
    ) {
        return KCondition.nieq(this, kColumn);
    }
    
    public KCondition notIEqual(
        final KValTextField kValTextField
    ) {
        return KCondition.nieq(this, kValTextField);
    }
    
    public KCondition notIEqual(
        final String string
    ) {
        return nieq(KFunction.val(string));
    }
    
    @Override
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
