package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PermissionDynamicSqlSupport {
    public static final Permission permission = new Permission();

    public static final SqlColumn<Long> id = permission.id;

    public static final SqlColumn<Long> parentId = permission.parentId;

    public static final SqlColumn<String> name = permission.name;

    public static final SqlColumn<Byte> type = permission.type;

    public static final SqlColumn<String> url = permission.url;

    public static final SqlColumn<Integer> sort = permission.sort;

    public static final SqlColumn<String> icon = permission.icon;

    public static final SqlColumn<Integer> status = permission.status;

    public static final SqlColumn<Integer> isValid = permission.isValid;

    public static final SqlColumn<Long> createdAt = permission.createdAt;

    public static final SqlColumn<Long> updatedAt = permission.updatedAt;

    public static final SqlColumn<Long> deletedAt = permission.deletedAt;

    public static final class Permission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> type = column("type", JDBCType.TINYINT);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<String> icon = column("icon", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public Permission() {
            super("t_permission");
        }
    }
}