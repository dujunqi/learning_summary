package cn.itsource.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/24 17:25
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @RequestMapping(value = "list")
    public String list(){
        return "hello";
    }
}
