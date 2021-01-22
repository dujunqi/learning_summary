package cn.itsource.security.filter;

import cn.itsource.security.entity.User;
import cn.itsource.security.security.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @desciption:token登录过滤器
 * @author: 杜俊圻
 * @date: 2021/01/22 16:58
 */

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private AuthenticationManager authenticationManager;
    /**
     * 通过构造方法传参 tokenManager，redisTemplate，authenticationManager
     */
    public TokenLoginFilter(TokenManager tokenManager,RedisTemplate redisTemplate,AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
        this.setPostOnly(false);//设置不仅仅是post提交
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));//登录路径匹配post提交
    }
    //重写登录成功后的方法successfulAuthentication
    //重写登录失败后的方法unsuccessfulAuthentication
    //重写attemptAuthentication 获取表单提交过来的用户名及密码
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //获取user对象
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
