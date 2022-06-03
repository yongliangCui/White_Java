package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Admin_menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface AdminMenuDao extends Mapper<Admin_menu> {
    @Select("select * from admin_menu where pid = #{pid}")
    public List<Admin_menu> findByPid(@Param("pid") int pid);
}
