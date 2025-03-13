package com.wingflare.business.user.biz;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.constants.UserEventName;
import com.wingflare.business.user.convert.UserConvert;
import com.wingflare.business.user.db.RoleDo;
import com.wingflare.business.user.db.UserDo;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.business.user.service.UserServer;
import com.wingflare.business.user.wrapper.UserWrapper;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UpdatePasswdBo;
import com.wingflare.facade.module.user.bo.UserBindRoleBo;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.facade.module.user.dict.UserAccountType;
import com.wingflare.facade.module.user.dto.UserDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.enums.SensitiveType;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.security.annotation.Desensitize;
import com.wingflare.lib.security.annotation.DesensitizeGroups;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.configure.properties.BusinessSystemProperties;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.model.EventCtx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


/**
 * 系统用户Biz
 *
 * @author naizui_ycx
 * @date Sun Mar 05 09:45:12 CST 2023
 */
@Component
@Validated
public class UserBizImpl implements UserBiz {

    @Resource
    private UserServer userServer;

    @Resource
    private BusinessSystemProperties businessSystemProperties;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    @Resource
    private UserAuthUtil userAuthUtil;

    @Resource
    private UserRoleServer userRoleServer;

    @Resource
    private RoleServer roleServer;

    private static final Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

    /**
     * 查询系统用户列表
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.list[*].userPasswd"
                    ),
                    @Desensitize(
                            jsonPath = "$.list[*].userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.list[*].userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public PageDto<UserDto> list(@Valid UserSearchBo bo) {
        IPage<UserDo> iPage = userServer.page(
                userServer.createPage(bo),
                UserWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                UserConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统用户详情
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto get(@Valid @NotNull IdBo bo) {
        return UserConvert.convert.doToDto(
                userServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统用户详情
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto getOnlyOne(@Valid @NotNull UserSearchBo bo) {
        return UserConvert.convert.doToDto(
                userServer.getOne(
                        UserWrapper.getQueryWrapper(bo)
                ));
    }

    /**
     * 删除系统用户
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto delete(@Valid @NotNull IdBo bo) {
        UserDto dto = deleteHandle(bo);
        afterDelete(dto);
        return dto;
    }


    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto deleteHandle(@Valid @NotNull IdBo bo) {
        return transactionTemplate.execute(status -> {
            UserDto dto = null;
            UserDo userDo = userServer.getById(bo.getId());

            if (userDo != null) {
                userServer.removeById(bo.getId());
                dto = UserConvert.convert.doToDto(userDo);
                eventUtil.publishEvent(UserEventName.USER_DELETE, false, dto);
            }

            return dto;
        });
    }

    public void afterDelete(UserDto dto) {
        Optional.ofNullable(dto)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(UserEventName.USER_DELETED, false, val);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }


    /**
     * 新增系统用户
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    @Validated({Default.class, Create.class})
    public UserDto create(@Valid @NotNull UserBo bo) {
        UserDto dto = createHandle(bo);
        afterCreate(dto);
        return dto;
    }


    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    @Validated({Default.class, Create.class})
    public UserDto createHandle(@Valid @NotNull UserBo bo) {
        return transactionTemplate.execute(status -> {
            checkUserCanSave(bo, null);
            bo.setSuperAdministrator(0);
            bo.setUserPasswd(UserAuthUtil.encryptPassword(bo.getUserPasswd()));
            UserDo userDo = UserConvert.convert.boToDo(bo);
            Assert.isTrue(userServer.save(userDo), ErrorCode.SYS_USER_CREATE_ERROR);

            List<UserRoleDo> userRoleDoList = new ArrayList<>();

            if (CollectionUtil.isNotEmpty(bo.getUserRole())) {
                bo.getUserRole().forEach(roleId -> {
                   userRoleDoList.add(
                           new UserRoleDo()
                                   .setUserId(userDo.getUserId())
                                   .setRoleId(roleId)
                   );
                });

                userRoleServer.saveBatch(userRoleDoList);
            }

            UserDto userDto = UserConvert.convert.doToDto(userDo);
            eventUtil.publishEvent(UserEventName.USER_CREATE, bo, userDto);
            return userDto;
        });
    }

    public void afterCreate(UserDto dto) {
        try {
            eventUtil.publishEvent(UserEventName.USER_CREATED,
                    EventCtx.getInstance().get(UserEventName.USER_CREATE).getSource(), dto);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * 更新系统用户
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    @Validated({Default.class, Update.class})
    public UserDto update(@Valid @NotNull UserBo bo) {
        UserDto dto = updateHandle(bo);
        afterUpdate(dto);
        return dto;
    }

    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    @Validated({Default.class, Update.class})
    public UserDto updateHandle(@Valid @NotNull UserBo bo) {
        return transactionTemplate.execute(status -> {
            UserDo oldUserDo = userServer.getById(bo.getUserId());
            UserDto userDto = null;

            if (oldUserDo == null) {
                throw new DataNotFoundException("user.data.notfound");
            }

            checkUserCanSave(bo, oldUserDo);
            UserDo userDo = UserConvert.convert.boToDo(bo);
            bo.setUserPasswd(null);
            bo.setSuperAdministrator(null);
            bo.setUserEmail(null);
            bo.setUserPhone(null);
            UserDo oldField = oldUserDo.setOnNew(userDo);

            if (oldField != null) {
                Assert.isTrue(userServer.updateById(oldUserDo), ErrorCode.SYS_USER_UPDATE_ERROR);
                userDto = UserConvert.convert.doToDto(oldUserDo);
                eventUtil.publishEvent(UserEventName.USER_UPDATE,
                        UserConvert.convert.doToDto(oldField), userDto);
            }

            return userDto;
        });
    }

    public void afterUpdate(UserDto dto) {
        Optional.ofNullable(dto)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(UserEventName.USER_UPDATED,
                                EventCtx.getInstance().get(UserEventName.USER_UPDATE).getSource(), val);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 更新用户密码
     *
     * @param bo
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto updatePasswd(@Valid @NotNull UpdatePasswdBo bo) {
        if (userAuthUtil.getUser() != null && !userAuthUtil.getUser().isSuperAdmin()) {
            String limitUserId = userAuthUtil.getUser()
                    .getUserId();
            Assert.isTrue(bo.getUserId().equals(limitUserId), ErrorCode.SYS_USER_UPDATE_PASSWD_NO_POWER);
        }

        UserDto dto = updatePasswdHandle(bo);
        afterUpdatePasswd(dto);
        return dto;
    }

    /**
     * 判断是否存在符合条件的系统用户
     *
     * @param bo 查询参数
     * @return 系统用户
     */
    public boolean has(UserSearchBo bo) {
        return userServer.has(
                UserWrapper.getQueryWrapper(bo)
        );
    }

    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPasswd",
                            sensitiveType = SensitiveType.PASSWD
                    ),
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto updatePasswdHandle(@Valid @NotNull UpdatePasswdBo bo) {
        return transactionTemplate.execute(status -> {
            UserDo userDo = userServer.getById(bo.getUserId());
            Assert.isTrue(userDo != null, ErrorCode.SYS_USER_NON_EXISTENT);

            if (StringUtil.isNotEmpty(bo.getOldPasswd())) {
                Assert.isTrue(
                        UserAuthUtil.matchesPassword(bo.getOldPasswd(), userDo.getUserPasswd()),
                        ErrorCode.SYS_USER_OLD_PWD_ERR
                );
            }

            userDo.setUserPasswd(UserAuthUtil.encryptPassword(bo.getPasswd()));
            Assert.isTrue(userServer.updateById(userDo), ErrorCode.SYS_USER_UPDATE_ERROR);
            UserDto userDto = UserConvert.convert.doToDto(userDo);
            eventUtil.publishEvent(UserEventName.USER_CHANGE_PWD, false, userDto);
            return userDto;
        });
    }


    public void afterUpdatePasswd(UserDto dto) {
        try {
            eventUtil.publishEvent(UserEventName.USER_CHANGED_PWD, false, dto);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }
    }


    /**
     * 通过登录名获取登录用户
     *
     * @param loginName
     * @return
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.userPhone",
                            sensitiveType = SensitiveType.MOBILE_PHONE
                    ),
                    @Desensitize(
                            jsonPath = "$.userEmail",
                            sensitiveType = SensitiveType.EMAIL
                    )
            }
    )
    public UserDto getUserByLoginName(@NotBlank String loginName) {
        UserDo userDo = userServer.getOne(
                new LambdaQueryWrapper<UserDo>()
                        .eq(UserDo::getUserEmail, loginName)
                        .or()
                        .eq(UserDo::getUserPhone, loginName)
                        .or()
                        .eq(UserDo::getUserAccount, loginName)
        );

        if (userDo == null) {
            return null;
        }

        return UserConvert.convert.doToDto(userDo);
    }

    /**
     * 用户绑定角色
     *
     * @param bo
     */
    @Override
    public void userBindRole(@Valid @NotNull UserBindRoleBo bo) {
        UserDo userDo = userServer.getById(bo.getUserId());
        Assert.isTrue(userDo != null, ErrorCode.SYS_USER_NON_EXISTENT);
        Assert.isTrue(userDo.getAccountType().contains(UserAccountType.PRIVILEGE.getValue()),
                ErrorCode.SYS_USER_NON_PRIVILEGE_NOT_BIND_ROLE);

        Set<String> baseRole = new HashSet<>(Optional.ofNullable(bo.getRoleIds()).orElse(new ArrayList<>()));
        Set<String> roleIds = new HashSet<>();
        Set<String> systems = new HashSet<>();
        Optional.ofNullable(bo.getRoleIds())
                .ifPresent(roleIds::addAll);
        Optional.ofNullable(bo.getSysRoleIds())
                .ifPresent(map -> map.forEach((k, v) -> {
                    roleIds.addAll(v);
                    systems.add(k);
                }));

        systems.removeAll(businessSystemProperties.getNames());
        Assert.isFalse(systems.size() > 0, ErrorCode.SYSTEM_NON_EXISTENT);

        if (roleIds.size() > 0) {
            LambdaQueryWrapper<RoleDo> wrapper = new LambdaQueryWrapper<>(RoleDo.class);
            wrapper.in(RoleDo::getRoleId, roleIds);
            Assert.isTrue(roleServer.count(wrapper) == roleIds.size(), ErrorCode.SYS_ROLE_NON_EXISTENT);
        }

        Object ret = transactionTemplate.execute(status -> {
            List<UserRoleDo> userRoleDos = new ArrayList<>();

            Optional.ofNullable(bo.getRoleIds())
                    .ifPresent(list -> list.forEach(item -> userRoleDos.add(new UserRoleDo()
                            .setUserId(userDo.getUserId())
                            .setRoleId(item))));

            Optional.ofNullable(bo.getSysRoleIds())
                    .ifPresent(map -> map.forEach((k, v) -> Optional.ofNullable(v)
                            .ifPresent(list -> list.forEach(item -> {
                                if (!baseRole.contains(item)) {
                                    userRoleDos.add(new UserRoleDo()
                                            .setUserId(userDo.getUserId())
                                            .setRoleId(item)
                                            .setSystemCode(k));
                                }
                            }))));

            userRoleServer.remove(new LambdaQueryWrapper<>(UserRoleDo.class)
                    .eq(UserRoleDo::getUserId, userDo.getUserId()));

            Assert.isTrue(userRoleServer.saveBatch(userRoleDos, 500), ErrorCode.SYS_USER_BIND_ROLE_ERROR);
            return null;
        });
    }

    @Override
    public Map<String, Object> getAttribute(@Valid @NotNull IdBo bo) {
        return null;
    }

    /**
     * 判断用户是否允许保存
     *
     * @param bo
     */
    private void checkUserCanSave(UserBo bo, UserDo oldDo) {
        if (bo.getAccountType() == null) {
            JSONArray accountType = new JSONArray();
            accountType.add(UserAccountType.PRIVILEGE.getValue());
            bo.setAccountType(accountType);
        } else {
            if (bo.getAccountType().size() > 1
                    && bo.getAccountType().contains(UserAccountType.PRIVILEGE.getValue())) {
                throw new BusinessLogicException(ErrorCode.SYS_USER_ACCOUNT_TYPE_ERROR);
            }
        }

        if (oldDo == null) {
            Assert.isFalse(has(
                    new UserSearchBo()
                            .setEq_userEmail(bo.getUserEmail())
            ), ErrorCode.SYS_USER_EMAIL_REPEAT);

            Assert.isFalse(has(
                    new UserSearchBo()
                            .setEq_userName(bo.getUserName())
            ), ErrorCode.SYS_USER_NAME_REPEAT);

            if (StringUtil.isNotEmpty(bo.getUserPhone())) {
                Assert.isFalse(has(
                        new UserSearchBo()
                                .setEq_userPhone(bo.getUserPhone())
                ), ErrorCode.SYS_USER_PHONE_REPEAT);
            }

            if (StringUtil.isNotEmpty(bo.getUserAccount())) {
                if (StringUtil.isNotEmpty(bo.getUserAccount())) {
                    Assert.isFalse(has(
                            new UserSearchBo()
                                    .setEq_userAccount(bo.getUserAccount())
                    ), ErrorCode.SYS_USER_ACCOUNT_REPEAT);
                }
            }
        } else {
            if (bo.getUserEmail() != null && !bo.getUserEmail().equals(oldDo.getUserEmail())) {
                Assert.isFalse(has(
                        new UserSearchBo()
                                .setEq_userEmail(bo.getUserEmail())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_EMAIL_REPEAT);
            }

            if (bo.getUserName() != null && !bo.getUserName().equals(oldDo.getUserName())) {
                Assert.isFalse(has(
                        new UserSearchBo()
                                .setEq_userName(bo.getUserName())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_NAME_REPEAT);
            }

            if (bo.getUserPhone() != null && !bo.getUserPhone().equals(oldDo.getUserPhone())) {
                Assert.isFalse(has(
                        new UserSearchBo()
                                .setEq_userPhone(bo.getUserPhone())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_PHONE_REPEAT);
            }

            if (StringUtil.isNotEmpty(bo.getUserAccount()) && !bo.getUserAccount().equals(oldDo.getUserAccount())) {
                Assert.isFalse(has(
                        new UserSearchBo()
                                .setEq_userAccount(bo.getUserAccount())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_ACCOUNT_REPEAT);
            }
        }
    }

}