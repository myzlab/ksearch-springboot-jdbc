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
    
//    public KLimit limit(
//        final int count
//    ) {
//        return KLimit.getInstance(kQueryData, count);
//    }
    
//    public KOffset offset(
//        final int start
//    ) {
//        return KOffset.getInstance(kQueryData, start);
//    }
//    
//    public KFetch fetch(
//        final int rowCount
//    ) {
//        return KFetch.getInstance(kQueryData, rowCount);
//    }
}
