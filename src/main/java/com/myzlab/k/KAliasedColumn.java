package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;

public class KAliasedColumn extends KBaseColumnUncastable {
    
    private KAliasedColumn() {
        super();
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
        try {
            return (KAliasedColumn) super.clone();
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError("An error has occurred while cloning KAliasedColumn object");
        }
    }
}
