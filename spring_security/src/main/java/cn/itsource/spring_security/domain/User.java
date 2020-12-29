package cn.itsource.spring_security.domain;

import cn.itsource.spring_security.model.BaseEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/29 17:08
 */
@Data
public class User extends BaseEntity {
    private String name;
    private String userName;
    private String passWord;
}
