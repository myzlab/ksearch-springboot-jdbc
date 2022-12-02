package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSetUpdate;

public class KSetUpdate extends KQueryUpdate {

    private KSetUpdate() {
        super();
    }
    
    private KSetUpdate(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KColumn kColumn,
        final KColumnAllowedToSetUpdate kColumnAllowedToSetUpdate
    ) {
        super(kQueryUpdateData, kInitializer);
        
        this.process(kColumn, kColumnAllowedToSetUpdate);
    }
    
    public static KSetUpdate getInstance(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KColumn kColumn,
        final KColumnAllowedToSetUpdate kColumnAllowedToSetUpdate
    ) {
        return new KSetUpdate(kInitializer, kQueryUpdateData, kColumn, kColumnAllowedToSetUpdate);
    }
    
    public KFromUpdate from(
        final KTable kTable
    ) {
        return KFromUpdate.getInstance(this.k, this.kQueryUpdateData, kTable);
    }
    
    public KFromUpdate from(
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        return KFromUpdate.getInstance(this.k, this.kQueryUpdateData, new KTable(null, kRaw.content, null));
    }
    
    public KSetUpdate set(
        final KColumn kColumn,
        final KColumnAllowedToSetUpdate kColumnAllowedToSetUpdate
    ) {
        this.process(kColumn, kColumnAllowedToSetUpdate);
        
        return this;
    }
    
    public KSetUpdate set(
        final KColumn kColumn,
        final KRaw kRaw
    ) {
        KUtils.assertNotNullNotEmpty(kRaw, "kRaw");
        
        this.process(kColumn, new KColumn(new StringBuilder(((KRaw) kRaw).content), false));
        
        return this;
    }
    
    public KWhereUpdate where(
        final KCondition kCondition
    ) {
        return KWhereUpdate.getInstance(this.k, this.kQueryUpdateData, kCondition);
    }
    
    public KWhereUpdate where(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kCondition = new KCondition(kRaw.content);
        
        return KWhereUpdate.getInstance(this.k, this.kQueryUpdateData, kCondition);
    }
    
    private void process(
        final KColumn kColumn,
        final KColumnAllowedToSetUpdate kColumnAllowedToSetUpdate
    ) {
        KUtils.assertNotNull(kColumn, "kColumn");
        KUtils.assertNotNull(kColumnAllowedToSetUpdate, "kColumnAllowedToSetUpdate");
        
        if (this.kQueryUpdateData.setValuesAdded == 0) {
            this.kQueryUpdateData.sb.append(" SET ");
        } else {
            this.kQueryUpdateData.sb.append(", ");
        }
        
        this.kQueryUpdateData.setValuesAdded++;
        
        this.kQueryUpdateData.params.addAll(kColumnAllowedToSetUpdate.getParams());
        this.kQueryUpdateData.sb.append(kColumn.name).append(" = ").append(kColumnAllowedToSetUpdate.getSqlToSet());
    }
    
}