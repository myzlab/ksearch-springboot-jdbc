package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToSelect;
import java.util.ArrayList;
import java.util.List;

public class KWith extends KQuery {

    private KWith(
        final KInitializer kInitializer
    ) {
        super(kInitializer);
    }

    protected static KWith getInstance(
        final KInitializer kInitializer,
        final boolean recursive,
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        final KWith kWith = new KWith(kInitializer);

        kWith.process(recursive, kCommonTableExpressionsFilled);

        return kWith;
    }
    
    public KDeleteFrom deleteFrom(
        final KTable kTable
    ) {
        return KDeleteFrom.getInstance(
            this.k,
            new KQueryDeleteData(new StringBuilder(this.kQueryData.sb), new ArrayList<>(this.kQueryData.params), 0, new ArrayList()),
            kTable
        );
    }
    
    public KDeleteFrom deleteFrom(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KDeleteFrom.getInstance(
            this.k,
            new KQueryDeleteData(new StringBuilder(this.kQueryData.sb), new ArrayList<>(this.kQueryData.params), 0, new ArrayList()),
            new KTable(null, kRaw.content, null)
        );
    }
    
    public KInsertInto insertInto(
        final KTable kTable
    ) {
        return KInsertInto.getInstance(
            this.k,
            new KQueryInsertIntoData(new StringBuilder(this.kQueryData.sb), new ArrayList<>(this.kQueryData.params), 0, new ArrayList()),
            kTable
        );
    }
    
//    public KSelect select(
//        final KBaseColumn... kBaseColumns
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kBaseColumns);
//    }
//    
//    public KSelect select(
//        final KRaw... kRaws
//    ) {
//        return KSelect.getInstance(this.k, this.kQueryData, kRaws);
//    }
    
    public KSelect select(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kColumnsAllowedToSelect);
    }
    
    public KSelect select(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getInstance(this.k, this.kQueryData, kQuery, alias);
    }
    
//    public KSelect selectDistinct(
//        final KBaseColumn... kBaseColumns
//    ) {
//        return KSelect.getDistinctInstance(this.k, this.kQueryData, kBaseColumns);
//    }
    
    public KSelect selectDistinct(
        final KColumnAllowedToSelect... kColumnsAllowedToSelect
    ) {
        return KSelect.getDistinctInstance(this.k, this.kQueryData, kColumnsAllowedToSelect);
    }
    
    public KSelect selectDistinct(
        final KQuery kQuery,
        final String alias
    ) {
        return KSelect.getDistinctInstance(this.k, this.kQueryData, kQuery, alias);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KColumn kColumn
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, kColumn);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final int n
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, n);
    }
    
    public KDistinctOnSelect selectDistinctOn(
        final KRaw kRaw
    ) {
        return KDistinctOnSelect.getInstance(this.k, this.kQueryData, kRaw);
    }
    
    public KUpdate update(
        final KTable kTable
    ) {
        return KUpdate.getInstance(
            this.k,
            new KQueryUpdateData(new StringBuilder(this.kQueryData.sb), new ArrayList<>(this.kQueryData.params), 0, 0, new ArrayList()),
            kTable
        );
    }
    
    public KUpdate update(
        final KRaw kRaw
    ) {
        KUtils.assertNotNull(kRaw, "kRaw");
        
        return KUpdate.getInstance(
            this.k,
            new KQueryUpdateData(new StringBuilder(this.kQueryData.sb), new ArrayList<>(this.kQueryData.params), 0, 0, new ArrayList()),
            new KTable(null, kRaw.content, null)
        );
    }
    
    private void process(
        final boolean recursive,
        final KCommonTableExpressionFilled... kCommonTableExpressionsFilled
    ) {
        KUtils.assertNotNullNotEmpty(kCommonTableExpressionsFilled, "kCommonTableExpressionsFilled", false);
        
        this.kQueryData.sb.append("WITH ").append(recursive ? "RECURSIVE " : "");
        
        for (int i = 0; i < kCommonTableExpressionsFilled.length; i++) {
            final KCommonTableExpressionFilled kCommonTableExpressionFilled = kCommonTableExpressionsFilled[i];
            
            if (i > 0) {
                this.kQueryData.sb.append(", ");
            }
            
            this.kQueryData.sb.append(kCommonTableExpressionFilled.name);
            
            final String[] columns = kCommonTableExpressionFilled.columns;
            
            if (columns != null && columns.length > 0) {
                this.kQueryData.sb.append(" (");

                for (int j = 0; j < columns.length; j++) {
                    if (j > 0) {
                        this.kQueryData.sb.append(", ");
                    }

                    this.kQueryData.sb.append(columns[j]);
                }

                this.kQueryData.sb.append(")");
            }
            
            this.kQueryData.sb.append(" AS ");
            
            if (kCommonTableExpressionFilled.kValues != null) {
                this.processValues(kCommonTableExpressionFilled.kValues.values);

                return;
            }
            
            this.processKQuery(kCommonTableExpressionFilled.kQuery);
        }
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
                
                if (values.get(j) instanceof KRaw) {
                    this.kQueryData.sb.append(((KRaw) values.get(j)).content);
                } else {
                    this.kQueryData.sb.append("?");
                    this.kQueryData.params.add(values.get(j));
                }
                
            }

            this.kQueryData.sb.append(")");
        }

        this.kQueryData.sb.append(")");
    }
    
    private void processKQuery(
        final KQuery kQuery
    ) {
        final KQueryData subQuery = kQuery.generateSubQueryData();
        
        this.kQueryData.sb.append("(").append(subQuery.sb).append(")");
        
        this.kQueryData.params.addAll(subQuery.params);
    }
}