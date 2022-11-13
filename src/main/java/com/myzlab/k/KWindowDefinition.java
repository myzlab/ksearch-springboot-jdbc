package com.myzlab.k;

public class KWindowDefinition {
    
    protected final StringBuilder sb;
    
    protected String name;
    
    protected KWindowDefinition() {
        this.sb = new StringBuilder();
    }

    protected KWindowDefinition(
        final String name
    ) {
        this();
        
        this.name = name;
    }
    
    protected KWindowDefinition(
        final StringBuilder sb,
        final String name
    ) {
        this(name);
        
        this.sb.append(sb);
    }
}
