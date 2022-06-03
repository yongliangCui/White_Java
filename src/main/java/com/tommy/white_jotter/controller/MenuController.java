package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.AdminMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MenuController {
    @Resource
    private AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public Result menu(){
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }

    @GetMapping("/api/admin/role/menu")
    public Result listAllMenu(){
        return ResultFactory.buildSuccessResult(adminMenuService.findByRid(1L));
    }
}
