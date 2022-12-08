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
import org.springframework.jdbc.core.JdbcTemplate;
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
    
    private static List<KPath> getNewPathsByAliasSource(
        final List<KPath> kPaths,
        final String aliasSource,
        final String aliasTarget,
        final String path
    ) {
        final List<KPath> newKPaths = new ArrayList<>();
        
        for (final KPath kPath : kPaths) {
            if (aliasSource.equals(kPath.aliasTarget)) {
                newKPaths.add(KPath.getInstance(kPath.aliasSource, aliasTarget, kPath.path + "," + path));
            }
        }
        
        return newKPaths;
    }
    
    private static List<KPath> getNewPathsByAliasTarget(
        final List<KPath> kPaths,
        final String aliasSource,
        final String aliasTarget,
        final String path
    ) {
        final List<KPath> newKPaths = new ArrayList<>();
        
        for (final KPath kPath : kPaths) {
            if (kPath.aliasSource.equals(aliasTarget)) {
                newKPaths.add(KPath.getInstance(aliasSource, kPath.aliasTarget, path + "," + kPath.path));
            }
        }
        
        return newKPaths;
    }
    
    private static <T extends KRow> String getCurrentAlias(
        final List<KNode> kNodes,
        final Class<T> clazz
    ) {
        for (final KNode kNode : kNodes) {
            if (kNode.source.equals(clazz)) {
                return kNode.aliasSource;
            }
        }
        
        return null;
    }
    
    protected static <T extends KRow> List<String[]> getPaths(
        final KQueryGenericData kQueryGenericData,
        final Class<T> clazz
    ) {
        
        if (clazz.equals(KRow.class)) {
            return new ArrayList<>();
        }
        
        final List<KPath> kPaths = new ArrayList<>();
        
        for (final KEdge kEdge : kQueryGenericData.kEdges) {
            final KNode kNodeSource = KNode.getInstance(kEdge.source, kEdge.aliasSource);
            final KNode kNodeTarget = KNode.getInstance(kEdge.target, kEdge.aliasTarget);
            
            if (!kQueryGenericData.kNodes.contains(kNodeSource)) {
                kQueryGenericData.kNodes.add(kNodeSource);
            }
            
            if (!kQueryGenericData.kNodes.contains(kNodeTarget)) {
                kQueryGenericData.kNodes.add(kNodeTarget);
            }
            
            final List<KPath> newKPaths = new ArrayList<>();
            
            newKPaths.add(KPath.getInstance(kEdge.aliasSource, kEdge.aliasTarget, kEdge.path));
            
            newKPaths.addAll(getNewPathsByAliasSource(kPaths, kEdge.aliasSource, kEdge.aliasTarget, kEdge.path));
            newKPaths.addAll(getNewPathsByAliasTarget(kPaths, kEdge.aliasSource, kEdge.aliasTarget, kEdge.path));
            
            kPaths.addAll(newKPaths);
        }
        
        final String currentAlias = getCurrentAlias(kQueryGenericData.kNodes, clazz);
        
        final List<String[]> paths = new ArrayList<>();
        
        if (currentAlias == null) {
            return paths;
        }
        
        final Map<String, String[]> allPaths = new HashMap<>();
        
        allPaths.put(currentAlias, "*".split(","));
        
        for (final KPath kPath : kPaths) {
            if (kPath.aliasSource.equals(currentAlias)) {
                allPaths.put(kPath.aliasTarget, (kPath.path + ",*").split(","));
            }
        }
        
        for (int i = 0; i < kQueryGenericData.kBaseColumns.size(); i++) {
            final KBaseColumn kBaseColumn = kQueryGenericData.kBaseColumns.get(i);
            
            if (kBaseColumn.name == null || kBaseColumn.kTable == null || kBaseColumn.type == null || kBaseColumn.kTable.alias == null) {
                paths.add(null);
                
                continue;
            }
            
            paths.add(allPaths.get(kBaseColumn.kTable.alias));
        }
        
        return paths;
    }
    
    protected static <T extends KRow> void mapColumn(
        final T t,
        final Object v,
        final String[] path,
        final KBaseColumn kBaseColumn
    ) {
        if (path == null) {
            return;
        }
        
        Object current = t;

        for (final String w : path) {
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
        final List<String[]> paths,
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
                paths.isEmpty() ? null : paths.get(i),
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
        final KExecutor k,
        final KQueryGenericData kQueryGenericData,
        final Class<T> clazz
    ) {
        System.out.println(kQueryGenericData.sb.toString());
        System.out.println(kQueryGenericData.params);
//        System.out.println(kQueryGenericData.kEdges);
//        System.out.println(kQueryGenericData.kNodes);
        
        if (k == null || k.getJdbc() == null) {
             System.err.println("JDBC no provided to KSearch!");
            
            return null;
        }
        
        final List<String[]> paths = KQueryUtils.getPaths(kQueryGenericData, clazz);

        final List<T> list = 
            k.getJdbc().query(kQueryGenericData.sb.toString(), getParams(kQueryGenericData), getArgTypes(kQueryGenericData), (final ResultSet resultSet, final int rowNum) -> {
                return mapObject(kQueryGenericData, resultSet, paths, clazz);
            });
        
        return new KCollection<>(list);
    }
    
    protected static int executeSingle(
        final KExecutor k,
        final KQueryGenericData kQueryGenericData
    ) {
        System.out.println(kQueryGenericData.sb.toString());
        System.out.println(kQueryGenericData.params);
        
        if (k == null || k.getJdbc() == null) {
            System.err.println("JDBC no provided to KSearch!");
            
            return -1;
        }
        
        return k.getJdbc().update(kQueryGenericData.sb.toString(), KQueryUtils.getParams(kQueryGenericData), KQueryUtils.getArgTypes(kQueryGenericData));
    }
}
