package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author dhb52
 * @date 2024-06-09
 * @since 1.0.0
 */
public class SystemUpdateUserPasswordRequestVO {

    @NotBlank(message = "Original password cannot be empty", groups = PutMapping.class)
    private String oldPassword;

    @NotBlank(message = "New password cannot be empty", groups = PutMapping.class)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
