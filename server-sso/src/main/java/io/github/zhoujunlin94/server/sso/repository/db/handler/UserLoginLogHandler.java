package io.github.zhoujunlin94.server.sso.repository.db.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.sso.repository.db.entity.UserLoginLog;
import io.github.zhoujunlin94.server.sso.repository.db.mapper.UserLoginLogMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujunlin
 * @date 2024/7/3 21:53
 */
@Repository
public class UserLoginLogHandler extends TKHandler<UserLoginLogMapper, UserLoginLog> {


}
