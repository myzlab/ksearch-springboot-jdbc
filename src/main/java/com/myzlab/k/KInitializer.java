package com.myzlab.k;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class KInitializer {
    
    public KWith with(
        final KCommonTableExpressionFilled kCommonTableExpressionFilled
    ) {
        return KWith.getInstance(this, kCommonTableExpressionFilled);
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getInstance(this, kBaseColumns);
    }
    
    public KSelect selectDistinct(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getDistinctInstance(this, kBaseColumns);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getInstance(this, kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KValNumberField kValNumberField
    ) {
        return KDistinctOnSelect.getInstance(this, kValNumberField);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final Number number
    ) {
        return KDistinctOnSelect.getInstance(this, number);
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
