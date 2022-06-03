package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Admin_role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRoleDao extends tk.mybatis.mapper.common.Mapper<Admin_role> {
    @Select("SELECT * FROM `admin_role` where id in(SELECT rid from admin_user_role where uid = #{uid})")
    public List<Admin_role> findByUid(@Param("uid") Long uid);
}
