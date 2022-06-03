package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.AdminRoleMenuDao;
import com.tommy.white_jotter.pojo.Admin_role_menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminRoleMenuService {
    @Resource
    private AdminRoleMenuDao adminRoleMenuDao;

    public List<Admin_role_menu> findByRid(Long rid){
        Admin_role_menu current = new Admin_role_menu();
        current.setRid(rid);
        return adminRoleMenuDao.select(current);
    }

    public List<Admin_role_menu> findByRid(List<Long> rids){
        Example example = new Example(Admin_role_menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("rid",rids);
        List<Admin_role_menu> list = adminRoleMenuDao.selectByExample(example);
        return list;
    }

    public void save(Admin_role_menu admin_role_menu){
        adminRoleMenuDao.insert(admin_role_menu);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(Long rid, Map<String, List<Long>> menusIds) {
        adminRoleMenuDao.deleteByPrimaryKey(rid);
        for (Long id : menusIds.get("menusIds")) {
            Admin_role_menu rm = new Admin_role_menu();
            rm.setRid(rid);
            rm.setMid(id);
            adminRoleMenuDao.insert(rm);
        }
    }



}
