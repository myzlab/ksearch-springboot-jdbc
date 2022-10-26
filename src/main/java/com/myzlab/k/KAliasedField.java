package com.myzlab.k;

public class KAliasedField<T> extends KBaseField<T> {
    
    public KAliasedField(
        final StringBuilder sb,
        final String alias
    ) {
        this.sb.append(sb);
        this.process(alias);
    }
    
    private void process(
        final String alias
    ) {
        this.sb.append(" AS ");
        this.sb.append(alias);
    }
}
