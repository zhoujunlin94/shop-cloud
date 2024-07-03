package io.github.zhoujunlin94.server.sso.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.sso.repository.db.entity.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginLogMapper extends TKMapper<UserLoginLog> {
}