package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToOrderBy;
import com.myzlab.k.allowed.KQueryAllowedToCombining;

public class KCombining extends KQuery {
    
    private KCombining(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KQueryAllowedToCombining kQueryAllowedToCombining,
        final String function,
        final boolean all
    ) {
        super(kQueryData, kExecutor);
        
        this.process(kQueryAllowedToCombining, function, all);
    }
    
    protected static KCombining getInstance(
        final KExecutor kExecutor,
        final KQueryData kQueryData,
        final KQueryAllowedToCombining kQueryAllowedToCombining,
        final String function,
        final boolean all
    ) {
        return new KCombining(kExecutor, kQueryData, kQueryAllowedToCombining, function, all);
    }

    public KCombining union(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", false);
    }
    
    public KCombining unionAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "UNION", true);
    }
    
    public KCombining intersect(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", false);
    }
    
    public KCombining intersectAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "INTERSECT", true);
    }
    
    public KCombining except(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", false);
    }
    
    public KCombining exceptAll(
        final KQueryAllowedToCombining kQueryAllowedToCombining
    ) {
        return KCombining.getInstance(this.k, this.kQueryData, kQueryAllowedToCombining, "EXCEPT", true);
    }
    
    public KOrderBy orderBy(
        final KColumnAllowedToOrderBy... kColumnsAllowedToOrderBy
    ) {
        return KOrderBy.getInstance(this.k, this.kQueryData, kColumnsAllowedToOrderBy);
    }
    
    public KLimit limit(
        final int count
    ) {
        return KLimit.getInstance(this.k, this.kQueryData, count);
    }
    
    public KOffset offset(
        final int start
    ) {
        return KOffset.getInstance(this.k, this.kQueryData, start);
    }
    
    public KFetch fetch(
        final int rowCount
    ) {
        return KFetch.getInstance(this.k, this.kQueryData, rowCount);
    }
    
    private void process(
        final KQueryAllowedToCombining kQueryAllowedToCombining,
        final String function,
        final boolean all
    ) {
        KUtils.assertNotNull(kQueryAllowedToCombining, "kQueryAllowedToCombining");
        
        final KQueryData subQuery = kQueryAllowedToCombining.generateSubQueryData();
        
        this.kQueryData.params.addAll(subQuery.params);
        
        this.kQueryData.sb.append(" ").append(function).append(all ? " ALL" : "").append(" (").append(subQuery.sb).append(")");
    }
}
