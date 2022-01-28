package io.github.asteria.mapper;

import static io.github.asteria.mapper.DictDataDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.asteria.domain.DictData;
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
public interface DictDataMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, dictCode, itemName, itemValue, itemDesc, itemSort, status, isValid, createdAt, updatedAt, deletedAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<DictData> insertStatement);

    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<DictData> records);

    default int insertMultiple(MultiRowInsertStatementProvider<DictData> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DictDataResult")
    Optional<DictData> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DictDataResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dict_code", property="dictCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_name", property="itemName", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_desc", property="itemDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_sort", property="itemSort", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.TINYINT),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.BIGINT),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.BIGINT)
    })
    List<DictData> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, dictData, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, dictData, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(DictData record) {
        return MyBatis3Utils.insert(this::insert, record, dictData, c ->
            c.map(dictCode).toProperty("dictCode")
            .map(itemName).toProperty("itemName")
            .map(itemValue).toProperty("itemValue")
            .map(itemDesc).toProperty("itemDesc")
            .map(itemSort).toProperty("itemSort")
            .map(status).toProperty("status")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertMultiple(Collection<DictData> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, dictData, c ->
            c.map(dictCode).toProperty("dictCode")
            .map(itemName).toProperty("itemName")
            .map(itemValue).toProperty("itemValue")
            .map(itemDesc).toProperty("itemDesc")
            .map(itemSort).toProperty("itemSort")
            .map(status).toProperty("status")
            .map(isValid).toProperty("isValid")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
        );
    }

    default int insertSelective(DictData record) {
        return MyBatis3Utils.insert(this::insert, record, dictData, c ->
            c.map(dictCode).toPropertyWhenPresent("dictCode", record::getDictCode)
            .map(itemName).toPropertyWhenPresent("itemName", record::getItemName)
            .map(itemValue).toPropertyWhenPresent("itemValue", record::getItemValue)
            .map(itemDesc).toPropertyWhenPresent("itemDesc", record::getItemDesc)
            .map(itemSort).toPropertyWhenPresent("itemSort", record::getItemSort)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(isValid).toPropertyWhenPresent("isValid", record::getIsValid)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(deletedAt).toPropertyWhenPresent("deletedAt", record::getDeletedAt)
        );
    }

    default Optional<DictData> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, dictData, completer);
    }

    default List<DictData> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, dictData, completer);
    }

    default List<DictData> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, dictData, completer);
    }

    default Optional<DictData> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, dictData, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(DictData record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dictCode).equalTo(record::getDictCode)
                .set(itemName).equalTo(record::getItemName)
                .set(itemValue).equalTo(record::getItemValue)
                .set(itemDesc).equalTo(record::getItemDesc)
                .set(itemSort).equalTo(record::getItemSort)
                .set(status).equalTo(record::getStatus)
                .set(isValid).equalTo(record::getIsValid)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(deletedAt).equalTo(record::getDeletedAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(DictData record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dictCode).equalToWhenPresent(record::getDictCode)
                .set(itemName).equalToWhenPresent(record::getItemName)
                .set(itemValue).equalToWhenPresent(record::getItemValue)
                .set(itemDesc).equalToWhenPresent(record::getItemDesc)
                .set(itemSort).equalToWhenPresent(record::getItemSort)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(isValid).equalToWhenPresent(record::getIsValid)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(deletedAt).equalToWhenPresent(record::getDeletedAt);
    }

    default int updateByPrimaryKey(DictData record) {
        return update(c ->
            c.set(dictCode).equalTo(record::getDictCode)
            .set(itemName).equalTo(record::getItemName)
            .set(itemValue).equalTo(record::getItemValue)
            .set(itemDesc).equalTo(record::getItemDesc)
            .set(itemSort).equalTo(record::getItemSort)
            .set(status).equalTo(record::getStatus)
            .set(isValid).equalTo(record::getIsValid)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .set(deletedAt).equalTo(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(DictData record) {
        return update(c ->
            c.set(dictCode).equalToWhenPresent(record::getDictCode)
            .set(itemName).equalToWhenPresent(record::getItemName)
            .set(itemValue).equalToWhenPresent(record::getItemValue)
            .set(itemDesc).equalToWhenPresent(record::getItemDesc)
            .set(itemSort).equalToWhenPresent(record::getItemSort)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(isValid).equalToWhenPresent(record::getIsValid)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .set(deletedAt).equalToWhenPresent(record::getDeletedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}