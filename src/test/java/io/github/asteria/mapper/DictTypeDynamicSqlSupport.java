package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DictTypeDynamicSqlSupport {
    public static final DictType dictType = new DictType();

    public static final SqlColumn<Long> id = dictType.id;

    public static final SqlColumn<String> dictName = dictType.dictName;

    public static final SqlColumn<String> dictCode = dictType.dictCode;

    public static final SqlColumn<Integer> status = dictType.status;

    public static final SqlColumn<Integer> isValid = dictType.isValid;

    public static final SqlColumn<Long> createdAt = dictType.createdAt;

    public static final SqlColumn<Long> updatedAt = dictType.updatedAt;

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