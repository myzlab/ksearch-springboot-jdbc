package com.myzlab.k;

public class KAliasedColumn<T> extends KBaseColumn<T> {
    
    public KAliasedColumn(
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
