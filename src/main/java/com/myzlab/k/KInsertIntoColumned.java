package com.myzlab.k;

public class KInsertIntoColumned extends KQueryInsertInto {

    private KInsertIntoColumned(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
 
    private KInsertIntoColumned(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KColumn... kColumns
    ) {
        super(kQueryInsertIntoData, kInitializer);
        
        this.process(kColumns);
    }
    
    protected static KInsertIntoColumned getInstance(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KColumn... kColumns
    ) {
        return new KInsertIntoColumned(kInitializer, kQueryInsertIntoData, kColumns);
    }
    
    public KInsertIntoFilled values(
        final KValues kValues
    ) {
        return KInsertIntoFilled.getInstance(this.k, this.kQueryInsertIntoData, kValues);
    }
    
    private void process(
        final KColumn... kColumns
    ) {
        KUtils.assertNotNullNotEmpty(kColumns, "kColumns", false);
            
        this.kQueryInsertIntoData.sb.append(" (");
        

        for (int i = 0; i < kColumns.length; i++) {
            final KColumn kColumn = kColumns[i];
                    
            if (i > 0) {
                this.kQueryInsertIntoData.sb.append(", ");
            }

            this.kQueryInsertIntoData.sb.append(kColumn.name);
        }

        this.kQueryInsertIntoData.sb.append(")");
    }
    
}

