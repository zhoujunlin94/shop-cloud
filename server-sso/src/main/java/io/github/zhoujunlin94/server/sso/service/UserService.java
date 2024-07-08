package io.github.zhoujunlin94.server.sso.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.github.zhoujunlin94.server.sso.dto.UserDTO;

/**
 * @author zhoujunlin
 * @date 2024-06-26-14:16
 */
public interface UserService {

    Integer register(UserDTO userDTO);

    SaTokenInfo login(UserDTO userDTO);

    UserDTO getUser(Integer userId);

}
