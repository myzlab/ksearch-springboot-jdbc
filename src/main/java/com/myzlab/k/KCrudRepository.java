package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.allowed.KColumnAllowedToSelect;
import static com.myzlab.k.KFunction.*;
import com.myzlab.k.helper.KExceptionHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public abstract class KCrudRepository<T extends KRow, Y> {

    public abstract KTable getMetadata();
    public abstract Class<T> getKRowClass();
    public abstract Class<?> getKMetadataClass();
    public abstract KTableColumn getKTableColumnId();
    public abstract KBuilder getK();
    
    public T findById(
        final Y id,
        final KColumnAllowedToSelect... selects
    ) {
        return findById(
            getK().getJdbcTemplateDefaultName(),
            id,
            selects
        );
    }
    
    public T findById(
        final String jdbc,
        final Y id,
        final KColumnAllowedToSelect... selects
    ) {
        return
            (T)
            getK()
            .jdbc(jdbc)
            .select(selects)
            .from(getMetadata())
            .where(getKTableColumnId().eq(id))
            .single(getKRowClass());
    }
    
    public KCollection<T> findAll(
        final KColumnAllowedToSelect... selects
    ) {
        return findAll(
            getK().getJdbcTemplateDefaultName(),
            selects
        );
    }
    
    public KCollection<T> findAll(
        final String jdbc,
        final KColumnAllowedToSelect... selects
    ) {
        return
            getK()
            .jdbc(jdbc)
            .select(selects)
            .from(getMetadata())
            .multiple(getKRowClass());
    }
    
    public int deleteById(
        final Y id
    ) {
        return deleteById(
            getK().getJdbcTemplateDefaultName(),
            id
        );
    }
    
    public int deleteById(
        final String jdbc,
        final Y id
    ) {
        return 
            getK()
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().eq(id))
            .execute();
    }
    
    public KCollection<T> deleteById(
        final Y id,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return deleteById(
            getK().getJdbcTemplateDefaultName(),
            id,
            kColumnsAllowedToReturning
        );
    }
    
    public KCollection<T> deleteById(
        final String jdbc,
        final Y id,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return 
            getK()
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().eq(id))
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
    }
    
    public boolean existsById(
        final Y id
    ) {
        return existsById(
            getK().getJdbcTemplateDefaultName(),
            id
        );
    }
    
    public boolean existsById(
        final String jdbc,
        final Y id
    ) {
        final KQuery subQuery =
            getK()
            .select1()
            .from(getMetadata())
            .where(getKTableColumnId().eq(id));

        return
            getK()
            .jdbc(jdbc)
            .select(exists(subQuery).as("GOD_BLESS_YOU"))
            .single()
            .getBoolean(0);
    }
    
    public void assertExistsById(
        final Y id,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertExistsById(
            getK().getJdbcTemplateDefaultName(),
            id,
            httpStatus,
            message
        );
    }
    
    public void assertExistsById(
        final String jdbc,
        final Y id,
        final HttpStatus httpStatus,
        final String message
    ) {
        final KQuery kQuery =
            getK()
            .select1()
            .from(getMetadata())
            .where(getKTableColumnId().eq(id));
        
        assertExists(getK(), jdbc, kQuery, httpStatus, message);
    }
    
    public void assertNotExistsById(
        final Y id,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertNotExistsById(
            getK().getJdbcTemplateDefaultName(),
            id,
            httpStatus,
            message
        );
    }
    
    public void assertNotExistsById(
        final String jdbc,
        final Y id,
        final HttpStatus httpStatus,
        final String message
    ) {
        final KQuery kQuery =
            getK()
            .select1()
            .from(getMetadata())
            .where(getKTableColumnId().eq(id));
        
        assertNotExists(getK(), jdbc, kQuery, httpStatus, message);
    }
    
    public long count() {
        return count(getK().getJdbcTemplateDefaultName());
    }
    
    public long count(
        final String jdbc
    ) {
        return count(jdbc, null);
    }
    
    public long count(
        final KColumn kColumn
    ) {
        return count(getK().getJdbcTemplateDefaultName(), kColumn);
    }
    
    public long count(
        final String jdbc,
        final KColumn kColumn
    ) {
        return
            getK()
            .jdbc(jdbc)
            .select(kColumn != null ? KFunction.count(kColumn) : KFunction.count())
            .from(getMetadata())
            .single(Long.class);
    }
    
    public long countDistinct(
        final KColumn kColumn
    ) {
        return countDistinct(getK().getJdbcTemplateDefaultName(), kColumn);
    }
    
    public long countDistinct(
        final String jdbc,
        final KColumn kColumn
    ) {
        return
            getK()
            .jdbc(jdbc)
            .select(KFunction.countDistinct(kColumn))
            .from(getMetadata())
            .single(Long.class);
    }
    
    public int insert(
        final T entity
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entity
            )
            .execute();
    }
    
    public T insert(
        final T entity,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) { 
        final KCollection<T> result =
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entity
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
        
        if (!result.isEmpty()) {
            return result.get(0);
        }
        
        return KQueryUtils.getKRowNull(getKRowClass());
    }
    
    public int insert(
        final List<T> entities
    ) { 
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entities
            )
            .execute();
    }
    
    public KCollection<T> insert(
        final List<T> entities,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entities
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
    }
    
    public int update(
        final T entity
    ) {
        if (entity.getPrimaryKeyValue() == null) {
            throw KExceptionHelper.internalServerError("Primary key value is required");
        }
        
        final List<KTableColumn> columns = new ArrayList<>();
        final List<Object> values = new ArrayList<>();
        
        KCrudRepositoryUtils.getColumnsAndValues(
            getKMetadataClass(),
            getMetadata(),
            getKRowClass(),
            entity.getDirtyProperties(),
            columns,
            values,
            entity,
            null,
            true
        );
        
        if (columns.isEmpty()) {
            throw KExceptionHelper.internalServerError("At least one column must be manipulated");
        }
        
        KSetUpdate kSetUpdate =
            getK()
            .update(getMetadata())
            .set(columns.get(0), values.get(0));
        
        for (int i = 1; i < columns.size(); i++) {
            kSetUpdate.set(columns.get(i), values.get(i));
        }
        
        return
            kSetUpdate
            .where(getKTableColumnId().eq(entity.getPrimaryKeyValue()))
            .execute();
    }
    
}
