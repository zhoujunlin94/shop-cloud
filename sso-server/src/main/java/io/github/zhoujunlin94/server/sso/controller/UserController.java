package io.github.zhoujunlin94.server.sso.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.github.zhoujunlin94.server.sso.dto.UserDTO;
import io.github.zhoujunlin94.server.sso.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhoujunlin
 * @date 2024-06-26-14:18
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public Integer register(@RequestBody @Valid UserDTO userDTO) {
        userDTO.setId(null);
        return userService.register(userDTO);
    }

    @PostMapping("login")
    public SaTokenInfo login(@RequestBody @Valid UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @GetMapping("getUser")
    public UserDTO getUser() {
        return userService.getUser(StpUtil.getLoginIdAsInt());
    }

}
