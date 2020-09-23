package com.jiangfan.springsecurity02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * spring security config
 *
 * @author LENOVO
 * @since JDK 1.8
 */
@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)  //标识支持@secure注解
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)  //标识支持@preauthorize注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {  //继承WebSecurityConfigurerAdapter
   // 定义用户信息服务来查询用户信息--------------spring security帮我们定义好了UserDetailsService
    //我们需要让spring security验证账号密码，只需要申明就好，然后告诉它是通过数据库查还是本地查
   // @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(); //基于内存模式
        UserDetails jiangfan= User.withUsername("jiangfan").password("123456").authorities("p1").build();//构建用户并设置权限(相当于数据库的用户)
        UserDetails lisi = User.withUsername("lisi").password("520123").authorities("p2").build();//构建用户并设置权限(相当于数据库的用户)
        inMemoryUserDetailsManager.createUser(jiangfan);  //创建构建好的用户
        inMemoryUserDetailsManager.createUser(lisi);//创建构建好的用户
        return inMemoryUserDetailsManager;
    }
    //密码编码器 来核对密码  passwordEncoder,纯字符串比对方式\
    @Bean
    public PasswordEncoder passwordEncoder() {
     //    return  NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();  // BCrypt方式的验证
    }

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //spring security为了防csrf跨域请求的发生。限制除了get以外大多数方法，所以关闭csrf
                .authorizeRequests()   //基于HTTP请求授权请求的认证
               .antMatchers("/user/p1").hasAuthority("p1")  //访问/user/p1的用户需要p1权限
               .antMatchers("/user/p2").hasAuthority("p2")  //访问/user/p2的用户需要p1权限
                .antMatchers("/user/**").authenticated() //所有/user/**的请求资源必须认证通过
                .anyRequest().permitAll() //请求许可  其它请求资源可以放行
                .and().formLogin() //允许表单登录
                .loginPage("/login-view")   //告诉spring security 访问login-view时候跳转到login.jsp
                .loginProcessingUrl("/login") //login.jsp的登录页面提交到/login来处理请求
                .successForwardUrl("/login-success")//登录成功跳转页面地址
              //  .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);  默认就是创建session
              //退出成功会清理session以及security上下文
            //    .and().logout().logoutUrl("/logoutAbc").logoutSuccessUrl("/logoutsuccess");//定义退出的URL以及退出成功跳转页

        ;

    }
}