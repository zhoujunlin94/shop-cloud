package io.github.zhoujunlin94.server.user.repository.db.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.user.repository.db.entity.User;
import io.github.zhoujunlin94.server.user.repository.db.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

/**
 * @author zhoujunlin
 * @date 2024-06-26-14:15
 */
@Repository
public class UserHandler extends TKHandler<UserMapper, User> {

    public User selectByPhone(String phone) {
        Weekend<User> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(User::getPhone, phone);
        return this.baseMapper.selectOneByExample(weekend);
    }

}
