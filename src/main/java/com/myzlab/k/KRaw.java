package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToGroupBy;
import com.myzlab.k.allowed.KColumnAllowedToOrderBy;

public class KRaw implements KColumnAllowedToOrderBy, KColumnAllowedToGroupBy {

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
    
}
