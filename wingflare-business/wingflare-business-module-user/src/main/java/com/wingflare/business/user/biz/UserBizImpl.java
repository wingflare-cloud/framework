package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.api.security.annotation.Desensitize;
import com.wingflare.api.security.annotation.DesensitizeGroups;
import com.wingflare.api.security.enums.SensitiveType;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.UserConvert;
import com.wingflare.business.user.db.RoleDO;
import com.wingflare.business.user.db.UserDO;
import com.wingflare.business.user.db.UserRoleDO;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.business.user.service.UserServer;
import com.wingflare.business.user.wrapper.UserWrapper;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dict.UserAccountType;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.facade.module.user.event.UserChangePwdEvent;
import com.wingflare.facade.module.user.event.UserChangedPwdEvent;
import com.wingflare.facade.module.user.event.UserCreateEvent;
import com.wingflare.facade.module.user.event.UserDeleteEvent;
import com.wingflare.facade.module.user.event.UserDeletedEvent;
import com.wingflare.facade.module.user.event.UserUpdateEvent;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.configure.properties.BusinessSystemProperties;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.math.BigInteger;
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
@Validated
public class UserBizImpl implements UserBiz {

    private final UserServer userServer;

    private final BusinessSystemProperties businessSystemProperties;

    private final TransactionTemplate transactionTemplate;

    private final EventPublisher eventPublisher;

    private final UserAuthUtil userAuthUtil;

    private final UserRoleServer userRoleServer;

    private final RoleServer roleServer;

    private static final Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

    public UserBizImpl(UserServer userServer, BusinessSystemProperties businessSystemProperties, TransactionTemplate transactionTemplate,
                       EventPublisher eventPublisher, UserAuthUtil userAuthUtil, UserRoleServer userRoleServer, RoleServer roleServer) {
        this.userServer = userServer;
        this.businessSystemProperties = businessSystemProperties;
        this.transactionTemplate = transactionTemplate;
        this.eventPublisher = eventPublisher;
        this.userAuthUtil = userAuthUtil;
        this.userRoleServer = userRoleServer;
        this.roleServer = roleServer;
    }

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
    public PageDto<UserDTO> list(@Valid UserSearchBO bo) {
        LambdaQueryWrapper<UserDO> queryWrapper = UserWrapper.getLambdaQueryWrapper(bo);
        queryWrapper.orderByDesc(UserDO::getUserId);

        IPage<UserDO> iPage = userServer.page(
                userServer.createPage(bo),
                queryWrapper
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
    public UserDTO get(@Valid @NotNull IdBo bo) {
        UserDO userDo = userServer.getById(bo.getId());
        UserDTO userDto = null;

        if (userDo != null) {
            userDto = UserConvert.convert.doToDto(
                    userServer.getById(bo.getId()));
            LambdaQueryWrapper<UserRoleDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserRoleDO::getUserId, bo.getId())
                    .eq(UserRoleDO::getIsDelete, 0);
            List<UserRoleDO> userRoleDOList = userRoleServer.list(wrapper);

            if (CollectionUtil.isNotEmpty(userRoleDOList)) {
                userDto.setUserRole(new ArrayList<>(userRoleDOList.stream().map(UserRoleDO::getRoleId).toList()));
            }
        }

        return userDto;
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
    public UserDTO getOnlyOne(@Valid @NotNull UserSearchBO bo) {
        return UserConvert.convert.doToDto(
                userServer.getOne(
                        UserWrapper.getLambdaQueryWrapper(bo)
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
    public UserDTO delete(@Valid @NotNull IdBo bo) {
        UserDTO dto = deleteHandle(bo);
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
    public UserDTO deleteHandle(@Valid @NotNull IdBo bo) {
        return transactionTemplate.execute(status -> {
            UserDTO dto = null;
            UserDO userDo = userServer.getById(bo.getId());

            if (userDo != null) {
                userServer.removeById(bo.getId());
                dto = UserConvert.convert.doToDto(userDo);
                eventPublisher.publishEvent(new UserDeleteEvent(dto));
            }

            return dto;
        });
    }

    public void afterDelete(UserDTO dto) {
        Optional.ofNullable(dto)
                .ifPresent(val -> {
                    try {
                        eventPublisher.publishEvent(new UserDeletedEvent(val));
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
    public UserDTO create(@Valid @NotNull UserBO bo) {
        UserDTO dto = createHandle(bo);
        afterCreate(bo, dto);
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
    public UserDTO createHandle(@Valid @NotNull UserBO bo) {
        return transactionTemplate.execute(status -> {
            checkUserCanSave(bo, null);
            bo.setSuperAdministrator(0);
            bo.setUserPasswd(UserAuthUtil.encryptPassword(bo.getUserPasswd()));
            UserDO userDo = UserConvert.convert.boToDo(bo);
            Assert.isTrue(userServer.save(userDo), ErrorCode.SYS_USER_CREATE_ERROR);

            if (CollectionUtil.isNotEmpty(bo.getUserRole())) {
                List<UserRoleDO> userRoleDOList = new ArrayList<>();

                bo.getUserRole().forEach(roleId -> {
                   userRoleDOList.add(
                           new UserRoleDO()
                                   .setUserId(userDo.getUserId())
                                   .setRoleId(roleId)
                   );
                });

                userRoleServer.saveBatch(userRoleDOList);
            }

            UserDTO userDto = UserConvert.convert.doToDto(userDo);
            eventPublisher.publishEvent(new UserCreateEvent(bo, userDto));
            return userDto;
        });
    }

    public void afterCreate(UserBO bo, UserDTO dto) {
        try {
            eventPublisher.publishEvent(new UserCreateEvent(bo, dto));
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
    public UserDTO update(@Valid @NotNull UserBO bo) {
        UserDTO dto = updateHandle(bo);
        afterUpdate(bo, dto);
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
    public UserDTO updateHandle(@Valid @NotNull UserBO bo) {
        return transactionTemplate.execute(status -> {
            UserDO oldUserDO = userServer.getById(bo.getUserId());
            UserDTO userDto = null;

            if (oldUserDO == null) {
                throw new DataNotFoundException("user.data.notfound");
            }

            checkUserCanSave(bo, oldUserDO);
            bo.setUserPasswd(null);
            bo.setSuperAdministrator(null);

            if (StringUtil.isEmpty(bo.getUserEmail())) {
                bo.setUserEmail(null);
            }

            if (StringUtil.isEmpty(bo.getUserPhone())) {
                bo.setUserPhone(null);
            }

            UserDO userDo = UserConvert.convert.boToDo(bo);
            UserDO oldField = oldUserDO.setOnNew(userDo);

            userRoleServer.deleteByUserId(bo.getUserId());

            if (CollectionUtil.isNotEmpty(bo.getUserRole())) {
                List<UserRoleDO> userRoleDOList = new ArrayList<>();

                bo.getUserRole().forEach(roleId -> {
                    userRoleDOList.add(
                            new UserRoleDO()
                                    .setUserId(userDo.getUserId())
                                    .setRoleId(roleId)
                    );
                });

                userRoleServer.saveBatch(userRoleDOList);
            }

            if (oldField != null) {
                Assert.isTrue(userServer.updateById(oldUserDO), ErrorCode.SYS_USER_UPDATE_ERROR);
                userDto = UserConvert.convert.doToDto(oldUserDO);
                eventPublisher.publishEvent(new UserUpdateEvent(UserConvert.convert.doToDto(oldField), userDto));
            }

            return userDto;
        });
    }

    public void afterUpdate(UserBO bo, UserDTO dto) {
        Optional.ofNullable(dto)
                .ifPresent(val -> {
                    try {
                        eventPublisher.publishEvent(new UserUpdateEvent(UserConvert.convert.boToDto(bo), val));
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
    public UserDTO updatePasswd(@Valid @NotNull UpdatePasswdBO bo) {
        if (userAuthUtil.getUser() != null && !userAuthUtil.getUser().isSuperAdmin()) {
            BigInteger limitUserId = userAuthUtil.getUser()
                    .getUserId();
            Assert.isTrue(bo.getUserId().compareTo(limitUserId) != 0, ErrorCode.SYS_USER_UPDATE_PASSWD_NO_POWER);
        }

        UserDTO dto = updatePasswdHandle(bo);
        afterUpdatePasswd(dto);
        return dto;
    }

    /**
     * 判断是否存在符合条件的系统用户
     *
     * @param bo 查询参数
     * @return 系统用户
     */
    public boolean has(UserSearchBO bo) {
        return userServer.has(
                UserWrapper.getLambdaQueryWrapper(bo)
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
    public UserDTO updatePasswdHandle(@Valid @NotNull UpdatePasswdBO bo) {
        return transactionTemplate.execute(status -> {
            UserDO userDo = userServer.getById(bo.getUserId());
            Assert.isTrue(userDo != null, ErrorCode.SYS_USER_NON_EXISTENT);

            if (StringUtil.isNotEmpty(bo.getOldPasswd())) {
                Assert.isTrue(
                        UserAuthUtil.matchesPassword(bo.getOldPasswd(), userDo.getUserPasswd()),
                        ErrorCode.SYS_USER_OLD_PWD_ERR
                );
            }

            userDo.setUserPasswd(UserAuthUtil.encryptPassword(bo.getPasswd()));
            Assert.isTrue(userServer.updateById(userDo), ErrorCode.SYS_USER_UPDATE_ERROR);
            UserDTO userDto = UserConvert.convert.doToDto(userDo);
            eventPublisher.publishEvent(new UserChangePwdEvent(userDto));
            return userDto;
        });
    }


    public void afterUpdatePasswd(UserDTO dto) {
        try {
            eventPublisher.publishEvent(new UserChangedPwdEvent(dto));
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
    public UserDTO getUserByLoginName(@NotBlank String loginName) {
        UserDO userDo = userServer.getOne(
                new LambdaQueryWrapper<UserDO>()
                        .eq(UserDO::getUserEmail, loginName)
                        .or()
                        .eq(UserDO::getUserPhone, loginName)
                        .or()
                        .eq(UserDO::getUserAccount, loginName)
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
    public void userBindRole(@Valid @NotNull UserBindRoleBO bo) {
        UserDO userDo = userServer.getById(bo.getUserId());
        Assert.isTrue(userDo != null, ErrorCode.SYS_USER_NON_EXISTENT);
        Assert.isTrue(userDo.getAccountType().contains(UserAccountType.PRIVILEGE.getValue()),
                ErrorCode.SYS_USER_NON_PRIVILEGE_NOT_BIND_ROLE);

        Set<BigInteger> baseRole = new HashSet<>(Optional.ofNullable(bo.getRoleIds()).orElse(new ArrayList<>()));
        Set<BigInteger> roleIds = new HashSet<>();
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
            LambdaQueryWrapper<RoleDO> wrapper = new LambdaQueryWrapper<>(RoleDO.class);
            wrapper.in(RoleDO::getRoleId, roleIds);
            Assert.isTrue(roleServer.count(wrapper) == roleIds.size(), ErrorCode.SYS_ROLE_NON_EXISTENT);
        }

        Object ret = transactionTemplate.execute(status -> {
            List<UserRoleDO> userRoleDOS = new ArrayList<>();

            Optional.ofNullable(bo.getRoleIds())
                    .ifPresent(list -> list.forEach(item -> userRoleDOS.add(new UserRoleDO()
                            .setUserId(userDo.getUserId())
                            .setRoleId(item))));

            Optional.ofNullable(bo.getSysRoleIds())
                    .ifPresent(map -> map.forEach((k, v) -> Optional.ofNullable(v)
                            .ifPresent(list -> list.forEach(item -> {
                                if (!baseRole.contains(item)) {
                                    userRoleDOS.add(new UserRoleDO()
                                            .setUserId(userDo.getUserId())
                                            .setRoleId(item)
                                            .setSystemCode(k));
                                }
                            }))));

            userRoleServer.remove(new LambdaQueryWrapper<>(UserRoleDO.class)
                    .eq(UserRoleDO::getUserId, userDo.getUserId()));

            Assert.isTrue(userRoleServer.saveBatch(userRoleDOS, 500), ErrorCode.SYS_USER_BIND_ROLE_ERROR);
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
    private void checkUserCanSave(UserBO bo, UserDO oldDo) {
        if (bo.getAccountType() != null) {
            if (bo.getAccountType().size() > 1
                    && bo.getAccountType().contains(UserAccountType.PRIVILEGE.getValue())) {
                throw new BusinessLogicException(ErrorCode.SYS_USER_ACCOUNT_TYPE_ERROR);
            }
        } else {
            if (!bo.getUserId().equals(oldDo.getUserId())) {
                throw new BusinessLogicException(ErrorCode.SYS_USER_CAN_NOT_UPDATE_USER);
            }
        }

        if (oldDo == null) {
            Assert.isFalse(has(
                    new UserSearchBO()
                            .setEq_userEmail(bo.getUserEmail())
            ), ErrorCode.SYS_USER_EMAIL_REPEAT);

            Assert.isFalse(has(
                    new UserSearchBO()
                            .setEq_userName(bo.getUserName())
            ), ErrorCode.SYS_USER_NAME_REPEAT);

            if (StringUtil.isNotEmpty(bo.getUserPhone())) {
                Assert.isFalse(has(
                        new UserSearchBO()
                                .setEq_userPhone(bo.getUserPhone())
                ), ErrorCode.SYS_USER_PHONE_REPEAT);
            }

            if (StringUtil.isNotEmpty(bo.getUserAccount())) {
                if (StringUtil.isNotEmpty(bo.getUserAccount())) {
                    Assert.isFalse(has(
                            new UserSearchBO()
                                    .setEq_userAccount(bo.getUserAccount())
                    ), ErrorCode.SYS_USER_ACCOUNT_REPEAT);
                }
            }
        } else {
            if (bo.getUserEmail() != null && !bo.getUserEmail().equals(oldDo.getUserEmail())) {
                Assert.isFalse(has(
                        new UserSearchBO()
                                .setEq_userEmail(bo.getUserEmail())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_EMAIL_REPEAT);
            }

            if (bo.getUserName() != null && !bo.getUserName().equals(oldDo.getUserName())) {
                Assert.isFalse(has(
                        new UserSearchBO()
                                .setEq_userName(bo.getUserName())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_NAME_REPEAT);
            }

            if (bo.getUserPhone() != null && !bo.getUserPhone().equals(oldDo.getUserPhone())) {
                Assert.isFalse(has(
                        new UserSearchBO()
                                .setEq_userPhone(bo.getUserPhone())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_PHONE_REPEAT);
            }

            if (StringUtil.isNotEmpty(bo.getUserAccount()) && !bo.getUserAccount().equals(oldDo.getUserAccount())) {
                Assert.isFalse(has(
                        new UserSearchBO()
                                .setEq_userAccount(bo.getUserAccount())
                                .setNeq_userId(bo.getUserId())
                ), ErrorCode.SYS_USER_ACCOUNT_REPEAT);
            }
        }
    }

}