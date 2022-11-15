package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;

public class KWindowDefinitionFrameExcluded extends KWindowDefinition implements KWindowDefinitionAllowedToWindow {
    
    private KWindowDefinitionFrameExcluded(
        final StringBuilder sb,
        final String name,
        final String excluding
    ) {
        super(sb, name);
        
        this.process(excluding);
    }
    
    public static KWindowDefinitionFrameExcluded getInstance(
        final StringBuilder sb,
        final String name,
        final String excluding
    ) {
        return new KWindowDefinitionFrameExcluded(sb, name, excluding);
    }

    private void process(
        final String excluding
    ) {
        KUtils.assertNotNull(excluding, "excluding");
        
        this.sb.append(" EXCLUDE ").append(excluding);
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
