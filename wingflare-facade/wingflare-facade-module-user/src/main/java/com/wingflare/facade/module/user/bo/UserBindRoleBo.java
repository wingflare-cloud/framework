package com.wingflare.facade.module.user.bo;

import com.wingflare.lib.core.validation.Update;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;
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
    @Min(message = "org.identityId.notBlank", value = 1, groups = Update.class)
    private BigInteger userId;

    /**
     * 角色id
     */
    @Size(max = 5, message = "roleId.tooMany")
    @UniqueElements(message = "roleId.duplicate")
    private List<BigInteger> roleIds;

    /**
     * 系统角色id
     */
    @Size(max = 100, message = "roleId.tooMany")
    private Map<@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]$", message = "systemName.error") String,
            @UniqueElements(message = "roleId.duplicate") @Size(max = 5, message = "roleId.tooMany")
                    List<BigInteger>> sysRoleIds;


    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public List<BigInteger> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<BigInteger> roleIds) {
        this.roleIds = roleIds;
    }

    public Map<String, List<BigInteger>> getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(Map<String, List<BigInteger>> sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }
}
