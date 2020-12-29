package cn.itsource.spring_security.controller;

import cn.itsource.spring_security.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private StudentService helloService;

    @RequestMapping(value = "/list")
    public Long list(){
        return helloService.count();
    }
}
