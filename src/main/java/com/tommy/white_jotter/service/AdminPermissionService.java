package com.tommy.white_jotter.service;

import cn.hutool.core.collection.CollectionUtil;
import com.tommy.white_jotter.dao.AdminPermissionDao;
import com.tommy.white_jotter.pojo.Admin_permission;
import com.tommy.white_jotter.pojo.Admin_role;
import com.tommy.white_jotter.pojo.Admin_role_permission;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminPermissionService {
    @Resource
    private UserService userService;
    @Resource
    private AdminPermissionDao adminPermissionDao;
    @Resource
    private AdminRolePermissionService adminRolePermissionService;
    @Resource
    private AdminRoleService adminRoleService;

    public List<Admin_permission> findAll(){
        return adminPermissionDao.selectAll();
    }

    public boolean needFilter(String api){
        List<Admin_permission> list = findAll();
        for (Admin_permission admin_permission : list) {
            if (api.startsWith(admin_permission.getUrl())){
                return true;
            }
        }
        return false;
    }

    public List<Admin_permission> findByRid(Long rid){
        List<Long> pids = adminRolePermissionService.findByRid(rid)
                .stream().map(Admin_role_permission::getPid).collect(Collectors.toList());

        if(CollectionUtil.isEmpty(pids)){
            return null;
        }
        Example example = new Example(Admin_permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",pids);
        return adminPermissionDao.selectByExample(example);
    }


    public Set<String> listByUser(String username){
        List<Long> rids = adminRoleService.listByUser(username)
                .stream().map(Admin_role::getId).collect(Collectors.toList());

        List<Long> pids = adminRolePermissionService.findByRids(rids)
                .stream().map(Admin_role_permission::getPid).collect(Collectors.toList());

        Example example = new Example(Admin_permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", pids);
        Set<String> urls = adminPermissionDao.selectByExample(example)
                .stream().map(Admin_permission::getUrl).collect(Collectors.toSet());
        return urls;
    }

}
