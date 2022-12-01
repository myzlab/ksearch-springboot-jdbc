package com.myzlab.k;

@FunctionalInterface
public interface KRowFunction<KRow, T> {
    T run(KRow kRow);
}
