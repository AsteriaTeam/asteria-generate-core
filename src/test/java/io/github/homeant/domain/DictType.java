package io.github.homeant.domain;

import lombok.Data;

/**
 * t_dict_type
 *
 * @author th
 * @date 2022-02-06
 */
@Data
public class DictType {
    /**
     * id 数据字典分类主键
    */
    private Long id;

    /**
     * dictName 数据字典分类名称
    */
    private String dictName;

    /**
     * dictCode 数据字典分类唯一标识
    */
    private String dictCode;

    /**
     * status 启用状态，1:启用，0:未启用
    */
    private Integer status;

    /**
     * isValid 是否有效 1:有效 0:无效
    */
    private Integer isValid;

    /**
     * createdAt 创建时间
    */
    private Long createdAt;

    /**
     * updatedAt 更新时间
    */
    private Long updatedAt;

    /**
     * deletedAt 删除时间
    */
    private Long deletedAt;
}