package cn.itsource.spring_security_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("cn.itsource.spring_security_demo.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true)//开启security注解扫描功能,开启prePostEnabled确定是否应该启用Spring Security的post前注释
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

}
