package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.SqlTypeValue;

public abstract class KQuery {
    
    protected KInitializer k;
    protected KQueryData kQueryData;

    protected KQuery() {
        this.kQueryData = new KQueryData();
    }
    
    protected KQuery(
        final KInitializer kInitializer
    ) {
        this.kQueryData = new KQueryData();
        this.k = kInitializer;
    }
    
    public KQuery(
        final KQueryData kQueryData,
        final KInitializer kInitializer
    ) {
        this.kQueryData = kQueryData;
        this.k = kInitializer;
    }
    
    private <T extends KRow> T singleMappingKRow(
        final Class<T> clazz
    ) {
        final List<String[]> ways = this.getWays(clazz);
        
        final T t = k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).query(this.kQueryData.sb.toString(), this.getParams(), this.getArgTypes(), (final ResultSet resultSet) -> {
            if (resultSet == null || !resultSet.next()) {
                return this.getKRowNull(clazz);
            }
            
            final T result = this.mapObject(resultSet, ways, clazz);
            
            if (resultSet.next()) {
                return this.getKRowNull(clazz);
            }
            
            return result;
        });
        
        return t;
    }
    
    private <T> T singleMappingSingleType() {
        return k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).query(this.kQueryData.sb.toString(), this.getParams(), this.getArgTypes(), (final ResultSet resultSet) -> {
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
        System.out.println(this.kQueryData.sb.toString());
        System.out.println(this.kQueryData.params);
//        System.out.println(this.kQueryData.kBaseColums);
            
        if (k == null || k.getJdbcTemplates() == null) {
            System.out.println("JDBC no provided to KSearch!");
            
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
        System.out.println(this.kQueryData.sb.toString());
        System.out.println(this.kQueryData.params);
//        System.out.println(this.kQueryData.kBaseColums);
            
        if (k == null || k.getJdbcTemplates() == null) {
            System.out.println("JDBC no provided to KSearch!");
            
            return null;
        }
        
        final List<String[]> ways = this.getWays(clazz);

        final List<T> list = k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).query(this.kQueryData.sb.toString(), this.getParams(), this.getArgTypes(), (final ResultSet rs, final int rowNum) -> {
            return this.mapObject(rs, ways, clazz);
        });
        
        return new KCollection<>(list);
    }
    
    public KCollection<KRow> multiple() {
        return this.multiple(KRow.class);
//        System.out.println(this.kQueryData.sb.toString());
//        System.out.println(this.kQueryData.params);
////        System.out.println(this.kQueryData.kBaseColums);
//            
//        if (k == null || k.getJdbcTemplates() == null) {
//            System.out.println("JDBC no provided to KSearch!");
//            
//            return null;
//        }
//
//        final List<KRow> list = k.getJdbcTemplates().get(   
//            k.getJdbcTemplateDefaultName()
//        ).query(this.kQueryData.sb.toString(), this.getParams(), this.getArgTypes(), (final ResultSet rs, final int rowNum) -> {
//            return this.mapObject(rs);
//        });
//        
//        return new KCollection<>(list);
    }
    
    private <T extends KRow> T mapObject(
        final ResultSet resultSet,
        final List<String[]> ways,
        final Class<T> clazz
    ) throws SQLException {
        
        final T t;
        
        try {
            t = (T) clazz.newInstance();  
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError(e.getMessage());
        }
        
        final Object[] o = new Object[this.kQueryData.kBaseColumns.size()];
        final Map<String, Integer> ref = new HashMap<>();

        for (int i = 0; i < this.kQueryData.kBaseColumns.size(); i++) {
            final Object v = resultSet.getObject(i + 1);
            final KBaseColumn kBaseColumn = this.kQueryData.kBaseColumns.get(i);
            
            if (kBaseColumn == null) {
                throw KExceptionHelper.internalServerError("The 'kBaseColumn' is required"); 
            }
            
            this.mapColumn(
                t,
                v,
                ways.get(i),
                kBaseColumn
            );
            
            o[i] = v;
            this.fillRef(ref, kBaseColumn, i);
        }
        
        t.o = o;
        t.ref = ref;

        return t;
    }
    
    private <T extends KRow> void mapColumn(
        final T t,
        final Object v,
        final String[] way,
        final KBaseColumn kBaseColumn
    ) {
        if (way == null) {
            return;
        }
        
        Object current = t;

        for (final String w : way) {
            if (w.equals("*")) {
                try {
                    final Method methodSet = current.getClass().getMethod(
                        KSearchNameHelper.generateSetName(kBaseColumn.name),
                        kBaseColumn.type
                    );

                    methodSet.invoke(current, v);
                } catch (Exception e) {
                    throw KExceptionHelper.internalServerError(e.getMessage());
                }
            } else {
                try {
                    final Method methodGet = current.getClass().getMethod(
                        KSearchNameHelper.generateGetName(w)
                    );

                    Object internalObject = methodGet.invoke(current);

                    if (internalObject == null) {
                        try {
                            internalObject = methodGet.getReturnType().newInstance();

                            final Method methodSet = current.getClass().getMethod(
                                KSearchNameHelper.generateSetName(w),
                                methodGet.getReturnType()
                            );

                            methodSet.invoke(current, internalObject);
                        } catch (Exception e) {
                            throw KExceptionHelper.internalServerError(e.getMessage());
                        }
                    }

                    current = internalObject;
                } catch (Exception e) {
                    throw KExceptionHelper.internalServerError(e.getMessage());
                }
            }
        }
    }
    
    private KRow mapObject(
        final ResultSet resultSet
    ) throws SQLException {
        final Object[] o = new Object[this.kQueryData.kBaseColumns.size()];
        final Map<String, Integer> ref = new HashMap<>();

        for (int i = 0; i < this.kQueryData.kBaseColumns.size(); i++) {
            final Object v = resultSet.getObject(i + 1);
            final KBaseColumn kBaseColumn = this.kQueryData.kBaseColumns.get(i);
            
            if (kBaseColumn == null) {
                throw KExceptionHelper.internalServerError("The 'kBaseColumn' is required"); 
            }
            
            o[i] = v;
            this.fillRef(ref, kBaseColumn, i);
        }

        return new KRow(o, ref);
    }
    
    private void fillRef(
        final Map<String, Integer> ref,
        final KBaseColumn kBaseColumn,
        final int i
    ) {
        final String nameToRef;
            
        if (kBaseColumn instanceof KAliasedColumn) {
            nameToRef = ((KAliasedColumn) kBaseColumn).alias;
        } else {
            nameToRef = KUtils.reverseParams(kBaseColumn);
        }
        
        if (ref.containsKey(nameToRef)) {
            throw KExceptionHelper.internalServerError("'Ref' value repeated. Please check values of 'SELECT' Clause. Repeated Value: ['" + nameToRef + "']");
        }

        ref.put(nameToRef, i);
    }
    
    private Object[] getParams() {
        final List<Object> params = new ArrayList<>();
        
        for (final Object param : this.kQueryData.params) {
            params.add(param);
        }
        
        return params.toArray();
    }
    
    private int[] getArgTypes() {
        final int[] argTypes = new int[this.kQueryData.params.size()];
        
        for (int i = 0; i < this.kQueryData.params.size(); i++) {
            final Object param = this.kQueryData.params.get(i);
            
            if (param instanceof String) {
                argTypes[i] = Types.VARCHAR;
            } else if (param instanceof Long) {
                argTypes[i] = Types.BIGINT;
            } else {
                argTypes[i] = SqlTypeValue.TYPE_UNKNOWN;
            }
        }
        
        return argTypes;
    }
    
    private <T extends KRow> List<String[]> getWays(
        final Class<T> clazz
    ) {
        final T o;
        
        try {
            o = (T) clazz.newInstance();  
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError(e.getMessage());
        }
        
        final List<String[]> ways = new ArrayList<>();
        
        for (int i = 0; i < this.kQueryData.kBaseColumns.size(); i++) {
            
            final KBaseColumn kBaseColumn = this.kQueryData.kBaseColumns.get(i);
            
            if (kBaseColumn.name == null || kBaseColumn.kTable == null || kBaseColumn.type == null) {
                ways.add(null);
                
                continue;
            }
            
            final String[] way = o.getWay(kBaseColumn.kTable.toSql(false));

            ways.add(way);
        }
        
        return ways;
    }
    
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
