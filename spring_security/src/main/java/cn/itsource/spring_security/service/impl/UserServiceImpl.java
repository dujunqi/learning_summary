package cn.itsource.spring_security.service.impl;

import cn.itsource.spring_security.common.core.dao.spi.BaseService;
import cn.itsource.spring_security.common.core.dao.spi.IBaseService;
import cn.itsource.spring_security.dao.StudentMapper.UserInfoMapper;
import cn.itsource.spring_security.domain.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desciption:验证登录
 * @author: 杜俊圻
 * @date: 2020/12/29 17:08
 */
/*实现UserDetailsService，通过AuthenticationManagerBuilder认证管理器构建器实现登录验证
通过设置@Service中bean的名字*/
@Service(value = "userDetailService")
public class UserServiceImpl  implements UserDetailsService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper();
        wrapper.eq("user_name",username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if(userInfo == null){
            throw  new UsernameNotFoundException("用户不存在");
        }
        //使用Authority工具创建权限collection
        List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        //返回的对象中的参数分别是用户名，密码，角色列表
        return new org.springframework.security.core.userdetails.User(userInfo.getUserName(),
                new BCryptPasswordEncoder().encode(userInfo.getPassword()),roles);
    }
}
