package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Admin_role_menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRoleMenuDao extends tk.mybatis.mapper.common.Mapper<Admin_role_menu> {
}
