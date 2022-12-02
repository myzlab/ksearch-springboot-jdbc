package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KColumnAllowedToReturning;
import java.util.ArrayList;
import java.util.List;

public class KRaw implements KColumnAllowedToOrderBy, KColumnAllowedToGroupBy, KColumnAllowedToReturning {

    protected String content;

    public KRaw(String content) {
        this.content = content;
    }

    @Override
    public String getSqlToOrderBy() {
        return content;
    }

    @Override
    public String getSqlToGroupBy() {
        return content;
    }
    
    @Override
    public String getSqlToReturning() {
        return content;
    }

    @Override
    public List<Object> getParams() {
        return new ArrayList<>();
    }
}
