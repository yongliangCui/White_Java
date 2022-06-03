package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.pojo.User;
import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/api/admin/user")
    public Result listUsers(){
        return ResultFactory.buildSuccessResult(userService.listAllByUid());
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUser(@RequestBody @Valid User user){
        userService.updateStatu(user);
        return ResultFactory.buildSuccessResult("更新状态成功");
    }
    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody User user){
        userService.resetPassword(user);
        return ResultFactory.buildSuccessResult("密码修改成功");
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody User user){
        userService.updateUser(user);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }


}
