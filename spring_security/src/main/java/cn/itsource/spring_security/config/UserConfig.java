package cn.itsource.spring_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 18:36
 */
/*自定义实现类的过程
    1.创建配置类，设置使用哪个UserDetailsService的实现类
    2.编写接口的实现类，返回user对象，该对象有用户的基本信息包括用户名.密码.权限等

* */
@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    //由于程序没有在配置文件中直接配置用户信息及密码信息 程序会去找这个Service 所以如果需要自定义逻辑时，只需要实现该接口即可
    private UserDetailsService userService;
    // 注入security自带的用户认证Service
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(password());
    }

    //由于configure方法中passwordEncoder方法需要一个PasswordEncoder对象，所以使用Bean注解向SpringBoot注册一个
    @Bean
    PasswordEncoder password(){
        return new SCryptPasswordEncoder();
    }
}
