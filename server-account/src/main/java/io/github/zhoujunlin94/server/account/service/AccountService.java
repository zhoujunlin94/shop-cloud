package io.github.zhoujunlin94.server.account.service;

/**
 * @author zhoujunlin
 * @date 2024/7/8
 */
public interface AccountService {

    void reduceBalance(Integer userId, Integer reducePrice);

}
