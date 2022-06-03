package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Admin_user_role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminUserRoleDao extends tk.mybatis.mapper.common.Mapper<Admin_user_role> {
    @Select("select * from admin_user_role where uid = #{uid}")
    public List<Admin_user_role> listAllByUid(@Param("uid") Long uid);
}
