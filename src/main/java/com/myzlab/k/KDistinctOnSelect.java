package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;

public class KDistinctOnSelect {
    
    private KInitializer k;
    private KQueryData kQueryData;
    
    private KDistinctOnSelect(
        final KInitializer kInitializer
    ) {
        this.k = kInitializer;
        this.kQueryData = new KQueryData();
        this.kQueryData.distinctOn = true;
    }
    
    private KDistinctOnSelect(
        final KQueryData kQueryData,
        final KInitializer kInitializer
    ) {
        this.k = kInitializer;
        this.kQueryData = kQueryData;
        this.kQueryData.distinctOn = true;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KColumn kColumn
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kColumn);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KValNumberField kValNumberField
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kValNumberField);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final Number number
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(number);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(new KColumn(new StringBuilder(kRaw.content), false));
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KColumn kColumn
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kColumn);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KValNumberField kValNumberField
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kValNumberField);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final Number number
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(number);
        
        return kDistinctOnSelect;
    }
    
    protected static KDistinctOnSelect getInstance(
        final KInitializer kInitializer,
        final KQueryData kQueryData,
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kQueryData, kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(new KColumn(new StringBuilder(kRaw.content), false));
        
        return kDistinctOnSelect;
    }
    
    public KSelect select(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kColumnsAllowedToSelect);
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kQuery, alias);
    }
    
//    public KSelect select(
//        final KBaseColumn... kBaseColumns
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kBaseColumns);
//    }
//    
//    public KSelect select(
//        final KRaw... kRaws
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kRaws);
//    }
    
    private void processSelectDistinctOn(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        this.kQueryData.sb.append(this.kQueryData.sb.length() > 0 ? " " : "").append("SELECT DISTINCT ON (").append(KUtils.reverseParams(kColumn)).append(")");
    }
    
    private void processSelectDistinctOn(
        final KValNumberField kValNumberField
    ) {
        KUtils.assertNotNull(kValNumberField, "kValNumberField");
        
        this.kQueryData.sb.append("SELECT DISTINCT ON (").append(KUtils.reverseParams(kValNumberField)).append(")");
    }
    
    private void processSelectDistinctOn(
        final Number number
    ) {
        KUtils.assertNotNull(number, "number");
        
        this.kQueryData.sb.append("SELECT DISTINCT ON (").append(number).append(")");
    }
}
