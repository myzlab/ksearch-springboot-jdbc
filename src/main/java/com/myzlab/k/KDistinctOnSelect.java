package com.myzlab.k;

public class KDistinctOnSelect {
    
    private KInitializer k;
    private KQueryData kQueryData;
    
    private KDistinctOnSelect(
        final KInitializer kInitializer
    ) {
        this.k = kInitializer;
        this.kQueryData = new KQueryData();
    }
    
    public static KDistinctOnSelect getSelectDistinctOnInstance(
        final KInitializer kInitializer,
        final KColumn kColumn
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kColumn);
        
        return kDistinctOnSelect;
    }
    
    public static KDistinctOnSelect getSelectDistinctOnInstance(
        final KInitializer kInitializer,
        final KValNumberField kValNumberField
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(kValNumberField);
        
        return kDistinctOnSelect;
    }
    
    public static KDistinctOnSelect getSelectDistinctOnInstance(
        final KInitializer kInitializer,
        final Number number
    ) {
        final KDistinctOnSelect kDistinctOnSelect = new KDistinctOnSelect(kInitializer);
        
        kDistinctOnSelect.processSelectDistinctOn(number);
        
        return kDistinctOnSelect;
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getSelectInstance(this.k, this.kQueryData, kBaseColumns);
    }
    
    private void processSelectDistinctOn(
        final KColumn kColumn
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        
        this.kQueryData.sb.append("SELECT DISTINCT ON (").append(kColumn.toSql()).append(")");
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
