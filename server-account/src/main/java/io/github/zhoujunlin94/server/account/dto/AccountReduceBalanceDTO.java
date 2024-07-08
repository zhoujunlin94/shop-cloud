package io.github.zhoujunlin94.server.account.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2023年09月23日 11:24
 * @desc
 */
@Data
public class AccountReduceBalanceDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户id不可为空")
    private Integer userId;

    /**
     * ø
     * 扣减金额
     */
    @NotNull(message = "扣减金额不可为空")
    private Integer reducePrice;

}
