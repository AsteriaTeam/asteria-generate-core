package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DictDataDynamicSqlSupport {
    public static final DictData dictData = new DictData();

    public static final SqlColumn<Long> id = dictData.id;

    public static final SqlColumn<String> dictCode = dictData.dictCode;

    public static final SqlColumn<String> itemName = dictData.itemName;

    public static final SqlColumn<String> itemValue = dictData.itemValue;

    public static final SqlColumn<String> itemDesc = dictData.itemDesc;

    public static final SqlColumn<Integer> itemSort = dictData.itemSort;

    public static final SqlColumn<Integer> status = dictData.status;

    public static final SqlColumn<Integer> isValid = dictData.isValid;

    public static final SqlColumn<Long> createdAt = dictData.createdAt;

    public static final SqlColumn<Long> updatedAt = dictData.updatedAt;

    public static final SqlColumn<Long> deletedAt = dictData.deletedAt;

    public static final class DictData extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> dictCode = column("dict_code", JDBCType.VARCHAR);

        public final SqlColumn<String> itemName = column("item_name", JDBCType.VARCHAR);

        public final SqlColumn<String> itemValue = column("item_value", JDBCType.VARCHAR);

        public final SqlColumn<String> itemDesc = column("item_desc", JDBCType.VARCHAR);

        public final SqlColumn<Integer> itemSort = column("item_sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public DictData() {
            super("t_dict_data");
        }
    }
}