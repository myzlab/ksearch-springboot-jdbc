package com.myzlab.ktest.generated.metadata;

public class Tables {
    
    public static final AuthorMetadata AUTHOR;
    public static final EditorialMetadata EDITORIAL;
    public static final BookMetadata BOOK;
    
    static {
        AUTHOR = AuthorMetadata.getInstance();
        EDITORIAL = EditorialMetadata.getInstance();
        BOOK = BookMetadata.getInstance();
    }
}

