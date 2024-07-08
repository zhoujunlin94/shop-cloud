package io.github.zhoujunlin94.server.account.repository.db.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.account.repository.db.entity.Account;
import io.github.zhoujunlin94.server.account.repository.db.mapper.AccountMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Optional;

/**
 * @author zhoujunlin
 * @date 2024/7/8 21:58
 */
@Repository
public class AccountHandler extends TKHandler<AccountMapper, Account> {


    public Integer getBalance(Integer userId) {
        Weekend<Account> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(Account::getUserId, userId);
        weekend.selectProperties("balance");
        return Optional.ofNullable(this.baseMapper.selectOneByExample(weekend)).map(Account::getBalance).orElse(0);
    }

    public boolean reduceBalance(Integer userId, Integer reducePrice) {
        return this.baseMapper.reduceBalance(userId, reducePrice) == 1;
    }

}
