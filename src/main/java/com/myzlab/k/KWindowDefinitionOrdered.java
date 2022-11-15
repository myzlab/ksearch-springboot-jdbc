package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KWindowDefinitionOrdered extends KWindowDefinition implements KWindowDefinitionAllowedToWindow {
    
    private KWindowDefinitionOrdered(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        super(sb, name);
        
        this.process(kColumn);
    }
    
    private KWindowDefinitionOrdered(
        final StringBuilder sb,
        final String name,
        final KColumnOrdered kColumnOrdered
    ) {
        super(sb, name);
        
        this.process(kColumnOrdered);
    }
    
    public static KWindowDefinitionOrdered getInstance(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        return new KWindowDefinitionOrdered(sb, name, kColumn);
    }
    
    public static KWindowDefinitionOrdered getInstance(
        final StringBuilder sb,
        final String name,
        final KColumnOrdered kColumnOrdered
    ) {
        return new KWindowDefinitionOrdered(sb, name, kColumnOrdered);
    }
    
    public KWindowDefinitionFrameNoStarted range() {
        return KWindowDefinitionFrameNoStarted.getInstance(sb, name, "RANGE", true);
    }
    
    public KWindowDefinitionFrameNoStarted rows() {
        return KWindowDefinitionFrameNoStarted.getInstance(sb, name, "ROWS", true);
    }
    
    public KWindowDefinitionFrameNoStarted groups() {
        return KWindowDefinitionFrameNoStarted.getInstance(sb, name, "GROUPS", true);
    }

    private void process(
        final KColumn kColumn
    ) {
        if (kColumn == null) {
            throw KExceptionHelper.internalServerError("The 'kColumn' param is required"); 
        }
        
        if (!kColumn.params.isEmpty()) {
            throw KExceptionHelper.internalServerError("Params in 'kColumn' are not allowed"); 
        }
        
        if (this.sb.length() > 0) {
            this.sb.append(" ");
        }
        
        this.sb.append("ORDER BY ").append(kColumn.toSql());
    }
    
    private void process(
        final KColumnOrdered kColumnOrdered
    ) {
        if (kColumnOrdered == null) {
            throw KExceptionHelper.internalServerError("The 'kColumnOrdered' param is required"); 
        }
        
        if (!kColumnOrdered.params.isEmpty()) {
            throw KExceptionHelper.internalServerError("Params in 'kColumnOrdered' are not allowed"); 
        }
        
        if (this.sb.length() > 0) {
            this.sb.append(" ");
        }
        
        this.sb.append("ORDER BY ").append(kColumnOrdered.toSql());
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSql() {
        return this.sb.toString();
    }
}
