package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToOver;
import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;
import com.myzlab.k.helper.KExceptionHelper;

public class KWindowDefinitionNamedPartitioned extends KWindowDefinition implements KWindowDefinitionAllowedToWindow, KWindowDefinitionAllowedToOver {
    
    private KWindowDefinitionNamedPartitioned(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        super(sb, name);
        
        this.process(kColumn);
    }
    
    public static KWindowDefinitionNamedPartitioned getInstance(
        final StringBuilder sb,
        final String name,
        final KColumn kColumn
    ) {
        return new KWindowDefinitionNamedPartitioned(sb, name, kColumn);
    }
    
    public KWindowDefinitionNamedOrdered orderBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionNamedOrdered.getInstance(sb, name, kColumn);
    }
    
    public KWindowDefinitionNamedOrdered orderBy(
        final KColumnOrdered kColumnOrdered
    ) {
        return KWindowDefinitionNamedOrdered.getInstance(sb, name, kColumnOrdered);
    }
    
    public KWindowDefinitionNamedFrameNoStarted range() {
        return KWindowDefinitionNamedFrameNoStarted.getInstance(sb, name, "RANGE", true);
    }
    
    public KWindowDefinitionNamedFrameNoStarted rows() {
        return KWindowDefinitionNamedFrameNoStarted.getInstance(sb, name, "ROWS", true);
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