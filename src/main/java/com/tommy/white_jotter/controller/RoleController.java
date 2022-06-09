package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.pojo.Admin_role;
import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.AdminPermissionService;
import com.tommy.white_jotter.service.AdminRoleMenuService;
import com.tommy.white_jotter.service.AdminRolePermissionService;
import com.tommy.white_jotter.service.AdminRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RoleController {
    @Resource
    private AdminRoleService adminRoleService;
    @Resource
    private AdminRolePermissionService adminRolePermissionService;
    @Resource
    private AdminRoleMenuService adminRoleMenuService;
    @Resource
    private AdminPermissionService adminPermissionService;

    @GetMapping("/api/admin/role")
    public Result listRoles(){
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermAndMenu());
    }

    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody Admin_role admin_role){
        adminRoleService.updateRoleStatus(admin_role);
        return ResultFactory.buildSuccessResult("更改状态成功");
    }

    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody Admin_role admin_role){
        adminRoleService.addOrUpdate(admin_role);
        adminRolePermissionService.savePermChange(admin_role.getId(),admin_role.getPermissions());
        return ResultFactory.buildSuccessResult("修改role成功");
    }

    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody Admin_role admin_role){
        adminRoleService.editRole(admin_role);
        return ResultFactory.buildSuccessResult("新增成功");
    }

    @GetMapping("/api/admin/role/perm")
    public Result listPerms(){
        return ResultFactory.buildSuccessResult(adminPermissionService.findAll());
    }

    @PutMapping("/api/admin/role/menu")
    public Result updateRoleMenu(@RequestParam Long rid, @RequestBody Map<String, List<Long>> menuIds){
        adminRoleMenuService.updateRoleMenu(rid,menuIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }


}
