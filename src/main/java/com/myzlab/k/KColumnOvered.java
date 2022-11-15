package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.List;

public class KColumnOvered extends KBaseColumn {
    
    protected KColumnOvered() {
        super();
    }
    
    private KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed,
        final KWindowDefinition kWindowDefinition
    ) {
        super(sb, params, operating, closed);
        
        this.process(kWindowDefinition);
    }
    
    @Override
    protected KColumnOvered cloneMe() {
        return new KColumnOvered(this.sb, this.params, this.operating, this.closed);
    }
    
    private void process(
        final KWindowDefinition kWindowDefinition
    ) {
        KUtils.assertNotNull(kWindowDefinition, "kWindowDefinition");
        
        this.sb.append(" OVER");
        
        if (kWindowDefinition.name != null) {
            this.sb.append(" ").append(kWindowDefinition.name);
        } else {
            this.sb.append("(").append(kWindowDefinition.sb.toString()).append(")");
        }
    }
}
