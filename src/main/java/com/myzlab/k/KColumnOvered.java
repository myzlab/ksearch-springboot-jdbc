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
        final KWindowDefinitionOrdered kWindowDefinitionOrdered
    ) {
        super(sb, params, operating, closed);
        
        this.process(kWindowDefinitionOrdered);
    }
    
    protected KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed,
        final KWindowDefinitionNamed kWindowDefinitionNamed
    ) {
        super(sb, params, operating, closed);
        
        this.process(kWindowDefinitionNamed);
    }
    
    protected KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed,
        final KWindowDefinitionUnnamed kWindowDefinitionUnnamed
    ) {
        super(sb, params, operating, closed);
        
        this.process(kWindowDefinitionUnnamed);
    }
    
    protected KColumnOvered(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed,
        final KWindowDefinitionPartitioned kWindowDefinitionPartitioned
    ) {
        super(sb, params, operating, closed);
        
        this.process(kWindowDefinitionPartitioned);
    }

    @Override
    protected KColumnOvered cloneMe() {
        return new KColumnOvered(this.sb, this.params, this.operating, this.closed);
    }
    
    private void process(
        final KWindowDefinitionOrdered kWindowDefinitionOrdered
    ) {
        if (kWindowDefinitionOrdered == null) {
            throw KExceptionHelper.internalServerError("The 'kWindowDefinitionOrdered' param is required"); 
        }
        
        this.sb.append(" OVER");
        
        if (kWindowDefinitionOrdered.name != null) {
            this.sb.append(" ").append(kWindowDefinitionOrdered.name);
        } else {
            this.sb.append("(").append(kWindowDefinitionOrdered.sb.toString()).append(")");
        }
    }
    
    private void process(
        final KWindowDefinitionNamed kWindowDefinitionNamed
    ) {
        if (kWindowDefinitionNamed == null) {
            throw KExceptionHelper.internalServerError("The 'kWindowDefinitionNamed' param is required"); 
        }
        
        this.sb.append(" OVER");
        
        if (kWindowDefinitionNamed.name != null) {
            this.sb.append(" ").append(kWindowDefinitionNamed.name);
        } else {
            this.sb.append("(").append(kWindowDefinitionNamed.sb.toString()).append(")");
        }
    }
    
    private void process(
        final KWindowDefinitionUnnamed kWindowDefinitionUnnamed
    ) {
        if (kWindowDefinitionUnnamed == null) {
            throw KExceptionHelper.internalServerError("The 'kWindowDefinitionUnnamed' param is required"); 
        }
        
        this.sb.append(" OVER(").append(kWindowDefinitionUnnamed.sb.toString()).append(")");
    }
    
    private void process(
        final KWindowDefinitionPartitioned kWindowDefinitionPartitioned
    ) {
        if (kWindowDefinitionPartitioned == null) {
            throw KExceptionHelper.internalServerError("The 'kWindowDefinitionPartitioned' param is required"); 
        }
        
        this.sb.append(" OVER");
        
        if (kWindowDefinitionPartitioned.name != null) {
            this.sb.append(" ").append(kWindowDefinitionPartitioned.name);
        } else {
            this.sb.append("(").append(kWindowDefinitionPartitioned.sb.toString()).append(")");
        }
    }
}
