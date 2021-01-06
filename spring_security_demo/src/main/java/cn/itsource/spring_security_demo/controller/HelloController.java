package cn.itsource.spring_security_demo.controller;

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
    public String list(){
        return "hello";
    }
    @GetMapping(value = "/index")
    public String index(){
        return "hello index";
    }
}
