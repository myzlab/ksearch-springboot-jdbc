package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public abstract class KBaseColumn {

    protected final StringBuilder sb = new StringBuilder();
    protected final List<Object> params = new ArrayList<>();
    protected boolean closed = true;
    protected final String name;
    protected final Class type;
    protected final KTable kTable;
    
    protected KBaseColumn() {
        super();
        
        this.name = null;
        this.type = null;
        this.kTable = null;
    }
    
    protected KBaseColumn(
        final boolean closed
    ) {
        super();
        
        this.closed = closed;
        this.name = null;
        this.type = null;
        this.kTable = null;
    }
    
    protected KBaseColumn(
        final boolean closed,
        final String name,
        final Class type,
        final KTable kTable
    ) {
        super();
        
        this.closed = closed;
        this.name = name;
        this.type = type;
        this.kTable = kTable;
    }
    
    protected KBaseColumn(
        final StringBuilder sb,
        final List<Object> params,
        final boolean closed
    ) {
        this(closed);
        
        this.sb.append(sb);
        this.params.addAll(params);
    }
    
    protected KBaseColumn(
        final StringBuilder sb,
        final List<Object> params,
        final boolean closed,
        final String name,
        final Class type,
        final KTable kTable
    ) {
        this(closed, name, type, kTable);
        
        this.sb.append(sb);
        this.params.addAll(params);
    }
    
    protected KBaseColumn(
        final StringBuilder sb
    ) {
        this();
        
        this.sb.append(sb);
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
    }
 
    protected String toSql() {
        return sb.toString();
    }
    
    public String getSetMethodName() {
        if (name == null) {
            return null;
        }
        
        return "set" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
    }
    
    protected abstract KBaseColumn cloneMe();
}
