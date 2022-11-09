package com.myzlab.k;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class KInitializer {
    
    public KWith with() {
        return new KWith();
    }
    
    public KSelect select(final KBaseColumn... kBaseColumns) {
        return KSelect.getInstance(kBaseColumns);
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
