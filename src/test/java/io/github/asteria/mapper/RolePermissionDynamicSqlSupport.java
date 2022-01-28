package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RolePermissionDynamicSqlSupport {
    public static final RolePermission rolePermission = new RolePermission();

    public static final SqlColumn<Long> id = rolePermission.id;

    public static final SqlColumn<Long> roleId = rolePermission.roleId;

    public static final SqlColumn<Long> permissionId = rolePermission.permissionId;

    public static final SqlColumn<Integer> isValid = rolePermission.isValid;

    public static final SqlColumn<Long> createdAt = rolePermission.createdAt;

    public static final SqlColumn<Long> updatedAt = rolePermission.updatedAt;

    public static final SqlColumn<Long> deletedAt = rolePermission.deletedAt;

    public static final class RolePermission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> roleId = column("role_id", JDBCType.BIGINT);

        public final SqlColumn<Long> permissionId = column("permission_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public RolePermission() {
            super("t_role_permission");
        }
    }
}