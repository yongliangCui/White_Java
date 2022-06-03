package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.AdminUserRoleDao;
import com.tommy.white_jotter.pojo.Admin_user_role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserRoleService {
    @Resource
    private AdminUserRoleDao adminUserRoleDao;

    public List<Admin_user_role> listAllByUid(Long uid){
        return adminUserRoleDao.listAllByUid(uid);
    }

    @Transactional
    public void saveRoleChanges(Long uid,List<Admin_user_role> roles){
        adminUserRoleDao.deleteByPrimaryKey(uid);
        Admin_user_role admin_user_role = new Admin_user_role();
        admin_user_role.setUid(uid);
        List<Long> rids = roles.stream().map(Admin_user_role::getId).collect(Collectors.toList());
        for (Long rid : rids) {
            admin_user_role.setRid(rid);
            adminUserRoleDao.insert(admin_user_role);
        }
    }

}
