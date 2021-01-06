package cn.itsource.spring_security_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/04 11:57
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
        auth.inMemoryAuthentication().withUser("lucy").password(password).roles("admin");
    }*/
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.exceptionHandling().accessDeniedPage("/unAuth.html"); //设置无权限时用户访问的页面

      http.formLogin()  //自定义自己编写的登录页面
              .loginPage("/login.html")          //登录页面设置
              .loginProcessingUrl("/user/login")  //登录访问路径
              .defaultSuccessUrl("/test/index").permitAll()//登陆成功之后，跳转路径
              .and().authorizeRequests().antMatchers("/","/test/hello","/user/login").permitAll()//设置哪些路径不需要认证
              //.anyRequest().authenticated()//所有的请求都已验证
              //.antMatchers("/test/index").hasAuthority("admins")//拦截/test/index请求验证该用户是否是admins权限
              //.antMatchers("/test/index").hasAnyAuthority("admins,manage")//拦截/test/index请求验证该用户是否含有admins或manage权限
              //.antMatchers("/test/index").hasRole("sale")//拦截/test/index请求验证该用户是否是sale角色
              .antMatchers("/test/index").hasAnyRole("sale,manager")//拦截/test/index请求验证该用户是否含有sale角色
              .and().csrf().disable(); //关闭csrf防护
    }

}
