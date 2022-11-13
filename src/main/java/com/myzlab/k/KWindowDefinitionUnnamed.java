package com.myzlab.k;

public class KWindowDefinitionUnnamed extends KWindowDefinition {

    private KWindowDefinitionUnnamed() {
        super();
    }
    
    public static KWindowDefinitionUnnamed getInstance() {
        return new KWindowDefinitionUnnamed();
    }
    
    public KWindowDefinitionNamed name(
        final String name
    ) {
        return KWindowDefinitionNamed.getInstance(sb, name);
    }
    
    public KWindowDefinitionPartitioned partitionBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionPartitioned.getInstance(sb, null, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, null, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumnOrdered kColumnOrdered
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, name, kColumnOrdered);
    }
}
