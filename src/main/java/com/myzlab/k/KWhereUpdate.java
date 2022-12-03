package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.helper.KExceptionHelper;

public class KWhereUpdate extends KQueryUpdate {
    
    private final KCondition kCondition;
    
    private KWhereUpdate() {
        super();
        
        this.kCondition = null;
    }
    
    private KWhereUpdate(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KCondition kCondition
    ) {
        super(kQueryUpdateData, kInitializer);
        
        KUtils.assertNotNull(kCondition, "kCondition");
        
        this.kCondition = kCondition;
    }
    
    public static KWhereUpdate getInstance(
        final KInitializer kInitializer,
        final KQueryUpdateData kQueryUpdateData,
        final KCondition kCondition
    ) {
        return new KWhereUpdate(kInitializer, kQueryUpdateData, kCondition);
    }
    
    public KWhereUpdate andNot(
        final KCondition kCondition
    ) {
        this.kCondition.andNot(kCondition);
        
        return this;
    }
    
    public KWhereUpdate andNot(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content);
        
        this.kCondition.andNot(kRawCondition);
        
        return this;
    }
    
    public KWhereUpdate orNot(
        final KCondition kCondition
    ) {
        this.kCondition.orNot(kCondition);
        
        return this;
    }
    
    public KWhereUpdate orNot(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content);
        
        this.kCondition.orNot(kRawCondition);
        
        return this;
    }

    public KWhereUpdate and(
        final KCondition kCondition
    ) {
        this.kCondition.and(kCondition);
        
        return this;
    }
    
    public KWhereUpdate and(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content);
        
        this.kCondition.and(kRawCondition);
        
        return this;
    }
    
    public KWhereUpdate or(
        final KCondition kCondition
    ) {
        this.kCondition.or(kCondition);
        
        return this;
    }
    
    public KWhereUpdate or(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        final KCondition kRawCondition = new KCondition(kRaw.content);
        
        this.kCondition.or(kRawCondition);
        
        return this;
    }
    
    public KReturningUpdate returning(
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        this.buildWhere();
        
        return KReturningUpdate.getInstance(this.k, this.kQueryUpdateData, kColumnsAllowedToReturning);
    }
    
    private void buildWhere() {
        this.buildWhere(this.kQueryUpdateData);
    }

    private void buildWhere(
        final KQueryUpdateData kQueryUpdateData
    ) {
        KUtils.assertNotNull(this.kCondition, "kCondition");
        
        if (this.kCondition.emptyCondition) {
            throw KExceptionHelper.internalServerError("'DELETE' sentence without conditions is not allowed");
        }
        
        kQueryUpdateData.sb.append(" WHERE ").append(this.kCondition.toSql());
        kQueryUpdateData.params.addAll(this.kCondition.params);
    }
    
    public int execute() {
        this.buildWhere();
        
        return super.executeSingle();
    }
}