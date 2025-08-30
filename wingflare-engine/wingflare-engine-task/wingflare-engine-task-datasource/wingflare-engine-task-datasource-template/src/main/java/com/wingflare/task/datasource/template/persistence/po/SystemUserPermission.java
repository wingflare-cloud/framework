package com.wingflare.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 系统用户权限表
 *
 * @author opensnail
 * @since 2022-03-05
 */
@TableName("sj_system_user_permission")
public class SystemUserPermission extends CreateDt {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String groupName;

    private String namespaceId;

    private Long systemUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }
}
