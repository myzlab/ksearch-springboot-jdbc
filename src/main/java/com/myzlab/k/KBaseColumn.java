package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public abstract class KBaseColumn {

    protected final StringBuilder sb;
    protected final List<Object> params;
    protected int operating = 0;
    protected boolean closed = true;
    
    protected KBaseColumn() {
        super();
        
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
    }
    
    protected KBaseColumn(
        final boolean closed
    ) {
        super();
        
        this.sb = new StringBuilder();
        this.params = new ArrayList<>();
        this.closed = closed;
    }
    
    protected KBaseColumn(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        this();
        
        this.sb.append(sb);
        this.params.addAll(params);
        this.operating += operating;
        this.closed = closed;
    }
    
    protected KBaseColumn(
        final StringBuilder sb
    ) {
        this();
        
        this.sb.append(sb);
        this.operating = 1;
    }
    
    protected KBaseColumn(
        final String val
    ) {
        this();
        
        if (val == null) {
            throw KExceptionHelper.internalServerError("The 'val' param cannot be null");
        }
        
        this.sb.append("?");
        this.params.add(val);
        this.operating  = 1;
    }
    
    protected KBaseColumn(
        final Number val
    ) {
        this();
        
        if (val == null) {
            throw KExceptionHelper.internalServerError("The 'val' param cannot be null");
        }
        
        this.sb.append("?");
        this.params.add(val);
        this.operating  = 1;
    }
 
    protected String toSql() {
        return sb.toString();
    }
    
    protected abstract KBaseColumn cloneMe();
}
