package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RoleDynamicSqlSupport {
    public static final Role role = new Role();

    public static final SqlColumn<Long> id = role.id;

    public static final SqlColumn<String> name = role.name;

    public static final SqlColumn<Integer> status = role.status;

    public static final SqlColumn<Integer> isValid = role.isValid;

    public static final SqlColumn<Long> createdAt = role.createdAt;

    public static final SqlColumn<Long> updatedAt = role.updatedAt;

    public static final SqlColumn<Long> deletedAt = role.deletedAt;

    public static final class Role extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public Role() {
            super("t_role");
        }
    }
}