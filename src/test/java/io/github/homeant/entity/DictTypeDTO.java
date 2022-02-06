package io.github.homeant.entity;

import lombok.Data;

@Data
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
}