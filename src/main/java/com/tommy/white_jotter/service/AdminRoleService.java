package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.AdminRoleDao;
import com.tommy.white_jotter.pojo.Admin_role;
import com.tommy.white_jotter.pojo.Admin_user_role;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleService {
    @Resource
    private UserService userService;
    @Resource
    private AdminRoleDao adminRoleDao;
    @Resource
    private AdminUserRoleService adminUserRoleService;
    @Resource
    private AdminRolePermissionService adminRolePermissionService;
    @Resource
    private AdminMenuService adminMenuService;
    @Resource
    private AdminPermissionService adminPermissionService;


    public List<Admin_role> listWithPermAndMenu(){
        List<Admin_role> roles = findAll();
        for (Admin_role role : roles) {
            Long id = role.getId();
            role.setMenus(adminMenuService.findByRid(id));
            role.setPermissions(adminPermissionService.findByRid(id));
        }
        return roles;
    }

    public List<Admin_role> findAll(){
        return adminRoleDao.selectAll();
    }

    public void addOrUpdate(Admin_role admin_role){
        adminRoleDao.updateByPrimaryKey(admin_role);
    }

    public List<Admin_role> listByUser(String username){
        Long uid = userService.findByName(username).getId();
        List<Long> rids = adminUserRoleService.listAllByUid(uid).stream().map(Admin_user_role::getRid).collect(Collectors.toList());
        Example example = new Example(Admin_role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",rids);
        return adminRoleDao.selectByExample(example);
    }
    public Admin_role updateRoleStatus(Admin_role role) {
        Admin_role roleInDB = adminRoleDao.selectByPrimaryKey(role.getId());
        roleInDB.setEnabled(role.getEnabled());
        adminRoleDao.updateByPrimaryKey(roleInDB);
        return roleInDB;
    }

    public void editRole(@RequestBody Admin_role admin_role){
        adminRoleDao.insert(admin_role);
        adminRolePermissionService.savePermChange(admin_role.getId(),admin_role.getPermissions());
    }

}
