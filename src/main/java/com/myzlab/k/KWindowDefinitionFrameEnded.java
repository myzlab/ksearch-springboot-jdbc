package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;

public class KWindowDefinitionFrameEnded extends KWindowDefinition implements KWindowDefinitionAllowedToWindow {
    
    private KWindowDefinitionFrameEnded(
        final StringBuilder sb,
        final String name,
        final String frameStart,
        final String frameEnd
    ) {
        super(sb, name);
        
        this.process(frameStart, frameEnd);
    }
    
    public static KWindowDefinitionFrameEnded getInstance(
        final StringBuilder sb,
        final String name,
        final String frameStart,
        final String frameEnd
    ) {
        return new KWindowDefinitionFrameEnded(sb, name, frameStart, frameEnd);
    }

    private void process(
        final String frameStart,
        final String frameEnd
    ) {
        KUtils.assertNotNull(frameStart, "frameStart");
        KUtils.assertNotNull(frameEnd, "frameEnd");
        
        this.sb.insert(this.sb.lastIndexOf(frameStart), "BETWEEN ");
        
        this.sb.append(" AND ").append(frameEnd);
    }
    
    public KWindowDefinitionFrameExcluded excludeCurrentRow() {
        return KWindowDefinitionFrameExcluded.getInstance(sb, name, "CURRENT ROW");
    }
    
    public KWindowDefinitionFrameExcluded excludeGroup() {
        return KWindowDefinitionFrameExcluded.getInstance(sb, name, "GROUP");
    }
    
    public KWindowDefinitionFrameExcluded excludeTies() {
        return KWindowDefinitionFrameExcluded.getInstance(sb, name, "TIES");
    }
    
    public KWindowDefinitionFrameExcluded excludeNoOthers() {
        return KWindowDefinitionFrameExcluded.getInstance(sb, name, "NO OTHERS");
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
