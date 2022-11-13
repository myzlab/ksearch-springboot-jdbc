package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;

public class KCombining {

    public KUnion union() {
        return new KUnion();
    }
    
    public KIntersect intersect() {
        return new KIntersect();
    }
    
    public KExcept except() {
        return new KExcept();
    }
    
//    public KOrderBy orderBy(
//        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
//    ) {
//        return KOrderBy.getInstance(kQueryData, kColumnsAllowedToOrderBy);
//    }
    
    public KLimit limit() {
        return new KLimit();
    }
    
    public KOffset offset() {
        return new KOffset();
    }
    
    public KFetch fetch() {
        return new KFetch();
    }
}
