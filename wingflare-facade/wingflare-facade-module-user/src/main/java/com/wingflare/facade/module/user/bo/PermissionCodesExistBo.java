package com.wingflare.facade.module.user.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限代码是否存在
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-09
 */
public class PermissionCodesExistBo {

    private String roleId;

    private List<CodesExist> codes = new ArrayList<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<CodesExist> getCodes() {
        return codes;
    }

    public void setCodes(List<CodesExist> codes) {
        this.codes = codes;
    }

    public void addCodes(CodesExist codes) {
        this.codes.add(codes);
    }

    static public class CodesExist {

        private String systemCode;

        private List<String> codes;


        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }

        public List<String> getCodes() {
            return codes;
        }

        public void setCodes(List<String> codes) {
            this.codes = codes;
        }
    }
}
