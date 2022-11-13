package com.myzlab.k;

public class KWindowDefinitionNamed extends KWindowDefinition {
    
    private KWindowDefinitionNamed() {
        super();
    }

    private KWindowDefinitionNamed(
        final String name
    ) {
        super(name);
    }
    
    private KWindowDefinitionNamed(
        final StringBuilder sb,
        final String name
    ) {
        super(sb, name);
    }
    
    public static KWindowDefinitionNamed getInstance(
        final StringBuilder sb,
        final String name
    ) {
        return new KWindowDefinitionNamed(sb, name);
    }
    
    public static KWindowDefinitionNamed getInstance(
        final String name
    ) {
        return new KWindowDefinitionNamed(name);
    }
    
    public KWindowDefinitionPartitioned partitionBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionPartitioned.getInstance(sb, name, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumn kColumn
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, name, kColumn);
    }
    
    public KWindowDefinitionOrdered orderBy(
        final KColumnOrdered kColumnOrdered
    ) {
        return KWindowDefinitionOrdered.getInstance(sb, name, kColumnOrdered);
    }
}
