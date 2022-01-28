package io.github.asteria.mapper;

import static io.github.asteria.mapper.ThirdPartyUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.asteria.domain.ThirdPartyUser;
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
public interface ThirdPartyUserMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, type, username, isValid, createdAt, updatedAt, deletedAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<ThirdPartyUser> insertStatement);

    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<ThirdPartyUser> records);

    default int insertMultiple(MultiRowInsertStatementProvider<ThirdPartyUser> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ThirdPartyUserResult")
    Optional<ThirdPartyUser> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ThirdPartyUserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.CHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.BIGINT)
    })
    List<ThirdPartyUser> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, thirdPartyUser, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, thirdPartyUser, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(ThirdPartyUser record) {
        return MyBatis3Utils.insert(this::insert, record, thirdPartyUser, c ->
            c.map(userId).toProperty("userId")
            .map(type).toProperty("type")
            .map(username).toProperty("username")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertMultiple(Collection<ThirdPartyUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, thirdPartyUser, c ->
            c.map(userId).toProperty("userId")
            .map(type).toProperty("type")
            .map(username).toProperty("username")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertSelective(ThirdPartyUser record) {
        return MyBatis3Utils.insert(this::insert, record, thirdPartyUser, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(username).toPropertyWhenPresent("username", record::getUsername)
            .map(isValid).toPropertyWhenPresent("isValid", record::getIsValid)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(deletedAt).toPropertyWhenPresent("deletedAt", record::getDeletedAt)
        );
    }

    default Optional<ThirdPartyUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, thirdPartyUser, completer);
    }

    default List<ThirdPartyUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, thirdPartyUser, completer);
    }

    default List<ThirdPartyUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, thirdPartyUser, completer);
    }

    default Optional<ThirdPartyUser> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, thirdPartyUser, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(ThirdPartyUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(type).equalTo(record::getType)
                .set(username).equalTo(record::getUsername)
                .set(isValid).equalTo(record::getIsValid)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(deletedAt).equalTo(record::getDeletedAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(ThirdPartyUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(type).equalToWhenPresent(record::getType)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(isValid).equalToWhenPresent(record::getIsValid)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(deletedAt).equalToWhenPresent(record::getDeletedAt);
    }

    default int updateByPrimaryKey(ThirdPartyUser record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(type).equalTo(record::getType)
            .set(username).equalTo(record::getUsername)
            .set(isValid).equalTo(record::getIsValid)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .set(deletedAt).equalTo(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(ThirdPartyUser record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(type).equalToWhenPresent(record::getType)
            .set(username).equalToWhenPresent(record::getUsername)
            .set(isValid).equalToWhenPresent(record::getIsValid)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .set(deletedAt).equalToWhenPresent(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}