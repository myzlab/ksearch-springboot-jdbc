package com.myzlab.k;

import com.myzlab.k.allowed.KWindowDefinitionAllowedToWindow;

public class KWindowDefinitionFrameStarted extends KWindowDefinition implements KWindowDefinitionAllowedToWindow {
    
    private final String frameStart;
    
    private KWindowDefinitionFrameStarted(
        final StringBuilder sb,
        final String name,
        final String frameStart
    ) {
        super(sb, name);
        
        this.frameStart = frameStart;
        
        this.process();
    }
    
    public static KWindowDefinitionFrameStarted getInstance(
        final StringBuilder sb,
        final String name,
        final String frameStart
    ) {
        return new KWindowDefinitionFrameStarted(sb, name, frameStart);
    }
    
    public KWindowDefinitionFrameEnded preceding(
        int offset
    ) {
        return KWindowDefinitionFrameEnded.getInstance(sb, name, frameStart, offset + " PRECEDING");
    }
    
    public KWindowDefinitionFrameEnded currentRow() {
        return KWindowDefinitionFrameEnded.getInstance(sb, name, frameStart, "CURRENT ROW");
    }
    
    public KWindowDefinitionFrameEnded following(
        int offset
    ) {
        return KWindowDefinitionFrameEnded.getInstance(sb, name, frameStart, offset + " FOLLOWING");
    }
    
    public KWindowDefinitionFrameEnded unboundedFollowing() {
        return KWindowDefinitionFrameEnded.getInstance(sb, name, frameStart, "UNBOUNDED FOLLOWING");
    }
    
    private void process() {
        KUtils.assertNotNull(this.frameStart, "frameStart");
        
        this.sb.append(" ").append(this.frameStart);
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
