package io.github.zhoujunlin94.server.user.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.github.zhoujunlin94.server.user.dto.UserDTO;
import io.github.zhoujunlin94.server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
