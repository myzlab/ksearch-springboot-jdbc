package com.myzlab.k;

public class KWindowDefinitionFrameNoStarted extends KWindowDefinition {
    
    private KWindowDefinitionFrameNoStarted(
        final StringBuilder sb,
        final String name,
        final String method,
        final boolean whiteSpace
    ) {
        super(sb, name);
        
        this.process(method, whiteSpace);
    }
    
    public static KWindowDefinitionFrameNoStarted getInstance(
        final StringBuilder sb,
        final String name,
        final String method,
        final boolean whiteSpace
    ) {
        return new KWindowDefinitionFrameNoStarted(sb, name, method, whiteSpace);
    }
    
    public KWindowDefinitionFrameStarted unboundedPreceding() {
        return KWindowDefinitionFrameStarted.getInstance(sb, name, "UNBOUNDED PRECEDING");
    }
    
    public KWindowDefinitionFrameStarted preceding(
        int offset
    ) {
        return KWindowDefinitionFrameStarted.getInstance(sb, name, offset + " PRECEDING");
    }
    
    public KWindowDefinitionFrameStarted currentRow() {
        return KWindowDefinitionFrameStarted.getInstance(sb, name, "CURRENT ROW");
    }
    
    public KWindowDefinitionFrameStarted following(
        int offset
    ) {
        return KWindowDefinitionFrameStarted.getInstance(sb, name, offset + " FOLLOWING");
    }

    private void process(
        final String method,
        final boolean whiteSpace
    ) {
        KUtils.assertNotNull(method, "method");
        
        this.sb.append(whiteSpace ? " " : "").append(method);
    }
    
}
