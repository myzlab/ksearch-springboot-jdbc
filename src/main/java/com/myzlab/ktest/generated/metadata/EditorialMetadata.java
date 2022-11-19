package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KTable;

public class EditorialMetadata extends KTable {
    
    private static EditorialMetadata instance = null;
    
    private EditorialMetadata() {
        super("public", "editorial", "ed");
    }

    public static EditorialMetadata getInstance() {
        if (instance != null) {
            return instance;
        }
        
        instance = new EditorialMetadata();
        
        return instance;
    }
    
    public final KColumn ID = new KColumn(this, "id", Long.class);
    public final KColumn NAME = new KColumn(this, "name", String.class);
}