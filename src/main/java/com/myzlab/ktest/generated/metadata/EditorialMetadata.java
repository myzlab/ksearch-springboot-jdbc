package com.myzlab.ktest.generated.metadata;

import com.myzlab.k.KColumn;
import com.myzlab.k.KTable;
import com.myzlab.k.KTableColumn;

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
    
    public final KTableColumn ID = new KTableColumn(this, "id", Long.class);
    public final KTableColumn NAME = new KTableColumn(this, "name", String.class);
}