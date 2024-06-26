package io.github.zhoujunlin94.server.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private Integer id;

    private String nickname;

    @NotBlank(message = "手机号不可为空")
    private String phone;

    @NotBlank(message = "密码不可为空")
    private String pwd;

}