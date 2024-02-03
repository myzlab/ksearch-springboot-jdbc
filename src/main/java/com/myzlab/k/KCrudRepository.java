package com.myzlab.k;

import com.myzlab.k.allowed.KColumnAllowedToReturning;
import com.myzlab.k.allowed.KColumnAllowedToSelect;
import static com.myzlab.k.KFunction.*;
import com.myzlab.k.functions.KDeleteFunction;
import com.myzlab.k.functions.KExistsFunction;
import com.myzlab.k.functions.KFindFunction;
import com.myzlab.k.functions.KValuesFunction;
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
    
    public KCollection<T> findByIds(
        final List<Y> ids,
        final KColumnAllowedToSelect... selects
    ) {
        return findByIds(
            getK().getJdbcTemplateDefaultName(),
            ids,
            selects
        );
    }
    
    public KCollection<T> findByIds(
        final String jdbc,
        final List<Y> ids,
        final KColumnAllowedToSelect... selects
    ) {
        return
            getK()
            .jdbc(jdbc)
            .select(selects)
            .from(getMetadata())
            .where(getKTableColumnId().in(ids))
            .multiple(getKRowClass());
    }
    
    public T findOneBy(
        final KFindFunction<KFrom, KQuery> kFindFunction,
        final KColumnAllowedToSelect... selects
    ) {
        return findOneBy(
            getK().getJdbcTemplateDefaultName(),
            kFindFunction,
            selects
        );
    }
    
    public T findOneBy(
        final String jdbc,
        final KFindFunction<KFrom, KQuery> kFindFunction,
        final KColumnAllowedToSelect... selects
    ) {
        return
            (T)
            kFindFunction.run(
                getK()
                .jdbc(jdbc)
                .select(selects)
                .from(getMetadata())
            )
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
    
    public KCollection<T> findMultipleBy(
        final KFindFunction<KFrom, KQuery> kFindFunction,
        final KColumnAllowedToSelect... selects
    ) {
        return findMultipleBy(
            getK().getJdbcTemplateDefaultName(),
            kFindFunction,
            selects
        );
    }
    
    public KCollection<T> findMultipleBy(
        final String jdbc,
        final KFindFunction<KFrom, KQuery> kFindFunction,
        final KColumnAllowedToSelect... selects
    ) {
        return
            kFindFunction.run(
                getK()
                .jdbc(jdbc)
                .select(selects)
                .from(getMetadata())
            )
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
    
    public int deleteBy(
        final KDeleteFunction<KDeleteFrom, KWhereDelete> kDeleteFunction
    ) {
        return deleteBy(
            getK().getJdbcTemplateDefaultName(),
            kDeleteFunction
        );
    }
    
    public int deleteBy(
        final String jdbc,
        final KDeleteFunction<KDeleteFrom, KWhereDelete> kDeleteFunction
    ) {
        return
            kDeleteFunction.run(
                getK()
                .jdbc(jdbc)
                .deleteFrom(getMetadata())
            )
            .execute();
    }
    
    public KCollection<T> deleteBy(
        final KDeleteFunction<KDeleteFrom, KWhereDelete> kDeleteFunction,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return deleteBy(
            getK().getJdbcTemplateDefaultName(),
            kDeleteFunction,
            kColumnsAllowedToReturning
        );
    }
    
    public KCollection<T> deleteBy(
        final String jdbc,
        final KDeleteFunction<KDeleteFrom, KWhereDelete> kDeleteFunction,
        final KColumnAllowedToReturning... kColumnsAllowedToReturning
    ) {
        return 
            kDeleteFunction.run(
                getK()
                .jdbc(jdbc)
                .deleteFrom(getMetadata())
            )
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
            .select(exists(subQuery).as("_🕆_GOD_BLESS_YOU_🕆_"))
            .single()
            .getBoolean(0);
    }
    
    public boolean existsBy(
        final KExistsFunction<KFrom, KQuery> kExistsFunction
    ) {
        return existsBy(
            getK().getJdbcTemplateDefaultName(),
            kExistsFunction
        );
    }
    
    public boolean existsBy(
        final String jdbc,
        final KExistsFunction<KFrom, KQuery> kExistsFunction
    ) {
        final KQuery subQuery =
            kExistsFunction.run(
                getK()
                .select1()
                .from(getMetadata())
            );
        
        return
            getK()
            .jdbc(jdbc)
            .select(exists(subQuery).as("_🕆_GOD_BLESS_YOU_🕆_"))
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
    
    public void assertExistsByIds(
        final List<Y> ids,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertExistsByIds(
            getK().getJdbcTemplateDefaultName(),
            ids,
            httpStatus,
            message
        );
    }
    
    public void assertExistsByIds(
        final String jdbc,
        final List<Y> ids,
        final HttpStatus httpStatus,
        final String message
    ) {
        final KValues values = values().append(ids,
            (KValuesFunction) (final Object id) -> new ArrayList() {{
                add(id);
            }}
        );
        
        final KCommonTableExpressionFilled idsCte = 
            cte("cte_")
            .columns("id")
            .as(values, "_🕆_JESUS_SAVES_🕆_");
        
        final boolean boolAndExists =
            getK()
            .with(idsCte)
            .select(
                boolAnd(
                    exists(
                        getK()
                        .select1()
                        .from(getMetadata())
                        .where(getKTableColumnId().eq(idsCte.c("id")))
                    )
                )
            )
            .from(idsCte)
            .single(Boolean.class);
        
        if (!boolAndExists) {
            throw KExceptionHelper.createByHttpStatus(httpStatus, message);
        }
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
    
    public void assertNotExistsByIds(
        final List<Y> ids,
        final HttpStatus httpStatus,
        final String message
    ) {
        assertNotExistsByIds(
            getK().getJdbcTemplateDefaultName(),
            ids,
            httpStatus,
            message
        );
    }
    
    public void assertNotExistsByIds(
        final String jdbc,
        final List<Y> ids,
        final HttpStatus httpStatus,
        final String message
    ) {
        final KValues values = values().append(ids,
            (KValuesFunction) (final Object id) -> new ArrayList() {{
                add(id);
            }}
        );
        
        final KCommonTableExpressionFilled idsCte = 
            cte("cte_")
            .columns("id")
            .as(values, "_🕆_JESUS_SAVES_🕆_");
        
        final boolean boolAndNotExists =
            getK()
            .with(idsCte)
            .select(
                boolAnd(
                    notExists(
                        getK()
                        .select1()
                        .from(getMetadata())
                        .where(getKTableColumnId().eq(idsCte.c("id")))
                    )
                )
            )
            .from(idsCte)
            .single(Boolean.class);
        
        if (!boolAndNotExists) {
            throw KExceptionHelper.createByHttpStatus(httpStatus, message);
        }
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
        KUtils.assertNotNull(entities, "entities");
        
        if (entities.isEmpty()) {
            return 0;
        }
        
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
        KUtils.assertNotNull(entities, "entities");
        
        if (entities.isEmpty()) {
            return new KCollection(getKRowClass());
        }
        
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
        KUtils.assertNotNull(entities, "entities");
        
        if (entities.isEmpty()) {
            return 0;
        }
        
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
        KUtils.assertNotNull(entities, "entities");
        
        if (entities.isEmpty()) {
            return new KCollection(getKRowClass());
        }
        
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