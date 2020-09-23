package com.jiangfan.springsecurity02.services;

import com.jiangfan.springsecurity02.dao.UserDao;
import com.jiangfan.springsecurity02.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * ClassName: MyUserDetailsServie
 * Description:
 * date: 2020-8-24 20:43
 * 实现根据用户名来查询用户信息
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@Service
public class MyUserDetailsServie implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    //实现查用户的方法 返回UserDetails  ,后期去查数据库
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //   System.out.println("username====" + username);
        UserDto userDto = userDao.quertUser(username);
        if (userDto == null) {
            return null;
        }
        List<String> pers = userDao.queryUserPerission(String.valueOf(userDto.getId()));
        String[]  str = new String[pers.size()];

        //模拟从数据库取用户然后spring security 进行验证
        UserDetails hello = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(pers.toArray(str)).build();

        //模拟从数据库取用户然后spring security 进行验证
        // UserDetails hello = User.withUsername("zhangsan").password("222").authorities("p1").build();

        return hello;

    }
}