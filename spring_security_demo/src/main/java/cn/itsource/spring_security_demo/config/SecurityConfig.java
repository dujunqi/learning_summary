package cn.itsource.spring_security_demo.config;

import cn.itsource.spring_security_demo.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/04 11:57
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService userDetailsService;
    //由于rememberMe功能需要连接数据库存储token信息，因此需要注入数据库链接信息
    @Autowired
    private DataSource dataSource;
    //配置对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//请求方法的时候创建persistent_logins表
        return jdbcTokenRepository;
    }

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
      http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();//添加用户注销功能
      http.formLogin()  //自定义自己编写的登录页面
              .loginPage("/login.html")          //登录页面设置
              .loginProcessingUrl("/user/login")  //登录访问路径
              /*.defaultSuccessUrl("/test/index").permitAll()//登陆成功之后，跳转路径*/
              .defaultSuccessUrl("/success.html").permitAll()
              .and().authorizeRequests().antMatchers("/","/test/hello","/user/login").permitAll()//设置哪些路径不需要认证
              //.anyRequest().authenticated()//所有的请求都已验证
              //.antMatchers("/test/index").hasAuthority("admins")//拦截/test/index请求验证该用户是否是admins权限
              //.antMatchers("/test/index").hasAnyAuthority("admins,manage")//拦截/test/index请求验证该用户是否含有admins或manage权限
              //.antMatchers("/test/index").hasRole("sale")//拦截/test/index请求验证该用户是否是sale角色
              .antMatchers("/test/index").hasAnyRole("sale,manager")//拦截/test/index请求验证该用户是否含有sale角色
              .and().rememberMe().tokenRepository(persistentTokenRepository())//配置rememberMe，注入数据库链接信息
              .tokenValiditySeconds(60)//配置用户登录有效时长，单位为秒
              .userDetailsService(userDetailsService)//注入当前使用的userDetailsService对象,如果没有代码默认为Security自带的UserDetailsService
              .and().csrf().disable(); //关闭csrf防护
    }

}
