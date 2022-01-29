package io.github.asteria.domain;

import lombok.Data;

/**
 * t_dict_type
 *
 * @author th
 * @date 2022-01-29
 */
@Data
public class DictType {
    private Long id;

    private String dictName;

    private String dictCode;

    private Integer status;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}