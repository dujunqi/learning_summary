package cn.itsource.spring_security_demo.entity;

import lombok.Data;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/04 14:10
 */
@Data
public class Users {
    private Integer id;
    private String username;
    private String password;
}
