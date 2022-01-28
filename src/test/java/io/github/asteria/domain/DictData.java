package io.github.asteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_dict_data
 *
 * @author th
 * @date 2022-01-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictData {
    private Long id;

    private String dictCode;

    private String itemName;

    private String itemValue;

    private String itemDesc;

    private Integer itemSort;

    private Integer status;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}