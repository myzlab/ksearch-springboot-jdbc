package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KWindowDefinitionPartitioned extends KWindowDefinition implements KWindowDefinitionAllowedToWindow {
    
    private KWindowDefinitionPartitioned(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        super(sb, name);
        
        this.process(kColumn);
    }
    
    public static KWindowDefinitionPartitioned getInstance(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        return new KWindowDefinitionPartitioned(sb, name, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, name, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumnOrdered kColumnOrdered
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, name, kColumnOrdered);
    }
    
    public KWindowDefinitionFrameNoStarted range() {
        return KWindowDefinitionFrameNoStarted.getInstance(sb, name, "RANGE", true);
    }
    
    public KWindowDefinitionFrameNoStarted rows() {
        return KWindowDefinitionFrameNoStarted.getInstance(sb, name, "ROWS", true);
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
        
        this.sb.append("PARTITION BY ").append(kColumn.toSql());
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
