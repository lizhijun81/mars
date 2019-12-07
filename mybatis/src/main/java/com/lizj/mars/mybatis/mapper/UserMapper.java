package com.lizj.mars.mybatis.mapper;

import com.lizj.mars.mybatis.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id, name, age from user where id = #{userId}")
    User getId(int userId);

    @Insert("insert into user (name, age) values (#{name}, #{age})")
    void insert(User user);
}
