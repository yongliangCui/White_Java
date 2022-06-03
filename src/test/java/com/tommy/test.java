package com.tommy;

import com.tommy.white_jotter.WhiteJotterApplication;
import com.tommy.white_jotter.dao.AdminPermissionDao;
import com.tommy.white_jotter.dao.BookDao;
import com.tommy.white_jotter.pojo.Book;
import com.tommy.white_jotter.redis.RedisService;
import com.tommy.white_jotter.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WhiteJotterApplication.class)
public class test {
    @Resource
    private RedisService redisService;
    @Resource
    private AdminRolePermissionService adminRolePermissionService;
    @Resource
    private AdminPermissionService adminPermissionService;
    @Resource
    private AdminRoleService adminRoleService;

    @Test
    public void test1(){
        System.out.println(adminRolePermissionService.findByRid(1L));
    }
    @Test
    public void test2(){
        System.out.println(adminRoleService.listWithPermAndMenu());;
    }
    @Test
    public void getName(){
        System.out.println(adminPermissionService.findByRid(1L));
    }

}
