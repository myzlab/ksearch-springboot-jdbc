package com.myzlab.k.functions;

import com.myzlab.k.KQuery;

@FunctionalInterface
public interface KFindOneFunction<KFrom, T extends KQuery> {
    T run(KFrom kFrom);
}
