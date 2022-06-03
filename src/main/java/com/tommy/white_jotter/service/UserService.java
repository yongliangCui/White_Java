package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.UserDao;
import com.tommy.white_jotter.pojo.Admin_role;
import com.tommy.white_jotter.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User findByName(String username){
        return userDao.findByName(username);
    }

    public User findByNameAndPassword(String username,String password){
        return userDao.findByNameAndPassword(username,password);
    }

    public int register(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return 0;
        }
        User resultUser = userDao.findByName(username);
        if (resultUser == null){
            return 2;
        }
//      生成加密所用的salt
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePassword = new SimpleHash("md5",password,salt,times).toString();
        user.setPassword(encodePassword);
        user.setSalt(salt);

        userDao.insert(user);
        return 1;
    }

    public void updateStatu(User user){
        User user1 = userDao.findByName(user.getUsername());
        user1.setEnabled(user.isEnabled());
        userDao.updateByPrimaryKey(user1);
    }

    public User resetPassword(User user){
        User userInDB = userDao.findByName(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        userDao.updateByPrimaryKeySelective(userInDB);
        return userInDB;
    }
//权限方面未改
    public void updateUser(User user){
        User userDB = userDao.findByName(user.getUsername());
        userDB.setName(user.getName());
        userDB.setEmail(user.getName());
        userDB.setPhone(user.getPhone());
        userDao.updateByPrimaryKeySelective(userDB);
    }

    public List<User> listAllByUid(){
        return userDao.list();
    }

}
