package cn.itsource.spring_security_demo.service;

import cn.itsource.spring_security_demo.entity.Users;
import cn.itsource.spring_security_demo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/04 13:56
 */
@Service(value = "userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        Users users = userMapper.selectOne(wrapper);
        if(users == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> roles= AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),roles);
    }
}
