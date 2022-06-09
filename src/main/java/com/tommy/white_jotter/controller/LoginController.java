package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.pojo.User;
import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class LoginController {
    @Resource
    private UserService userService;
    @PostMapping("/api/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user1 = userService.findByName(username);
            if (!user1.isEnabled()){
                return ResultFactory.buildFailResult("该用户已被禁用");
            }
            return ResultFactory.buildSuccessResult(username);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账户不存在");
        }
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody User user){
        int statu = userService.register(user);
        if (statu == 0){
            return ResultFactory.buildFailResult("用户名和密码不能为空");
        }
        if (statu == 1){
            return ResultFactory.buildSuccessResult("注册成功");
        }
        if (statu == 2){
            return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @GetMapping("api/logout")
    public Result loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("登出成功");
    }
    @GetMapping("/api/authentication")
    public String authentication(){
        return "OK";
    }

}
