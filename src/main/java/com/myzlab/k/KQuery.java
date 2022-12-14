package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class KQuery {
    
    protected KExecutor k;
    protected KQueryData kQueryData;
    protected final List<KSpecialFunction> kSpecialFunctions;

    protected KQuery() {
        this.kQueryData = new KQueryData();
        this.kSpecialFunctions = new ArrayList<>();
    }
    
    protected KQuery(
        final KExecutor kExecutor,
        final List<KSpecialFunction> kSpecialFunctions
    ) {
        this.k = kExecutor;
        this.kQueryData = new KQueryData();
        this.kSpecialFunctions = kSpecialFunctions;
    }
    
    public KQuery(
        final KQueryData kQueryData,
        final KExecutor kExecutor,
        final List<KSpecialFunction> kSpecialFunctions
    ) {
        this.k = kExecutor;
        this.kQueryData = kQueryData;
        this.kSpecialFunctions = kSpecialFunctions;
    }
    
    private <T extends KRow> T singleMappingKRow(
        final Class<T> clazz
    ) {
        final List<String[]> paths = KQueryUtils.getPaths(this.kQueryData, clazz);
        
        final T t = 
            k
            .getJdbc()
            .query(this.kQueryData.sb.toString(), KQueryUtils.getParams(this.kQueryData), KQueryUtils.getArgTypes(this.kQueryData), (final ResultSet resultSet) -> {
                if (resultSet == null || !resultSet.next()) {
                    return this.getKRowNull(clazz);
                }

                final T result = KQueryUtils.mapObject(this.kQueryData, resultSet, paths, clazz);

                if (resultSet.next()) {
                    return this.getKRowNull(clazz);
                }

                return result;
            });
        
        return t;
    }
    
    private <T> T singleMappingSingleType() {
        return 
            k
            .getJdbc()
            .query(this.kQueryData.sb.toString(), KQueryUtils.getParams(this.kQueryData), KQueryUtils.getArgTypes(this.kQueryData), (final ResultSet resultSet) -> {
                if (resultSet == null || !resultSet.next()) {
                    return null;
                }

                final T result = (T) resultSet.getObject(1);

                if (resultSet.next()) {
                    return null;
                }

                return result;
            });
    }
    
    public KRow single() {
        return this.single(KRow.class);
    }
    
    public <T> T single(
        final Class<T> clazz
    ) {
//        System.out.println(this.kQueryData.sb.toString());
//        System.out.println(this.kQueryData.params);
//        System.out.println(this.kQueryData.kBaseColums);
            
        if (k == null || k.getJdbc() == null) {
            System.err.println("JDBC no provided to KSearch!");
            
            return null;
        }
        
        if (clazz.getSuperclass().equals(KRow.class) || clazz.equals(KRow.class)) {
            return (T) this.singleMappingKRow((Class<? extends KRow>) clazz);
        }
        
        if (this.kQueryData.kBaseColumns.size() > 1) {
            throw KExceptionHelper.internalServerError("Only a single column is allowed in the 'SELECT clause for the requested mapping type");
        }
        
        return this.singleMappingSingleType();
    }
    
    public <T extends KRow> KCollection<T> multiple(
        final Class<T> clazz
    ) {
        return KQueryUtils.multipleMapping(this.k, this.kSpecialFunctions, this.kQueryData, clazz);
    }
    
    public KCollection<KRow> multiple() {
        return this.multiple(KRow.class);
    }
    
    
//    private KRow mapObject(
//        final ResultSet resultSet
//    ) throws SQLException {
//        final Object[] o = new Object[this.kQueryData.kBaseColumns.size()];
//        final Map<String, Integer> ref = new HashMap<>();
//
//        for (int i = 0; i < this.kQueryData.kBaseColumns.size(); i++) {
//            final Object v = resultSet.getObject(i + 1);
//            final KBaseColumn kBaseColumn = this.kQueryData.kBaseColumns.get(i);
//            
//            if (kBaseColumn == null) {
//                throw KExceptionHelper.internalServerError("The 'kBaseColumn' is required"); 
//            }
//            
//            o[i] = v;
//            KUtils.fillRef(ref, kBaseColumn, i);
//        }
//
//        return new KRow(o, ref);
//    }
    
    private <T extends KRow> T getKRowNull(
        final Class<T> clazz
    ) throws SQLException {
        
        final T t;
        
        try {
            t = (T) clazz.newInstance();  
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError(e.getMessage());
        }
        
        t.isNull = true;

        return t;
    }
    
    protected KQueryData generateSubQueryData() {
        return this.kQueryData.cloneMe();
    }
}
