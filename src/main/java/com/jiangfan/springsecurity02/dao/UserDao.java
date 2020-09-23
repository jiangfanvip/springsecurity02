package com.jiangfan.springsecurity02.dao;

import com.jiangfan.springsecurity02.model.PermissionDto;
import com.jiangfan.springsecurity02.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDto quertUser(String name) {
        String sql = "select * from  t_user   where username=?";
        List<UserDto> query = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(UserDto.class));
        return query.size() > 0 ? query.get(0) : null;
    }

    public List<String> queryUserPerission(String userId) {
        String sql = "SELECT n1.* from t_role_permission n  left join t_permission  n1 on n.permission_id=n1.id where n.role_id in(\n" +
                "SELECT  role_id  from t_user_role e  where e.user_id in(\n" +
                "SELECT id from  t_user  where id=?\n" +
                "))  \n";
        List<PermissionDto> query = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> lists = new ArrayList<>();
        query.forEach(c -> lists.add(c.getCode()));
        return lists;

    }

}
