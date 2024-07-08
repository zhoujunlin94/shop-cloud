package io.github.zhoujunlin94.server.account.service.impl;

import io.github.zhoujunlin94.meet.common.exception.MeetException;
import io.github.zhoujunlin94.server.account.repository.db.handler.AccountHandler;
import io.github.zhoujunlin94.server.account.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhoujunlin
 * @date 2024/7/8 22:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountHandler accountHandler;

    @Override
    @Transactional(transactionManager = "accountTransactionManager", rollbackFor = Exception.class)
    public void reduceBalance(Integer userId, Integer reducePrice) {
        log.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());
        // 检查余额
        checkBalance(userId, reducePrice);
        log.info("[reduceBalance] 开始扣减用户 {} 余额", userId);

        // 扣除余额
        boolean result = accountHandler.reduceBalance(userId, reducePrice);
        if (!result) {
            log.warn("[reduceBalance] 扣除用户 {} 余额失败", userId);
            throw MeetException.meet("余额不足，扣除失败");
        }
        // 扣除成功
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }

    private void checkBalance(Integer userId, Integer price) {
        log.info("[checkBalance] 检查用户 {} 余额", userId);
        Integer balance = accountHandler.getBalance(userId);
        if (balance < price) {
            log.warn("[checkBalance] 用户 {} 余额不足，当前余额:{}", userId, balance);
            throw MeetException.meet("用户余额不足");
        }
    }

}
