package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.RoleUserDTO;
import com.wingflare.lib.standard.PageDto;


/**
 * <p>
 * 系统用户角色表 业务接口
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
public interface UserRoleBiz {


    PageDto<RoleUserDTO> getUserList(UserSearchBO userSearchBo);

}
