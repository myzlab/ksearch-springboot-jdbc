package com.myzlab.k;

import java.util.List;

public class KWith extends KQuery {

    private KWith(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }

    public static KWith getInstance(
        final KInitializer kInitializer,
        final KCommonTableExpressionFilled kCommonTableExpressionFilled
    ) {
        final KWith kWith = new KWith(kInitializer);

        kWith.process(kCommonTableExpressionFilled);

        return kWith;
    }
    
    public KSelect select(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kBaseColumns);
    }
    
    public KSelect selectDistinct(
        final KBaseColumn... kBaseColumns
    ) {
        return KSelect.getDistinctInstance(this.k, this.kQueryData, kBaseColumns);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KValNumberField kValNumberField
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, kValNumberField);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final Number number
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, number);
    }

    private void process(
        final KCommonTableExpressionFilled kCommonTableExpressionFilled
    ) {
        KUtils.assertNotNull(kCommonTableExpressionFilled, "kCommonTableExpressionFilled");

        this.kQueryData.sb.append("WITH ").append(kCommonTableExpressionFilled.name);

        final String[] columns = kCommonTableExpressionFilled.columns;

        if (columns != null && columns.length > 0) {
            this.kQueryData.sb.append(" (");

            for (int i = 0; i < columns.length; i++) {
                if (i > 0) {
                    this.kQueryData.sb.append(", ");
                }

                this.kQueryData.sb.append(columns[i]);
            }

            this.kQueryData.sb.append(")");
        }

        this.kQueryData.sb.append(" AS ");

        if (kCommonTableExpressionFilled.kValues != null) {
            this.processValues(kCommonTableExpressionFilled.kValues.values);
            
            return;
        }
        
        //TODO SUBQUERY
    }
    
    private void processValues(
        final List<List<Object>> allValues
    ) {
        this.kQueryData.sb.append("(VALUES ");
            
        for (int i = 0; i < allValues.size(); i++) {
            final List<Object> values = allValues.get(i);
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }

            this.kQueryData.sb.append("(");

            for (int j = 0; j < values.size(); j++) {
                if (j > 0) {
                    this.kQueryData.sb.append(", ");
                }

                this.kQueryData.sb.append("?");
                this.kQueryData.params.add(values.get(j));
            }

            this.kQueryData.sb.append(")");
        }

        this.kQueryData.sb.append(")");
    } 
}