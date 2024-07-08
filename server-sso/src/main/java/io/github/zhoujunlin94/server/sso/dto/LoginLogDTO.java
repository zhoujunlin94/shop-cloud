package io.github.zhoujunlin94.server.sso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2024/7/3 21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLogDTO {

    private Integer userId;

    private String loginIp;

}
