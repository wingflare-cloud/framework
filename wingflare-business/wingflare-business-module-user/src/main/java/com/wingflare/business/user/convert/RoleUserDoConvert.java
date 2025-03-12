package com.wingflare.business.user.convert;

import com.wingflare.business.user.db.RoleUserDo;
import com.wingflare.facade.module.user.dto.RoleUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleUserDoConvert {

    RoleUserDoConvert convert = Mappers.getMapper(RoleUserDoConvert.class);

    List<RoleUserDto> doToDtoList(List<RoleUserDo> userDoList);

}
