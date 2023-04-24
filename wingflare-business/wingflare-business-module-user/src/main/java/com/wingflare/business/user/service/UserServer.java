package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.UserDo;
import com.wingflare.business.user.mapper.UserMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户Server
 *
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
@Service
public class UserServer extends BaseService<UserMapper, UserDo>
{

    /**
    * 获取系统用户Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<String, UserDo> getMap(QueryWrapper<UserDo> wrapper)
	{
        List<UserDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<String, UserDo> userDoMap = new HashMap<>(list.size());

        for (UserDo userDo : list) {
            userDoMap.put(userDo.getUserId(), userDo);
        }

        return userDoMap;
    }
}
