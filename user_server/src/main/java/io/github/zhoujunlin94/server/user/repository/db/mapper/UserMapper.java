package io.github.zhoujunlin94.server.user.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.user.repository.db.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends TKMapper<User> {
}