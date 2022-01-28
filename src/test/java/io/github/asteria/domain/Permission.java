package io.github.asteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_permission
 *
 * @author th
 * @date 2022-01-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    private Long id;

    private Long parentId;

    private String name;

    private Byte type;

    private String url;

    private Integer sort;

    private String icon;

    private Integer status;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}