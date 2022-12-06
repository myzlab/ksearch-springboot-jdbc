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

public class KQueryUtils {
    
    protected static void fillRef(
        final Map<String, Integer> ref,
        final KBaseColumn kBaseColumn,
        final int i
    ) {
        final String nameToRef;
            
        if (kBaseColumn instanceof KAliasedColumn) {
            nameToRef = ((KAliasedColumn) kBaseColumn).alias;
        } else if (kBaseColumn instanceof KTableColumn) {
            nameToRef = kBaseColumn.name;
        } else {
            nameToRef = KUtils.reverseParams(kBaseColumn);
        }
        
        if (ref.containsKey(nameToRef)) {
            throw KExceptionHelper.internalServerError("'Ref' value repeated. Please check values of 'SELECT' Clause. Repeated Value: ['" + nameToRef + "']");
        }

        ref.put(nameToRef, i);
    }
    
    protected static int[] getArgTypes(
        final KQueryGenericData kQueryGenericData
    ) {
        final int[] argTypes = new int[kQueryGenericData.params.size()];
        
        for (int i = 0; i < kQueryGenericData.params.size(); i++) {
            final Object param = kQueryGenericData.params.get(i);
            
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
    
    protected static Object[] getParams(
        final KQueryGenericData kQueryGenericData
    ) {
        final List<Object> params = new ArrayList<>();
        
        for (final Object param : kQueryGenericData.params) {
            params.add(param);
        }
        
        return params.toArray();
    }
    
    protected static <T extends KRow> List<String[]> getWays(
        final KQueryGenericData kQueryGenericData,
        final Class<T> clazz
    ) {
        final T o;
        
        try {
            o = (T) clazz.newInstance();  
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError(e.getMessage());
        }
        
        final List<String[]> ways = new ArrayList<>();
        
        for (int i = 0; i < kQueryGenericData.kBaseColumns.size(); i++) {
            
            final KBaseColumn kBaseColumn = kQueryGenericData.kBaseColumns.get(i);
            
            if (kBaseColumn.name == null || kBaseColumn.kTable == null || kBaseColumn.type == null) {
                ways.add(null);
                
                continue;
            }
            
            final String[] way = o.getWay(kBaseColumn.kTable.toSql(false));

            ways.add(way);
        }
        
        return ways;
    }
    
    protected static <T extends KRow> void mapColumn(
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
    
    protected static <T extends KRow> T mapObject(
        final KQueryGenericData kQueryGenericData,
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
        
        final Object[] o = new Object[kQueryGenericData.kBaseColumns.size()];
        final Map<String, Integer> ref = new HashMap<>();

        for (int i = 0; i < kQueryGenericData.kBaseColumns.size(); i++) {
            final Object v = resultSet.getObject(i + 1);
            final KBaseColumn kBaseColumn = kQueryGenericData.kBaseColumns.get(i);
            
            if (kBaseColumn == null) {
                throw KExceptionHelper.internalServerError("The 'kBaseColumn' is required"); 
            }
            
            KQueryUtils.mapColumn(
                t,
                v,
                ways.get(i),
                kBaseColumn
            );
            
            o[i] = v;
            KQueryUtils.fillRef(ref, kBaseColumn, i);
        }
        
        t.o = o;
        t.ref = ref;

        return t;
    }
    
    protected static <T extends KRow> KCollection<T> multipleMapping(
        final KInitializer k,
        final KQueryGenericData kQueryGenericData,
        final Class<T> clazz
    ) {
        System.out.println(kQueryGenericData.sb.toString());
        System.out.println(kQueryGenericData.params);
//        System.out.println(this.kQueryData.kBaseColums);
            
        if (k == null || k.getJdbcTemplates() == null) {
            System.out.println("JDBC no provided to KSearch!");
            
            return null;
        }
        
        final List<String[]> ways = KQueryUtils.getWays(kQueryGenericData, clazz);

        final List<T> list = k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).query(kQueryGenericData.sb.toString(), getParams(kQueryGenericData), getArgTypes(kQueryGenericData), (final ResultSet resultSet, final int rowNum) -> {
            return mapObject(kQueryGenericData, resultSet, ways, clazz);
        });
        
        return new KCollection<>(list);
    }
    
    protected static int executeSingle(
        final KInitializer k,
        final KQueryGenericData kQueryGenericData
    ) {
        System.out.println(kQueryGenericData.sb.toString());
        System.out.println(kQueryGenericData.params);
        
        return k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).update(kQueryGenericData.sb.toString(), KQueryUtils.getParams(kQueryGenericData), KQueryUtils.getArgTypes(kQueryGenericData));
    }
}
