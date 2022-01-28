package io.github.asteria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_third_party_user
 *
 * @author th
 * @date 2022-01-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThirdPartyUser {
    private Long id;

    private Long userId;

    private String type;

    private String username;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

    private Long deletedAt;
}