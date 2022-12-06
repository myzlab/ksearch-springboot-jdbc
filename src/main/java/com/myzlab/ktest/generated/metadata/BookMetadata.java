package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KJoinDefinition;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;

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
    
    public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
    public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
    public final KTableColumn TITLE = new KTableColumn(this, "title", String.class);
    public final KTableColumn DESCRIPTION = new KTableColumn(this, "description", String.class);
    public final KTableColumn PAGES = new KTableColumn(this, "pages", Long.class);
    public final KTableColumn DATA = new KTableColumn(this, "data", String.class);
    public final KTableColumn AUTHOR_ID = new KTableColumn(this, "author_id", Long.class);
    public final KTableColumn EDITORIAL_ID = new KTableColumn(this, "editorial_id", Long.class);
    
    public KJoinDefinition joinAuthor() {
        return AUTHOR.on(this.AUTHOR_ID.eq(AUTHOR.ID));
    }
    
    public KJoinDefinition joinEditorial() {
        return EDITORIAL.on(this.EDITORIAL_ID.eq(EDITORIAL.ID));
    }
}