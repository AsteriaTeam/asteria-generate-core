package io.github.asteria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDTO {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否有效 1:有效 0:无效
     */
    private Integer isValid;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;

    /**
     * 删除时间
     */
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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Long getRoleId() {
        return roleId;
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
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 获取删除时间
     *
     * @return deleted_at - 删除时间
     */
    public Long getDeletedAt() {
        return deletedAt;
    }

    /**
     * 获取删除时间
     *
     * @return deleted_at - 删除时间
     */
    public Long getDeletedAt() {
        return deletedAt;
    }
}