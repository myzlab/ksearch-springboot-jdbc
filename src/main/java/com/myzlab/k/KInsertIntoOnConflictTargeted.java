package com.myzlab.k;

public class KInsertIntoOnConflictTargeted extends KQueryInsertInto {

    private KInsertIntoOnConflictTargeted(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }
 
    private KInsertIntoOnConflictTargeted(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KColumn kColumn
    ) {
        super(kQueryInsertIntoData, kInitializer);
        
        KUtils.assertNotNull(kColumn, "kColumn");
        
        this.process(kColumn);
    }
    
    private KInsertIntoOnConflictTargeted(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final String constraint
    ) {
        super(kQueryInsertIntoData, kInitializer);
        
        KUtils.assertNotNull(constraint, "constraint");
        
        this.process(constraint);
    }
    
    protected static KInsertIntoOnConflictTargeted getInstanceOnColumn(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final KColumn kColumn
    ) {
        return new KInsertIntoOnConflictTargeted(kInitializer, kQueryInsertIntoData, kColumn);
    }
    
    protected static KInsertIntoOnConflictTargeted getInstanceOnConstraint(
        final KInitializer kInitializer,
        final KQueryInsertIntoData kQueryInsertIntoData,
        final String constraint
    ) {
        return new KInsertIntoOnConflictTargeted(kInitializer, kQueryInsertIntoData, constraint);
    }
    
    public KInsertIntoOnConflictDoNothing doNothing() {
        return KInsertIntoOnConflictDoNothing.getInstance(this.k, this.kQueryInsertIntoData);
    }
    
    public KInsertIntoOnConflictDoUpdate doUpdate() {
        return KInsertIntoOnConflictDoUpdate.getInstance(this.k, this.kQueryInsertIntoData);
    }

    private void process(
        final KColumn kColumn
    ) {
        this.kQueryInsertIntoData.sb.append(" (").append(kColumn.name).append(")");
    }
    
    private void process(
        final String constraint
    ) {
        this.kQueryInsertIntoData.sb.append(" ON CONSTRAINT ").append(constraint);
    }
}
