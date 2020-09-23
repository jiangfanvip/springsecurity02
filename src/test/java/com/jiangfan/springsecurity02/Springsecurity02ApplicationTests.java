package com.jiangfan.springsecurity02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springsecurity02ApplicationTests {

    @Test
    public void testBCrypt() {
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt()); //对密码进行加密
        System.out.println(hashpw);
        new BCryptPasswordEncoder().encode("123");
//        boolean checkpw = BCrypt.checkpw("123123","$2a$10$J6jnUgOBOAzmwjmNEIsdu.eplg8u6SG/v7uiuG4YFcw.jBgHZXWPW");
//        System.out.println(checkpw);
//$2a$10$J6jnUgOBOAzmwjmNEIsdu.eplg8u6SG/v7uiuG4YFcw.jBgHZXWPW
    }

}
