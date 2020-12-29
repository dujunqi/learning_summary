package cn.itsource.spring_security.service.impl;

import cn.itsource.spring_security.common.core.dao.spi.BaseService;
import cn.itsource.spring_security.dao.StudentMapper.UserMapper;
import cn.itsource.spring_security.domain.User;
import cn.itsource.spring_security.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/29 17:08
 */
@Service
public class UserServiceImpl extends BaseService<User, Long, UserMapper> implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUserName(username);
        User targetName = userMapper.selectOne(user);
        Assert.isNull(targetName,"用户不存在");
        List<GrantedAuthority> roles = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(targetName.getUserName(),
                new BCryptPasswordEncoder().encode(targetName.getPassWord()),roles);
    }
}
