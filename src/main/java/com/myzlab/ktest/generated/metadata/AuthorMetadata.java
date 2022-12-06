package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;

public class AuthorMetadata extends KTable {
    
    private static AuthorMetadata instance = null;
    
    private AuthorMetadata() {
        super("public", "author", "au");
    }

    public static AuthorMetadata getInstance() {
        if (instance != null) {
            return instance;
        }
        
        instance = new AuthorMetadata();
        
        return instance;
    }
    
    public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
    public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
    public final KTableColumn AGE = new KTableColumn(this, "age", Integer.class);

}