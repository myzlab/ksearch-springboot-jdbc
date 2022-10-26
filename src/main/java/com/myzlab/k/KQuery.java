package com.myzlab.k;

public abstract class KQuery {

    protected final StringBuilder sb = new StringBuilder();
    
    public void single() {
        System.out.println(sb.toString());
    }
}
