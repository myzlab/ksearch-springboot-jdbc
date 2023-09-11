package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.allowed.KColumnAllowedToSelect;
import static com.myzlab.k.KFunction.*;
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
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().eq(id))
            .execute();
    }
    
    public T deleteById(
        final Y id,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return deleteById(
            getK().getJdbcTemplateDefaultName(),
            id,
            kColumnsAllowedToReturning
        );
    }
    
    public T deleteById(
        final String jdbc,
        final Y id,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return 
            getK()
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().eq(id))
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass())
            .getFirst();
    }
    
    public int deleteByIds(
        final List<Y> ids
    ) {
        return deleteByIds(
            getK().getJdbcTemplateDefaultName(),
            ids
        );
    }
    
    public int deleteByIds(
        final String jdbc,
        final List<Y> ids
    ) {
        return 
            getK()
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().in(ids))
            .execute();
    }
    
    public KCollection<T> deleteByIds(
        final List<Y> ids,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return deleteByIds(
            getK().getJdbcTemplateDefaultName(),
            ids,
            kColumnsAllowedToReturning
        );
    }
    
    public KCollection<T> deleteByIds(
        final String jdbc,
        final List<Y> ids,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return 
            getK()
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
            .where(getKTableColumnId().in(ids))
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
    }
    
    public int deleteAll() {
        return deleteAll(
            getK().getJdbcTemplateDefaultName()
        );
    }
    
    public int deleteAll(
        final String jdbc
    ) {
        return 
            getK()
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
            .execute();
    }
    
    public KCollection<T> deleteAll(
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return deleteAll(
            getK().getJdbcTemplateDefaultName(),
            kColumnsAllowedToReturning
        );
    }
    
    public KCollection<T> deleteAll(
        final String jdbc,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return 
            getK()
            .jdbc(jdbc)
            .deleteFrom(getMetadata())
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
        return insert(getK().getJdbcTemplateDefaultName(), entity);
    }
    
    public int insert(
        final String jdbc,
        final T entity
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                jdbc,
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
        return insert(getK().getJdbcTemplateDefaultName(), entity, kColumnsAllowedToReturning);
    }
    
    public T insert(
        final String jdbc,
        final T entity,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entity
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass())
            .getFirst();
    }
    
    public int insert(
        final List<T> entities
    ) {
        return insert(getK().getJdbcTemplateDefaultName(), entities);
    }
    
    public int insert(
        final String jdbc,
        final List<T> entities
    ) {
        KUtils.assertNotNullNotEmpty(entities, "entities", false);
        
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                jdbc,
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
        return insert(getK().getJdbcTemplateDefaultName(), entities, kColumnsAllowedToReturning);
    }
    
    public KCollection<T> insert(
        final String jdbc,
        final List<T> entities,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToInsert(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                entities
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
    }
    
    public int updateById(
        final T entity
    ) {
        return updateById(getK().getJdbcTemplateDefaultName(), entity);
    }
    
    public int updateById(
        final String jdbc,
        final T entity
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToUpdate(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                getKTableColumnId(),
                entity
            )
            .execute();
    }
    
    public T updateById(
        final T entity,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return updateById(getK().getJdbcTemplateDefaultName(), entity, kColumnsAllowedToReturning);
    }
    
    public T updateById(
        final String jdbc,
        final T entity,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToUpdate(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                getKTableColumnId(),
                entity
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass())
            .getFirst();
    }
    
    public int updateById(
        final List<T> entities
    ) {
        return updateById(getK().getJdbcTemplateDefaultName(), entities);
    }
    
    public int updateById(
        final String jdbc,
        final List<T> entities
    ) {
        KUtils.assertNotNullNotEmpty(entities, "entities", false);
        
        return
            KCrudRepositoryUtils.getKQueryBaseToUpdate(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                getKTableColumnId(),
                entities
            )
            .execute();
    }
    
    public KCollection<T> updateById(
        final List<T> entities,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return updateById(getK().getJdbcTemplateDefaultName(), entities, kColumnsAllowedToReturning);
    }
    
    public KCollection<T> updateById(
        final String jdbc,
        final List<T> entities,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return
            KCrudRepositoryUtils.getKQueryBaseToUpdate(
                jdbc,
                getK(),
                getKMetadataClass(),
                getMetadata(),
                getKRowClass(),
                getKTableColumnId(),
                entities
            )
            .returning(kColumnsAllowedToReturning)
            .execute(getKRowClass());
    }
}