package io.github.asteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_role
 *
 * @author th
 * @date 2022-01-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Long id;

    private String name;

    private Integer status;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}