package cn.itsource.security.filter;

import cn.itsource.security.entity.SecurityUser;
import cn.itsource.security.entity.User;
import cn.itsource.security.security.TokenManager;
import cn.itsource.utils.utils.R;
import cn.itsource.utils.utils.ResponseUtil;
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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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
    //认证成功调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //1、获取用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        //根据用户名生成token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        //将用户名及权限列表放入redis
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(),user.getPermissionValueList());
        //返回token信息
        ResponseUtil.out(response, R.ok().data("token",token));
    }

    //认证失败调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
