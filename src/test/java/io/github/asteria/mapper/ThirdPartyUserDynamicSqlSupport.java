package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ThirdPartyUserDynamicSqlSupport {
    public static final ThirdPartyUser thirdPartyUser = new ThirdPartyUser();

    public static final SqlColumn<Long> id = thirdPartyUser.id;

    public static final SqlColumn<Long> userId = thirdPartyUser.userId;

    public static final SqlColumn<String> type = thirdPartyUser.type;

    public static final SqlColumn<String> username = thirdPartyUser.username;

    public static final SqlColumn<Integer> isValid = thirdPartyUser.isValid;

    public static final SqlColumn<Long> createdAt = thirdPartyUser.createdAt;

    public static final SqlColumn<Long> updatedAt = thirdPartyUser.updatedAt;

    public static final SqlColumn<Long> deletedAt = thirdPartyUser.deletedAt;

    public static final class ThirdPartyUser extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.CHAR);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public ThirdPartyUser() {
            super("t_third_party_user");
        }
    }
}