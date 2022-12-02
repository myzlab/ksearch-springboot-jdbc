package com.myzlab.k;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class KInitializer {
    
    public KWith with(
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        return KWith.getInstance(this, false, kCommonTableExpressionsFilled);
    }
    
    public KWith withRecursive(
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        return KWith.getInstance(this, true, kCommonTableExpressionsFilled);
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getInstance(this, kQuery, alias);
    }
    
    public KSelect selectDistinct(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getDistinctInstance(this, kQuery, alias);
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getInstance(this, kBaseColumns);
    }
    
    public KSelect select(
        final KRaw... kRaws
    ) {
        return KSelect.getInstance(this, kRaws);
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
    
    public KDeleteFrom deleteFrom(
        final KTable kTable
    ) {
        return KDeleteFrom.getInstance(this, kTable);
    }
    
    public KDeleteFrom deleteFrom(
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KDeleteFrom.getInstance(this, new KTable(null, kRaw.content, null));
    }
    
    public KUpdate update(
        final KTable kTable
    ) {
        return KUpdate.getInstance(this, kTable);
    }
    
    public KUpdate update(
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KUpdate.getInstance(this, new KTable(null, kRaw.content, null));
    }
    
    public abstract Map<String, JdbcTemplate> getJdbcTemplates();
    
    public abstract String getJdbcTemplateDefaultName();
}
