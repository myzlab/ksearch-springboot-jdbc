package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;
import org.springframework.jdbc.core.JdbcTemplate;

public class KExecutor {
    
    private final KBuilder k;
    private final String jdbc;

    private KExecutor(
        final KBuilder k,
        final String jdbc
    ) {
        this.k =  k;
        this.jdbc = jdbc;
    }
    
    protected JdbcTemplate getJdbc() {
        return k.getJdbcTemplates().get(jdbc);
    }
    
    protected static KExecutor getInstance(
        final KBuilder kBuilder,
        final String jdbc
    ) {
        return new KExecutor(kBuilder, jdbc);
    }
    
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
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getInstance(this, kColumnsAllowedToSelect);
    }
    
    public KSelect selectDistinct(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getDistinctInstance(this, kColumnsAllowedToSelect);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getInstance(this, kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KRaw kRaw
    ) {
        return KDistinctOnSelect.getInstance(this, kRaw);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final int n
    ) {
        return KDistinctOnSelect.getInstance(this, n);
    }
    
    public KDeleteFrom deleteFrom(
        final KTable kTable
    ) {
        return KDeleteFrom.getInstance(this, kTable);
    }
    
    public KDeleteFrom deleteFrom(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KDeleteFrom.getInstance(this, new KTable(null, kRaw.content, null));
    }
    
    public KInsertInto insertInto(
        final KTable kTable
    ) {
        return KInsertInto.getInstance(this, kTable);
    }
    
    public KUpdate update(
        final KTable kTable
    ) {
        return KUpdate.getInstance(this, kTable);
    }
    
    public KUpdate update(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KUpdate.getInstance(this, new KTable(null, kRaw.content, null));
    }
    
}
