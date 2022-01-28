package io.github.asteria.mapper;

import static io.github.asteria.mapper.PermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.asteria.domain.Permission;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface PermissionMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, parentId, name, type, url, sort, icon, status, isValid, createdAt, updatedAt, deletedAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Permission> insertStatement);

    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Permission> records);

    default int insertMultiple(MultiRowInsertStatementProvider<Permission> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PermissionResult")
    Optional<Permission> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PermissionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.TINYINT),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.BIGINT)
    })
    List<Permission> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, permission, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, permission, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(Permission record) {
        return MyBatis3Utils.insert(this::insert, record, permission, c ->
            c.map(parentId).toProperty("parentId")
            .map(name).toProperty("name")
            .map(type).toProperty("type")
            .map(url).toProperty("url")
            .map(sort).toProperty("sort")
            .map(icon).toProperty("icon")
            .map(status).toProperty("status")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertMultiple(Collection<Permission> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, permission, c ->
            c.map(parentId).toProperty("parentId")
            .map(name).toProperty("name")
            .map(type).toProperty("type")
            .map(url).toProperty("url")
            .map(sort).toProperty("sort")
            .map(icon).toProperty("icon")
            .map(status).toProperty("status")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertSelective(Permission record) {
        return MyBatis3Utils.insert(this::insert, record, permission, c ->
            c.map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(url).toPropertyWhenPresent("url", record::getUrl)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
            .map(icon).toPropertyWhenPresent("icon", record::getIcon)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(isValid).toPropertyWhenPresent("isValid", record::getIsValid)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(deletedAt).toPropertyWhenPresent("deletedAt", record::getDeletedAt)
        );
    }

    default Optional<Permission> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, permission, completer);
    }

    default List<Permission> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, permission, completer);
    }

    default List<Permission> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, permission, completer);
    }

    default Optional<Permission> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, permission, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(Permission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(parentId).equalTo(record::getParentId)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(url).equalTo(record::getUrl)
                .set(sort).equalTo(record::getSort)
                .set(icon).equalTo(record::getIcon)
                .set(status).equalTo(record::getStatus)
                .set(isValid).equalTo(record::getIsValid)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(deletedAt).equalTo(record::getDeletedAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(Permission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(parentId).equalToWhenPresent(record::getParentId)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(icon).equalToWhenPresent(record::getIcon)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(isValid).equalToWhenPresent(record::getIsValid)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(deletedAt).equalToWhenPresent(record::getDeletedAt);
    }

    default int updateByPrimaryKey(Permission record) {
        return update(c ->
            c.set(parentId).equalTo(record::getParentId)
            .set(name).equalTo(record::getName)
            .set(type).equalTo(record::getType)
            .set(url).equalTo(record::getUrl)
            .set(sort).equalTo(record::getSort)
            .set(icon).equalTo(record::getIcon)
            .set(status).equalTo(record::getStatus)
            .set(isValid).equalTo(record::getIsValid)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .set(deletedAt).equalTo(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(Permission record) {
        return update(c ->
            c.set(parentId).equalToWhenPresent(record::getParentId)
            .set(name).equalToWhenPresent(record::getName)
            .set(type).equalToWhenPresent(record::getType)
            .set(url).equalToWhenPresent(record::getUrl)
            .set(sort).equalToWhenPresent(record::getSort)
            .set(icon).equalToWhenPresent(record::getIcon)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(isValid).equalToWhenPresent(record::getIsValid)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .set(deletedAt).equalToWhenPresent(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}