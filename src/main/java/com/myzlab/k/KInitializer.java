package com.myzlab.k;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class KInitializer {
    
    public KWith with() {
        return new KWith();
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getSelectInstance(this, kBaseColumns);
    }
    
    public KSelect selectDistinct(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getSelectDistinctInstance(this, kBaseColumns);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getSelectDistinctOnInstance(this, kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KValNumberField kValNumberField
    ) {
        return KDistinctOnSelect.getSelectDistinctOnInstance(this, kValNumberField);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final Number number
    ) {
        return KDistinctOnSelect.getSelectDistinctOnInstance(this, number);
    }
    
    public void insertInto() {
        
    }
    
    public void update() {
        
    }
    
    public void delete() {
        
    }
    
    public abstract Map<String, JdbcTemplate> getJdbcTemplates();
    
    public abstract String getJdbcTemplateDefaultName();
}
