package io.github.asteria.mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    public static final User user = new User();

    public static final SqlColumn<Long> id = user.id;

    public static final SqlColumn<String> username = user.username;

    public static final SqlColumn<String> password = user.password;

    public static final SqlColumn<String> eMail = user.eMail;

    public static final SqlColumn<String> nickName = user.nickName;

    public static final SqlColumn<Integer> isValid = user.isValid;

    public static final SqlColumn<Long> createdAt = user.createdAt;

    public static final SqlColumn<Long> updatedAt = user.updatedAt;

    public static final SqlColumn<Long> deletedAt = user.deletedAt;

    public static final class User extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> eMail = column("e_mail", JDBCType.VARCHAR);

        public final SqlColumn<String> nickName = column("nick_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> isValid = column("is_valid", JDBCType.TINYINT);

        public final SqlColumn<Long> createdAt = column("created_at", JDBCType.BIGINT);

        public final SqlColumn<Long> updatedAt = column("updated_at", JDBCType.BIGINT);

        public final SqlColumn<Long> deletedAt = column("deleted_at", JDBCType.BIGINT);

        public User() {
            super("t_user");
        }
    }
}