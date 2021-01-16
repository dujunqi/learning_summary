package cn.itsource.spring_security_demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/30 18:02
 */
@RestController
@RequestMapping(value = "/test")
public class HelloController {
    @GetMapping(value = "/hello")
    public String list() {
        return "hello";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "hello index";
    }

    @GetMapping(value = "/update")
    //@Secured({"ROLE_sale", "ROLE_manager"})//注解类型管理controller中的方法，存在该用户权限的用户才能访问
    //@PreAuthorize(value = "hasAnyAuthority('sale')")//Spring Security的post前注释,在访问该方法访问前验证是否存在该权限
    @PostAuthorize(value = "hasAnyAuthority('sale')")//Spring Security的post后注释,在访问该方法访问后验证是否存在该权限，不常用。应用场景在方法需要返回值的时候才应用
    public String update() {
        System.out.println("update......");
        return "hello index";
    }
}
