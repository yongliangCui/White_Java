package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Admin_role_permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface AdminRolePermissionDao extends Mapper<Admin_role_permission> {
    @Delete("delete from admin_permission where rid = #{rid}")
    void deleteByRid(@Param("rid") Long rid);
}
