package io.github.asteria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDTO {
    private Long id;

    private Long parentId;

    private String name;

    /**
     * 权限类型 1:菜单 2:按钮 3:权限
     */
    private Byte type;

    private String url;

    private Integer sort;

    private String icon;

    /**
     * 启用状态，1:启用，0:未启用
     */
    private Integer status;

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
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 获取权限类型 1:菜单 2:按钮 3:权限
     *
     * @return type - 权限类型 1:菜单 2:按钮 3:权限
     */
    public Byte getType() {
        return type;
    }

    /**
     * 获取权限类型 1:菜单 2:按钮 3:权限
     *
     * @return type - 权限类型 1:菜单 2:按钮 3:权限
     */
    public Byte getType() {
        return type;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 获取启用状态，1:启用，0:未启用
     *
     * @return status - 启用状态，1:启用，0:未启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 获取启用状态，1:启用，0:未启用
     *
     * @return status - 启用状态，1:启用，0:未启用
     */
    public Integer getStatus() {
        return status;
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