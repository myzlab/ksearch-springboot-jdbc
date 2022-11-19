package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public class KAliasedColumn extends KBaseColumnUncastable {
    
    protected String alias;
    
    private KAliasedColumn() {
        super();
    }
    
    protected KAliasedColumn(
        final StringBuilder sb,
        final String alias,
        final List<Object> params,
        final boolean closed,
        final String name,
        final Class type,
        final KTable kTable
    ) {
        super(sb, params, closed, name, type, kTable);
        
        this.alias = alias;
    }
    
    protected KAliasedColumn(
        final KBaseColumnCastable kBaseColumnCastable,
        final String alias
    ) {
        super(kBaseColumnCastable.sb, kBaseColumnCastable.params, false, kBaseColumnCastable.name, kBaseColumnCastable.type, kBaseColumnCastable.kTable);
        
        if (alias == null) {
            throw KExceptionHelper.internalServerError("The 'alias' param is required");
        }
        
        this.alias = alias;
        
        this.process(alias);
    }
    
    private void process(
        final String alias
    ) {
        this.sb.append(" AS ");
        this.sb.append(alias);
    }
    
    @Override
    protected KAliasedColumn cloneMe() {
        return new KAliasedColumn(this.sb, this.alias, new ArrayList<>(this.params), this.closed, this.name, this.type, this.kTable);
    }
}
