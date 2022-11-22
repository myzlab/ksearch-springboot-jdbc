package com.myzlab.k;

public class KCommonTableExpressionFilled extends KCommonTableExpression {

    private KCommonTableExpressionFilled() {
        super();
    }
    
    protected KCommonTableExpressionFilled(
        final KValues kValues,
        final String name,
        final String... columns
    ) {
        super(kValues, name, columns);
    }
        
    public static KCommonTableExpressionFilled getInstance(
        final KValues kValues,
        final String name,
        final String... columns
    ) {
        return new KCommonTableExpressionFilled(kValues, name, columns);
    }
    
    public KCommonTableExpressionAliased as(
        final String alias
    ) {
        return KCommonTableExpressionAliased.getInstance(kValues, this.name, alias, this.columns);
    }
}
