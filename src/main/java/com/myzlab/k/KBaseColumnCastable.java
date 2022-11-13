package com.myzlab.k;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class KBaseColumnCastable extends KBaseColumn {
    
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
    
    public KCondition between(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.bt(this, kColumnLow, kColumnHigh);
    }
    
    public KCondition between(
        final KValNumberField kValNumberFieldLow,
        final KValNumberField kValNumberFieldHigh
    ) {
        return KCondition.bt(this, kValNumberFieldLow, kValNumberFieldHigh);
    }
    
    public KCondition between(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.bt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    public KCondition between(
        final Number numberLow,
        final Number numberHigh
    ) {
        return KCondition.bt(this, KFunction.val(numberLow), KFunction.val(numberHigh));
    }
    
    public KCondition between(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.bt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
    }
    
    public KCondition bt(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.bt(this, kColumnLow, kColumnHigh);
    }
    
    public KCondition bt(
        final KValNumberField kValNumberFieldLow,
        final KValNumberField kValNumberFieldHigh
    ) {
        return KCondition.bt(this, kValNumberFieldLow, kValNumberFieldHigh);
    }
    
    public KCondition bt(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.bt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    public KCondition bt(
        final Number numberLow,
        final Number numberHigh
    ) {
        return KCondition.bt(this, KFunction.val(numberLow), KFunction.val(numberHigh));
    }
    
    public KCondition bt(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.bt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
    }
    
    public KColumn cast(
        final KDataType kDataType    
    ) {
        return KFunction.cast(this, kDataType);
    }
    
    public KCondition eq(
        final KColumn kColumn
    ) {
        return KCondition.eq(this, kColumn);
    }
    
    public KCondition eq(
        final KValNumberField kValNumberField
    ) {
        return KCondition.eq(this, kValNumberField);
    }
    
    public KCondition eq(
        final KValTextField kValTextField
    ) {
        return KCondition.eq(this, kValTextField);
    }
    
    public KCondition eq(
        final Number number
    ) {
        return KCondition.eq(this, KFunction.val(number));
    }
    
    public KCondition eq(
        final String value
    ) {
        return KCondition.eq(this, KFunction.val(value));
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
        return KCondition.eq(this, KFunction.val(number));
    }
    
    public KCondition equal(
        final String value
    ) {
        return KCondition.eq(this, KFunction.val(value));
    }
    
    public KCondition greaterThan(
        final KColumn kColumn
    ) {
        return KCondition.gt(this, kColumn);
    }
    
    public KCondition greaterThan(
        final KValNumberField kValNumberField
    ) {
        return KCondition.gt(this, kValNumberField);
    }
    
    public KCondition greaterThan(
        final KValTextField kValTextField
    ) {
        return KCondition.gt(this, kValTextField);
    }
    
    public KCondition greaterThan(
        final Number number
    ) {
        return KCondition.gt(this, KFunction.val(number));
    }
    
    public KCondition greaterThan(
        final String value
    ) {
        return KCondition.gt(this, KFunction.val(value));
    }
    
    public KCondition greaterThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.gte(this, kColumn);
    }
    
    public KCondition greaterThanOrEqualTo(
        final KValNumberField kValNumberField
    ) {
        return KCondition.gte(this, kValNumberField);
    }
    
    public KCondition greaterThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.gte(this, kValTextField);
    }
    
    public KCondition greaterThanOrEqualTo(
        final Number number
    ) {
        return KCondition.gte(this, KFunction.val(number));
    }
    
    public KCondition greaterThanOrEqualTo(
        final String value
    ) {
        return KCondition.gte(this, KFunction.val(value));
    }
    
    public KCondition gt(
        final KColumn kColumn
    ) {
        return KCondition.gt(this, kColumn);
    }
    
    public KCondition gt(
        final KValNumberField kValNumberField
    ) {
        return KCondition.gt(this, kValNumberField);
    }
    
    public KCondition gt(
        final KValTextField kValTextField
    ) {
        return KCondition.gt(this, kValTextField);
    }
    
    public KCondition gt(
        final Number number
    ) {
        return KCondition.gt(this, KFunction.val(number));
    }
    
    public KCondition gt(
        final String value
    ) {
        return KCondition.gt(this, KFunction.val(value));
    }
    
    public KCondition gte(
        final KColumn kColumn
    ) {
        return KCondition.gte(this, kColumn);
    }
    
    public KCondition gte(
        final KValNumberField kValNumberField
    ) {
        return KCondition.gte(this, kValNumberField);
    }
    
    public KCondition gte(
        final KValTextField kValTextField
    ) {
        return KCondition.gte(this, kValTextField);
    }
    
    public KCondition gte(
        final Number number
    ) {
        return KCondition.gte(this, KFunction.val(number));
    }
    
    public KCondition gte(
        final String value
    ) {
        return KCondition.gte(this, KFunction.val(value));
    }
    
    public KCondition in(
        final Object[] values
    ) {
        return KCondition.in(this, Arrays.asList(values));
    }
    
    public KCondition in(
        final Collection values
    ) {
        return KCondition.in(this, values);
    }
    
    public KCondition isFalse() {
        return KCondition.isFalse(this);
    }
    
    public KCondition isNotFalse() {
        return KCondition.isNotFalse(this);
    }
    
    public KCondition isNotNull() {
        return KCondition.isNotNull(this);
    }
    
    public KCondition isNull() {
        return KCondition.isNull(this);
    }
    
    public KCondition isNotTrue() {
        return KCondition.isNotTrue(this);
    }
    
    public KCondition isTrue() {
        return KCondition.isTrue(this);
    }
    
    public KCondition isUnknown() {
        return KCondition.isUnknown(this);
    }
    
    public KCondition isNotUnknown() {
        return KCondition.isNotUnknown(this);
    }
    
    public KCondition lessThan(
        final KColumn kColumn
    ) {
        return KCondition.lt(this, kColumn);
    }
    
    public KCondition lessThan(
        final KValNumberField kValNumberField
    ) {
        return KCondition.lt(this, kValNumberField);
    }
    
    public KCondition lessThan(
        final KValTextField kValTextField
    ) {
        return KCondition.lt(this, kValTextField);
    }
    
    public KCondition lessThan(
        final Number number
    ) {
        return KCondition.lt(this, KFunction.val(number));
    }
    
    public KCondition lessThan(
        final String value
    ) {
        return KCondition.lt(this, KFunction.val(value));
    }
    
    public KCondition lessThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.lte(this, kColumn);
    }
    
    public KCondition lessThanOrEqualTo(
        final KValNumberField kValNumberField
    ) {
        return KCondition.lte(this, kValNumberField);
    }
    
    public KCondition lessThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.lte(this, kValTextField);
    }
    
    public KCondition lessThanOrEqualTo(
        final Number number
    ) {
        return KCondition.lte(this, KFunction.val(number));
    }
    
    public KCondition lessThanOrEqualTo(
        final String value
    ) {
        return KCondition.lte(this, KFunction.val(value));
    }
    
    public KCondition lt(
        final KColumn kColumn
    ) {
        return KCondition.lt(this, kColumn);
    }
    
    public KCondition lt(
        final KValNumberField kValNumberField
    ) {
        return KCondition.lt(this, kValNumberField);
    }
    
    public KCondition lt(
        final KValTextField kValTextField
    ) {
        return KCondition.lt(this, kValTextField);
    }
    
    public KCondition lt(
        final Number number
    ) {
        return KCondition.lt(this, KFunction.val(number));
    }
    
    public KCondition lt(
        final String value
    ) {
        return KCondition.lt(this, KFunction.val(value));
    }
    
    public KCondition lte(
        final KColumn kColumn
    ) {
        return KCondition.lte(this, kColumn);
    }
    
    public KCondition lte(
        final KValNumberField kValNumberField
    ) {
        return KCondition.lte(this, kValNumberField);
    }
    
    public KCondition lte(
        final KValTextField kValTextField
    ) {
        return KCondition.lte(this, kValTextField);
    }
    
    public KCondition lte(
        final Number number
    ) {
        return KCondition.lte(this, KFunction.val(number));
    }
    
    public KCondition lte(
        final String value
    ) {
        return KCondition.lte(this, KFunction.val(value));
    }
    
    public KCondition nbt(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.nbt(this, kColumnLow, kColumnHigh);
    }
    
    public KCondition nbt(
        final KValNumberField kValNumberFieldLow,
        final KValNumberField kValNumberFieldHigh
    ) {
        return KCondition.nbt(this, kValNumberFieldLow, kValNumberFieldHigh);
    }
    
    public KCondition nbt(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.nbt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    public KCondition nbt(
        final Number numberLow,
        final Number numberHigh
    ) {
        return KCondition.nbt(this, KFunction.val(numberLow), KFunction.val(numberHigh));
    }
    
    public KCondition nbt(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.nbt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
    }
    
    public KCondition neq(
        final KColumn kColumn
    ) {
        return KCondition.neq(this, kColumn);
    }
    
    public KCondition neq(
        final KValTextField kValTextField
    ) {
        return KCondition.neq(this, kValTextField);
    }
    
    public KCondition neq(
        final KValNumberField kValNumberField
    ) {
        return KCondition.neq(this, kValNumberField);
    }
    
    public KCondition neq(
        final Number number
    ) {
        return KCondition.neq(this, KFunction.val(number));
    }
    
    public KCondition neq(
        final String value
    ) {
        return KCondition.neq(this, KFunction.val(value));
    }
    
    public KCondition ngt(
        final KColumn kColumn
    ) {
        return KCondition.ngt(this, kColumn);
    }
    
    public KCondition ngt(
        final KValNumberField kValNumberField
    ) {
        return KCondition.ngt(this, kValNumberField);
    }
    
    public KCondition ngt(
        final KValTextField kValTextField
    ) {
        return KCondition.ngt(this, kValTextField);
    }
    
    public KCondition ngt(
        final Number number
    ) {
        return KCondition.ngt(this, KFunction.val(number));
    }
    
    public KCondition ngt(
        final String value
    ) {
        return KCondition.ngt(this, KFunction.val(value));
    }
    
    public KCondition ngte(
        final KColumn kColumn
    ) {
        return KCondition.ngte(this, kColumn);
    }
    
    public KCondition ngte(
        final KValNumberField kValNumberField
    ) {
        return KCondition.ngte(this, kValNumberField);
    }
    
    public KCondition ngte(
        final KValTextField kValTextField
    ) {
        return KCondition.ngte(this, kValTextField);
    }
    
    public KCondition ngte(
        final Number number
    ) {
        return KCondition.ngte(this, KFunction.val(number));
    }
    
    public KCondition ngte(
        final String value
    ) {
        return KCondition.ngte(this, KFunction.val(value));
    }
    
    public KCondition nlt(
        final KColumn kColumn
    ) {
        return KCondition.nlt(this, kColumn);
    }
    
    public KCondition nlt(
        final KValNumberField kValNumberField
    ) {
        return KCondition.nlt(this, kValNumberField);
    }
    
    public KCondition nlt(
        final KValTextField kValTextField
    ) {
        return KCondition.nlt(this, kValTextField);
    }
    
    public KCondition nlt(
        final Number number
    ) {
        return KCondition.nlt(this, KFunction.val(number));
    }
    
    public KCondition nlt(
        final String value
    ) {
        return KCondition.nlt(this, KFunction.val(value));
    }
    
    public KCondition nlte(
        final KColumn kColumn
    ) {
        return KCondition.nlte(this, kColumn);
    }
    
    public KCondition nlte(
        final KValNumberField kValNumberField
    ) {
        return KCondition.nlte(this, kValNumberField);
    }
    
    public KCondition nlte(
        final KValTextField kValTextField
    ) {
        return KCondition.nlte(this, kValTextField);
    }
    
    public KCondition nlte(
        final Number number
    ) {
        return KCondition.nlte(this, KFunction.val(number));
    }
    
    public KCondition nlte(
        final String value
    ) {
        return KCondition.nlte(this, KFunction.val(value));
    }
    
    public KCondition notBetween(
        final KColumn kColumnLow,
        final KColumn kColumnHigh
    ) {
        return KCondition.nbt(this, kColumnLow, kColumnHigh);
    }
    
    public KCondition notBetween(
        final KValNumberField kValNumberFieldLow,
        final KValNumberField kValNumberFieldHigh
    ) {
        return KCondition.nbt(this, kValNumberFieldLow, kValNumberFieldHigh);
    }
    
    public KCondition notBetween(
        final KValTextField kValTextFieldLow,
        final KValTextField kValTextFieldHigh
    ) {
        return KCondition.nbt(this, kValTextFieldLow, kValTextFieldHigh);
    }
    
    public KCondition notBetween(
        final Number numberLow,
        final Number numberHigh
    ) {
        return KCondition.nbt(this, KFunction.val(numberLow), KFunction.val(numberHigh));
    }
    
    public KCondition notBetween(
        final String valueLow,
        final String valueHigh
    ) {
        return KCondition.nbt(this, KFunction.val(valueLow), KFunction.val(valueHigh));
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
        return KCondition.neq(this, KFunction.val(number));
    }
    
    public KCondition notEqual(
        final String value
    ) {
        return KCondition.neq(this, KFunction.val(value));
    }
    
    public KCondition notGreaterThan(
        final KColumn kColumn
    ) {
        return KCondition.ngt(this, kColumn);
    }
    
    public KCondition notGreaterThan(
        final KValNumberField kValNumberField
    ) {
        return KCondition.ngt(this, kValNumberField);
    }
    
    public KCondition notGreaterThan(
        final KValTextField kValTextField
    ) {
        return KCondition.ngt(this, kValTextField);
    }
    
    public KCondition notGreaterThan(
        final Number number
    ) {
        return KCondition.ngt(this, KFunction.val(number));
    }
    
    public KCondition notGreaterThan(
        final String value
    ) {
        return KCondition.ngt(this, KFunction.val(value));
    }
    
    public KCondition notGreaterThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.ngte(this, kColumn);
    }
    
    public KCondition notGreaterThanOrEqualTo(
        final KValNumberField kValNumberField
    ) {
        return KCondition.ngte(this, kValNumberField);
    }
    
    public KCondition notGreaterThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.ngte(this, kValTextField);
    }
    
    public KCondition notGreaterThanOrEqualTo(
        final Number number
    ) {
        return KCondition.ngte(this, KFunction.val(number));
    }
    
    public KCondition notGreaterThanOrEqualTo(
        final String value
    ) {
        return KCondition.ngte(this, KFunction.val(value));
    }
    
    public KCondition notIn(
        final Object[] values
    ) {
        return KCondition.notIn(this, Arrays.asList(values));
    }
    
    public KCondition notIn(
        final Collection values
    ) {
        return KCondition.notIn(this, values);
    }
    
    public KCondition notLessThan(
        final KColumn kColumn
    ) {
        return KCondition.nlt(this, kColumn);
    }
    
    public KCondition notLessThan(
        final KValNumberField kValNumberField
    ) {
        return KCondition.nlt(this, kValNumberField);
    }
    
    public KCondition notLessThan(
        final KValTextField kValTextField
    ) {
        return KCondition.nlt(this, kValTextField);
    }
    
    public KCondition notLessThan(
        final Number number
    ) {
        return KCondition.nlt(this, KFunction.val(number));
    }
    
    public KCondition notLessThan(
        final String value
    ) {
        return KCondition.nlt(this, KFunction.val(value));
    }
    
    public KCondition notLessThanOrEqualTo(
        final KColumn kColumn
    ) {
        return KCondition.nlte(this, kColumn);
    }
    
    public KCondition notLessThanOrEqualTo(
        final KValNumberField kValNumberField
    ) {
        return KCondition.nlte(this, kValNumberField);
    }
    
    public KCondition notLessThanOrEqualTo(
        final KValTextField kValTextField
    ) {
        return KCondition.nlte(this, kValTextField);
    }
    
    public KCondition notLessThanOrEqualTo(
        final Number number
    ) {
        return KCondition.nlte(this, KFunction.val(number));
    }
    
    public KCondition notLessThanOrEqualTo(
        final String value
    ) {
        return KCondition.nlte(this, KFunction.val(value));
    }
}
