package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;

public class KAliasedColumn extends KBaseColumnUncastable {
    
    private KAliasedColumn() {
        super();
    }
    
    protected KAliasedColumn(
        final StringBuilder sb,
        final List<Object> params,
        final int operating,
        final boolean closed
    ) {
        super(sb, params, operating, closed);
    }
    
    protected KAliasedColumn(
        final KBaseColumnCastable kBaseColumnCastable,
        final String alias
    ) {
        super(kBaseColumnCastable.sb, kBaseColumnCastable.params, kBaseColumnCastable.operating, false);
        
        if (alias == null) {
            throw KExceptionHelper.internalServerError("The 'alias' param is required");
        }
        
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
        return new KAliasedColumn(this.sb, new ArrayList<>(this.params), this.operating, this.closed);
    }
}
