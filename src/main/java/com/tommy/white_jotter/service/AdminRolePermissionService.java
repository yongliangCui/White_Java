package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.AdminRolePermissionDao;
import com.tommy.white_jotter.pojo.Admin_permission;
import com.tommy.white_jotter.pojo.Admin_role_permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRolePermissionService {
    @Resource
    private AdminRolePermissionDao adminRolePermissionDao;

    public List<Admin_role_permission> findByRid(Long rid){
        Admin_role_permission admin_role_permission = new Admin_role_permission();
        admin_role_permission.setRid(rid);
        return adminRolePermissionDao.select(admin_role_permission);
    }

    public List<Admin_role_permission> findByRids(List<Long> rid){
        Example example = new Example(Admin_role_permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("rid",rid);
        return adminRolePermissionDao.selectByExample(example);
    }

    @Transactional
    public void savePermChange(Long rid, List<Admin_permission> perms){
        adminRolePermissionDao.deleteByRid(rid);
        List<Long> pids = perms.stream().map(Admin_permission::getId).collect(Collectors.toList());
        Admin_role_permission admin_role_permission = new Admin_role_permission();
        for (Long pid : pids) {
            admin_role_permission.setRid(rid);
            admin_role_permission.setPid(pid);
            adminRolePermissionDao.insertSelective(admin_role_permission);
        }
    }
}
