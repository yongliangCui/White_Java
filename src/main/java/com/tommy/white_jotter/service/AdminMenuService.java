package com.tommy.white_jotter.service;

import cn.hutool.core.collection.CollectionUtil;
import com.tommy.white_jotter.dao.AdminMenuDao;
import com.tommy.white_jotter.dao.AdminRoleMenuDao;
import com.tommy.white_jotter.pojo.Admin_menu;
import com.tommy.white_jotter.pojo.Admin_role_menu;
import com.tommy.white_jotter.pojo.Admin_user_role;
import com.tommy.white_jotter.pojo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    private AdminMenuDao adminMenuDao;
    @Resource
    private UserService userService;
    @Resource
    private AdminUserRoleService adminUserRoleService;
    @Resource
    private AdminRoleMenuService adminRoleMenuService;

    public Admin_menu findById(int id){
        Admin_menu admin_menu = adminMenuDao.selectByPrimaryKey(id);
        return admin_menu;
    }
    public List<Admin_menu> findByPid(Long pid){
        Admin_menu admin_menu = new Admin_menu();
        admin_menu.setParentId(pid);
        return adminMenuDao.select(admin_menu);
    }
    public List<Admin_menu> getMenusByCurrentUser(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByName(username);

        List<Long> rids = adminUserRoleService.listAllByUid(user.getId()).stream().map(Admin_user_role::getRid).collect(Collectors.toList());
        List<Long> mids = adminRoleMenuService.findByRid(rids).stream().map(Admin_role_menu::getMid).collect(Collectors.toList());
        List<Admin_menu> list = findById(mids);
        handleMenus(list);
        return list;
    }

    public List<Admin_menu> findByRid(Long rid){
        List<Long> mids = adminRoleMenuService.findByRid(rid).stream().map(Admin_role_menu::getMid).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(mids)){
            return null;
        }
        List<Admin_menu> list = findById(mids);
        return list;
    }

    List<Admin_menu> findById(List<Long> mids){
        Example example = new Example(Admin_menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",mids);
        List<Admin_menu> list = adminMenuDao.selectByExample(example);
        return list;
    }


    public void handleMenus(List<Admin_menu> menus) {
        menus.forEach(m -> {
            List<Admin_menu> children = findByPid(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }

}
