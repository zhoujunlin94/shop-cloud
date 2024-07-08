package io.github.zhoujunlin94.server.account.repository.db.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.account.repository.db.entity.Account;
import io.github.zhoujunlin94.server.account.repository.db.mapper.AccountMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujunlin
 * @date 2024/7/8 21:58
 */
@Repository
public class AccountHandler extends TKHandler<AccountMapper, Account> {

}
