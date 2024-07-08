package io.github.zhoujunlin94.server.account.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhoujunlin
 * @date 2024/7/8 21:59
 */
@Data
@Accessors(chain = true)
public class AccountDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户余额
     */
    private Integer balance;

}
