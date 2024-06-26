package io.github.zhoujunlin94.server.user.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import io.github.zhoujunlin94.meet.common.exception.MeetException;
import io.github.zhoujunlin94.meet.common.util.EncryptUtil;
import io.github.zhoujunlin94.server.user.dto.UserDTO;
import io.github.zhoujunlin94.server.user.repository.db.entity.User;
import io.github.zhoujunlin94.server.user.repository.db.handler.UserHandler;
import io.github.zhoujunlin94.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024-06-26-14:16
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserHandler userHandler;

    @Override
    public Integer register(UserDTO userDTO) {
        User user = userHandler.selectByPhone(userDTO.getPhone());
        if (Objects.nonNull(user)) {
            return user.getId();
        }
        User entity = BeanUtil.toBean(userDTO, User.class);
        entity.setPwd(EncryptUtil.encryptMD5(userDTO.getPwd()));
        userHandler.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public SaTokenInfo login(UserDTO userDTO) {
        User user = userHandler.selectByPhone(userDTO.getPhone());
        if (Objects.isNull(user) || !user.getPwd().equals(EncryptUtil.encryptMD5(userDTO.getPwd()))) {
            throw MeetException.meet("用户不存在或者密码错误");
        }
        StpUtil.login(user.getId());
        return StpUtil.getTokenInfo();
    }

}
