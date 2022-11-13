package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import java.util.List;

public class KColumnOrdered extends KBaseColumn implements KColumnAllowedToOrderBy {
    
    protected KColumnOrdered() {
        super();
    }
    
    private KColumnOrdered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KColumnOrdered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed,
        final int orientation
    ) {
        super(sb, params, operating, closed);
        
        this.process(orientation);
    }

    @Override
    protected KColumnOrdered cloneMe() {
        return new KColumnOrdered(this.sb, this.params, this.operating, this.closed);
    }
    
    private void process(
        final int orientation
    ) {
        this.sb.append(" ").append(orientation > 0 ? "ASC" : "DESC");
    }
    
    @Override
    public String getSqlToOrderBy() {
        return KUtils.reverseParams(this);
    }
}
