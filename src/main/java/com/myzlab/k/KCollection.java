package com.myzlab.k;

import java.util.List;

public class KCollection<T extends KRow> {

    final List<T> list;

    public KCollection(
        final List<T> list
    ) {
        super();
        
        this.list = list;
    }
    
    public List<T> getList() {
        return this.list;
    }
}
