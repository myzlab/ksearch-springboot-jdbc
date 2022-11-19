package com.myzlab.k;

import com.myzlab.k.helper.KExceptionHelper;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
    
    public Map<String, Object> single() {
        System.out.println(kQueryData.sb.toString());
        System.out.println(kQueryData.params);
        
        if (this.k == null || this.k.getJdbcTemplates() == null) {
            return null;
        }
        
        final List<Object> params = new ArrayList<>();
        
        for (final Object param : this.kQueryData.params) {
            params.add(param);
        }
        
        final Object[] p = params.toArray();
        
        return k.getJdbcTemplates().get(k.getJdbcTemplateDefaultName()).queryForMap(this.kQueryData.sb.toString(), p);
    }
    
    public <T extends KMapper> List<T> multiple(
        final Class<T> clazz
    ) {
//        System.out.println(this.kQueryData.sb.toString());
//        System.out.println(this.kQueryData.params);
//        System.out.println(this.kQueryData.kBaseColums);
            
        if (k == null || k.getJdbcTemplates() == null) {
            return null;
        }
        
        final List<String[]> ways = this.getWays(clazz);

        final List<T> list = k.getJdbcTemplates().get(   
            k.getJdbcTemplateDefaultName()
        ).query(this.kQueryData.sb.toString(), this.getParams(), this.getArgTypes(), (rs, rowNum) -> {
            return this.mapObject(rs, ways, clazz);
        });
        
        return list;
    }
    
    private <T extends KMapper> void mapColumn(
        final T t,
        final Object v,
        final String[] way,
        final KBaseColumn kBaseColumn
    ) {
        if (way == null) {
            return;
        }
        
        final KColumn kColumn = ((KColumn) kBaseColumn);
        
        Object current = t;

        for (final String w : way) {
            if (w.equals("*")) {
                try {
                    final Method methodSet = current.getClass().getMethod(
                        kColumn.getSetMethodName(),
                        kColumn.type
                    );

                    methodSet.invoke(current, v);
                } catch (Exception e) {
                    throw KExceptionHelper.internalServerError(e.getMessage());
                }
            } else {
                try {
                    final Method methodGet = current.getClass().getMethod(
                        "get" + String.valueOf(w.charAt(0)).toUpperCase() + w.substring(1)
                    );

                    Object internalObject = methodGet.invoke(current);

                    if (internalObject == null) {
                        try {
                            internalObject = methodGet.getReturnType().newInstance();

                            final Method methodSet = current.getClass().getMethod(
                                "set" + String.valueOf(w.charAt(0)).toUpperCase() + w.substring(1),
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
    
    private <T extends KMapper> T mapObject(
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

        for (int i = 0; i < this.kQueryData.kBaseColums.size(); i++) {
            this.mapColumn(
                t,
                resultSet.getObject(i + 1),
                ways.get(i),
                this.kQueryData.kBaseColums.get(i)
            );
        }

        return t;
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
    
    private <T extends KMapper> List<String[]> getWays(
        final Class<T> clazz
    ) {
        final T o;
        
        try {
            o = (T) clazz.newInstance();  
        } catch (Exception e) {
            throw KExceptionHelper.internalServerError(e.getMessage());
        }
        
        final List<String[]> ways = new ArrayList<>();
        
        for (int i = 0; i < this.kQueryData.kBaseColums.size(); i++) {
            
            final KBaseColumn kBaseColum = this.kQueryData.kBaseColums.get(i);
            
            if (!(kBaseColum instanceof KColumn)) {
                ways.add(null);
                
                continue;
            }
                    
            final KColumn kColumn = (KColumn) kBaseColum;
                
            if (kColumn.kTable == null) {
                ways.add(null);
                
                continue;
            }
            
            final String[] way = o.getWay(kColumn.kTable.toSql(false));

            ways.add(way);
        }
        
        return ways;
    }
    
//    public <T extends KMapper> List<Map<String, Object>> multiple(
//        final Class<T> clazz
//    ) {
//        System.out.println(this.kQueryData.sb.toString());
//        System.out.println(this.kQueryData.params);
//        System.out.println(this.kQueryData.kBaseColums);
//        
//        if (k == null || k.getJdbcTemplates() == null) {
//            return null;
//        }
//        
//        final List<Object> params = new ArrayList<>();
//        
//        for (final Object param : this.kQueryData.params) {
//            params.add(param);
//        }
//        
//        final Object[] p = params.toArray();
//        
//        final List<Map<String, Object>> r = k.getJdbcTemplates().get(k.getJdbcTemplateDefaultName()).queryForList(this.kQueryData.sb.toString(), p);
//        
//        return r;
//    }
}
