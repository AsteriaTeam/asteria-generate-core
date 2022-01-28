package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {
    public static final UserRole userRole = new UserRole();

    public static final SqlColumn<Long> id = userRole.id;

    public static final SqlColumn<Long> userId = userRole.userId;

    public static final SqlColumn<Long> roleId = userRole.roleId;

    public static final SqlColumn<Integer> isValid = userRole.isValid;

    public static final SqlColumn<Long> createdAt = userRole.createdAt;

    public static final SqlColumn<Long> updatedAt = userRole.updatedAt;

    public static final SqlColumn<Long> deletedAt = userRole.deletedAt;

    public static final class UserRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public UserRole() {
            super("t_user_role");
        }
    }
}