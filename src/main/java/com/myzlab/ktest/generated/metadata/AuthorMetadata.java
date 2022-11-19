package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KTable;

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
    
    public final KColumn ID = new KColumn(this, "id", Long.class);
    public final KColumn NAME = new KColumn(this, "name", String.class);
    public final KColumn AGE = new KColumn(this, "age", Integer.class);

}