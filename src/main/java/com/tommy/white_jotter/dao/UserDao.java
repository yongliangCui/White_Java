package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao extends tk.mybatis.mapper.common.Mapper<User> {
    @Select("select * from user where username = #{username}")
    public User findByName(@Param("username") String username);
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByNameAndPassword(@Param("username") String username,@Param("password") String password);
    @Select("select * from user")
    @Results(id = "usermap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "salt",column = "salt"),
            @Result(property = "name",column = "name"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "email",column = "email"),
            @Result(property = "enable",column = "enable"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.tommy.white_jotter.dao.AdminRoleDao.findByUid",
                    fetchType = FetchType.EAGER))
    }
    )
    public List<User> list();
}
