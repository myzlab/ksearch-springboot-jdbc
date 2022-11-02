package com.myzlab.k;

public abstract class KBaseColumn<T> {

    protected final StringBuilder sb = new StringBuilder();
 
    protected String toSql() {
        return sb.toString();
    }
}
