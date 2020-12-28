package cn.itsource.spring_security.domain;

import cn.itsource.spring_security.model.BaseEntity;
import lombok.Data;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:10
 */
@Data
public class Student extends BaseEntity {
    private String name;
    private Integer age;
    private Boolean sex;
}
