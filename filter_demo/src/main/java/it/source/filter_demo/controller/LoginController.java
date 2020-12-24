package it.source.filter_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/23 11:47
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping(value = "/list")
    public String list(){return "login/list";}

}
