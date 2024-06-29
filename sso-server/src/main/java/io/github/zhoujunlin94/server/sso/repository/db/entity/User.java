package io.github.zhoujunlin94.server.sso.repository.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 */
@Data
@Accessors(chain = true)
@Table(name = "meet_user")
public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "pwd")
    private String pwd;
}