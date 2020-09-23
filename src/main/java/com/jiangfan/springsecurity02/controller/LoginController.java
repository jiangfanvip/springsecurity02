package com.jiangfan.springsecurity02.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020-8-21 10:05
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess() {
        return this.getUsername()+"登录成功";
    }

    @RequestMapping(value = "/user/resources01", produces = {"text/plain;charset=UTF-8"})
    public String getResources01() {
        return "访问资源01";
    }

    @RequestMapping(value = "/all/resources02", produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAnyAuthority('p1')")  //这个方法拥有P1权限的用户才可以访问
    public String getResources02() {
        return "访问资源02";
    }

    @RequestMapping(value = "/user/p1", produces = {"text/plain;charset=UTF-8"})
    public String userp1() {
        return "..............userp1..........";
    }


    @RequestMapping(value = "/user/p2", produces = {"text/plain;charset=UTF-8"})
    public String userp2() {
        return "................userp2........";
    }

    private String getUsername() {
        //得到当前对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();  //获取登录用户身份
        if (principal == null) {
            return "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            return username;

        }
        return principal.toString();

    }
}