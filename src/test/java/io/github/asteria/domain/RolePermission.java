package io.github.asteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_role_permission
 *
 * @author th
 * @date 2022-01-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}