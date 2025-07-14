package com.wingflare.business.user.convert;

import com.wingflare.business.user.db.RoleUserDO;
import com.wingflare.facade.module.user.dto.RoleUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleUserDoConvert {

    RoleUserDoConvert convert = Mappers.getMapper(RoleUserDoConvert.class);

    List<RoleUserDTO> doToDtoList(List<RoleUserDO> userDoList);

}
