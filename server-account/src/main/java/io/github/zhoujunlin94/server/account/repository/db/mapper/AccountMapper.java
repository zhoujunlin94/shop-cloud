package io.github.zhoujunlin94.server.account.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.account.repository.db.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper extends TKMapper<Account> {

    @Update("UPDATE shop_account SET balance = balance - #{reducePrice} WHERE user_id = #{userId} AND balance >= #{reducePrice}")
    int reduceBalance(@Param("userId") Integer userId, @Param("reducePrice") Integer reducePrice);

}