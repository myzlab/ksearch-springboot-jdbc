package com.myzlab.ksearch;

import com.myzlab.k.KInitializer;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public class KTest extends KInitializer {

    @Override
    public Map<String, JdbcTemplate> getJdbcTemplates() {
        return null;
    }

    @Override
    public String getJdbcTemplateDefaultName() {
        return null;
    }
}
