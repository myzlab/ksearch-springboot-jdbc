package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class KBuilder {
    
    public KExecutor jdbc(
        final String jdbc
    ) {
        return KExecutor.getInstance(this, jdbc);
    }
    
    private KExecutor jdbc() {
        return KExecutor.getInstance(this, this.getJdbcTemplateDefaultName());
    }
    
    public KWith with(
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        return KWith.getInstance(jdbc(), false, kCommonTableExpressionsFilled);
    }
    
    public KWith withRecursive(
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        return KWith.getInstance(jdbc(), true, kCommonTableExpressionsFilled);
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getInstance(jdbc(), kQuery, alias);
    }
    
    public KSelect selectDistinct(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getDistinctInstance(jdbc(), kQuery, alias);
    }
    
    public KSelect select(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getInstance(jdbc(), kColumnsAllowedToSelect);
    }
    
    public KSelect selectDistinct(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getDistinctInstance(jdbc(), kColumnsAllowedToSelect);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getInstance(jdbc(), kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KRaw kRaw
    ) {
        return KDistinctOnSelect.getInstance(jdbc(), kRaw);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final int n
    ) {
        return KDistinctOnSelect.getInstance(jdbc(), n);
    }
    
    public KDeleteFrom deleteFrom(
        final KTable kTable
    ) {
        return KDeleteFrom.getInstance(jdbc(), kTable);
    }
    
    public KDeleteFrom deleteFrom(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KDeleteFrom.getInstance(jdbc(), new KTable(null, kRaw.content, null));
    }
    
    public KInsertInto insertInto(
        final KTable kTable
    ) {
        return KInsertInto.getInstance(jdbc(), kTable);
    }
    
    public KUpdate update(
        final KTable kTable
    ) {
        return KUpdate.getInstance(jdbc(), kTable);
    }
    
    public KUpdate update(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KUpdate.getInstance(jdbc(), new KTable(null, kRaw.content, null));
    }
    
    public abstract Map<String, JdbcTemplate> getJdbcTemplates();
    
    public abstract String getJdbcTemplateDefaultName();
}
