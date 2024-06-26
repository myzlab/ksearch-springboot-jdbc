package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToOver;
import java.util.List;

public class KColumnOvered extends KBaseColumn implements KColumnAllowedToSelect {
    
    protected KColumnOvered() {
        super();
    }
    
    private KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final boolean closed
    ) {
        super(sb, params, closed);
    }
    
    protected KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final boolean closed,
        final KWindowDefinitionAllowedToOver kWindowDefinitionAllowedToOver
    ) {
        super(sb, params, closed);
        
        this.process(kWindowDefinitionAllowedToOver);
    }
    
    public KAliasedColumn as(
        final String alias
    ) {
        return KFunction.as(this, alias);
    }
    
    @Override
    protected KColumnOvered cloneMe() {
        return new KColumnOvered(this.sb, this.params, this.closed);
    }
    
    private void process(
        final KWindowDefinitionAllowedToOver kWindowDefinitionAllowedToOver
    ) {
        KUtils.assertNotNull(kWindowDefinitionAllowedToOver, "kWindowDefinitionAllowedToOver");
        
        this.sb.append(" OVER");
        
        if (kWindowDefinitionAllowedToOver.getName() != null) {
            this.sb.append(" \"").append(kWindowDefinitionAllowedToOver.getName()).append("\"");
        } else {
            this.sb.append("(").append(kWindowDefinitionAllowedToOver.getSql()).append(")");
            this.params.addAll(kWindowDefinitionAllowedToOver.getParams());
        }
    }

    @Override
    public KBaseColumn getKBaseColumn() {
        return this;
    }
}
