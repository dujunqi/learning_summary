package cn.itsource.spring_security.domain;

import cn.itsource.spring_security.model.BaseEntity;
import lombok.Data;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/30 14:33
 */
@Data
public class UserInfo extends BaseEntity {
    private String userName;
    private String password;
}
