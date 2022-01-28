package io.github.asteria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictTypeDTO {
    /**
     * 数据字典分类主键
     */
    private Long id;

    /**
     * 数据字典分类名称
     */
    private String dictName;

    /**
     * 数据字典分类唯一标识
     */
    private String dictCode;

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
     * 获取数据字典分类主键
     *
     * @return id - 数据字典分类主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 获取数据字典分类主键
     *
     * @return id - 数据字典分类主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 获取数据字典分类名称
     *
     * @return dict_name - 数据字典分类名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 获取数据字典分类名称
     *
     * @return dict_name - 数据字典分类名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 获取数据字典分类唯一标识
     *
     * @return dict_code - 数据字典分类唯一标识
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 获取数据字典分类唯一标识
     *
     * @return dict_code - 数据字典分类唯一标识
     */
    public String getDictCode() {
        return dictCode;
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