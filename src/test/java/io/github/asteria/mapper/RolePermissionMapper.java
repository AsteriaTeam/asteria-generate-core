package io.github.asteria.mapper;

import static io.github.asteria.mapper.RolePermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.asteria.domain.RolePermission;
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
public interface RolePermissionMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, roleId, permissionId, isValid, createdAt, updatedAt, deletedAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<RolePermission> insertStatement);

    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<RolePermission> records);

    default int insertMultiple(MultiRowInsertStatementProvider<RolePermission> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RolePermissionResult")
    Optional<RolePermission> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RolePermissionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.TINYINT),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.BIGINT)
    })
    List<RolePermission> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rolePermission, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rolePermission, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(RolePermission record) {
        return MyBatis3Utils.insert(this::insert, record, rolePermission, c ->
            c.map(roleId).toProperty("roleId")
            .map(permissionId).toProperty("permissionId")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertMultiple(Collection<RolePermission> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rolePermission, c ->
            c.map(roleId).toProperty("roleId")
            .map(permissionId).toProperty("permissionId")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertSelective(RolePermission record) {
        return MyBatis3Utils.insert(this::insert, record, rolePermission, c ->
            c.map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
            .map(isValid).toPropertyWhenPresent("isValid", record::getIsValid)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(deletedAt).toPropertyWhenPresent("deletedAt", record::getDeletedAt)
        );
    }

    default Optional<RolePermission> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rolePermission, completer);
    }

    default List<RolePermission> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rolePermission, completer);
    }

    default List<RolePermission> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rolePermission, completer);
    }

    default Optional<RolePermission> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rolePermission, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(RolePermission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(isValid).equalTo(record::getIsValid)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(deletedAt).equalTo(record::getDeletedAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(RolePermission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(isValid).equalToWhenPresent(record::getIsValid)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(deletedAt).equalToWhenPresent(record::getDeletedAt);
    }

    default int updateByPrimaryKey(RolePermission record) {
        return update(c ->
            c.set(roleId).equalTo(record::getRoleId)
            .set(permissionId).equalTo(record::getPermissionId)
            .set(isValid).equalTo(record::getIsValid)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .set(deletedAt).equalTo(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(RolePermission record) {
        return update(c ->
            c.set(roleId).equalToWhenPresent(record::getRoleId)
            .set(permissionId).equalToWhenPresent(record::getPermissionId)
            .set(isValid).equalToWhenPresent(record::getIsValid)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .set(deletedAt).equalToWhenPresent(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}