package cn.itsource.spring_security.service;

import cn.itsource.spring_security.common.core.dao.spi.IBaseService;
import cn.itsource.spring_security.domain.Student;
import cn.itsource.spring_security.domain.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/29 17:08
 */

public interface UserService extends IBaseService<User,Long> {

}
