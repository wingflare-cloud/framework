package com.wingflare.facade.module.user.bo;

import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Update;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserBindRoleBo
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 用户绑定角色bo
 */
public class UserBindRoleBo {

    /**
     * 用户id
     */
    @NotBlank(message = "org.identityId.notBlank", groups = Update.class)
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "user.userId.formatError", groups = Update.class)
    private String userId;

    /**
     * 角色id
     */
    @Size(max = 5, message = "roleId.tooMany")
    @UniqueElements(message = "roleId.duplicate")
    private List<
            @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "roleId.formatError") String> roleIds;

    /**
     * 系统角色id
     */
    @Size(max = 100, message = "roleId.tooMany")
    private Map<@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]$", message = "systemName.error") String,
            @UniqueElements(message = "roleId.duplicate") @Size(max = 5, message = "roleId.tooMany")
                    List<@Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "roleId.formatError") String>> sysRoleIds;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public Map<String, List<String>> getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(Map<String, List<String>> sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }
}
