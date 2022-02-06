package io.github.homeant.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DictTypeDynamicSqlSupport {
    public static final DictType dictType = new DictType();

    /**
     * id 数据字典分类主键
    */
    public static final SqlColumn<Long> id = dictType.id;

    /**
     * dictName 数据字典分类名称
    */
    public static final SqlColumn<String> dictName = dictType.dictName;

    /**
     * dictCode 数据字典分类唯一标识
    */
    public static final SqlColumn<String> dictCode = dictType.dictCode;

    /**
     * status 启用状态，1:启用，0:未启用
    */
    public static final SqlColumn<Integer> status = dictType.status;

    /**
     * isValid 是否有效 1:有效 0:无效
    */
    public static final SqlColumn<Integer> isValid = dictType.isValid;

    /**
     * createdAt 创建时间
    */
    public static final SqlColumn<Long> createdAt = dictType.createdAt;

    /**
     * updatedAt 更新时间
    */
    public static final SqlColumn<Long> updatedAt = dictType.updatedAt;

    /**
     * deletedAt 删除时间
    */
    public static final SqlColumn<Long> deletedAt = dictType.deletedAt;

    public static final class DictType extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> dictName = column("dict_name", JDBCType.VARCHAR);

        public final SqlColumn<String> dictCode = column("dict_code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public DictType() {
            super("t_dict_type");
        }
    }
}