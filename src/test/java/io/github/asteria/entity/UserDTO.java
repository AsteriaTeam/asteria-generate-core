package io.github.asteria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String eMail;

    private String nickName;

    /**
     * 是否有效 1:有效 0:无效
     */
    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return e_mail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @return e_mail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 获取是否有效 1:有效 0:无效
     *
     * @return is_valid - 是否有效 1:有效 0:无效
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 获取是否有效 1:有效 0:无效
     *
     * @return is_valid - 是否有效 1:有效 0:无效
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return deleted_at
     */
    public Long getDeletedAt() {
        return deletedAt;
    }

    /**
     * @return deleted_at
     */
    public Long getDeletedAt() {
        return deletedAt;
    }
}