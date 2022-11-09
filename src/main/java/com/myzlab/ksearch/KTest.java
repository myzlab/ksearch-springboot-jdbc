package com.myzlab.ksearch;

import com.myzlab.k.KInitializer;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public class KTest extends KInitializer {

    @Override
    public Map<String, JdbcTemplate> getJdbcTemplates() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJdbcTemplateDefaultName() {
        return null;
    }
}
