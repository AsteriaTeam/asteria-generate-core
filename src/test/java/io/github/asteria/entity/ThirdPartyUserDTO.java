package io.github.asteria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThirdPartyUserDTO {
    private Long id;

    private Long userId;

    private String type;

    private String username;

    private Integer isValid;

    private Long createdAt;

    private Long updatedAt;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return is_valid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @return is_valid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return deleted_at
     */
    public Long getDeletedAt() {
        return deletedAt;
    }

    /**
     * @return deleted_at
     */
    public Long getDeletedAt() {
        return deletedAt;
    }
}