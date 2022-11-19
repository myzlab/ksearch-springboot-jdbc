package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KTable;

public class BookMetadata extends KTable {

    private static BookMetadata instance = null;

    private BookMetadata() {
        super("public", "book", "bo");
    }

    public static BookMetadata getInstance() {
        if (instance != null) {
            return instance;
        }
        
        instance = new BookMetadata();
        
        return instance;
    }
    
    public final AuthorMetadata AUTHOR = AuthorMetadata.getInstance();
    public final EditorialMetadata EDITORIAL = EditorialMetadata.getInstance();
    
    public final KColumn ID = new KColumn(this, "id", Long.class);
    public final KColumn NAME = new KColumn(this, "name", String.class);
    public final KColumn TITLE = new KColumn(this, "title", String.class);
    public final KColumn DESCRIPTION = new KColumn(this, "description", String.class);
    public final KColumn PAGES = new KColumn(this, "pages", Long.class);
    public final KColumn DATA = new KColumn(this, "data", String.class);
    public final KColumn AUTHOR_ID = new KColumn(this, "author_id", Long.class);
    public final KColumn EDITORIAL_ID = new KColumn(this, "editorial_id", Long.class);
    
    public KJoinDefinition joinAuthor() {
        return AUTHOR.on(this.AUTHOR_ID.eq(AUTHOR.ID));
    }
    
    public KJoinDefinition joinEditorial() {
        return EDITORIAL.on(this.EDITORIAL_ID.eq(EDITORIAL.ID));
    }
}