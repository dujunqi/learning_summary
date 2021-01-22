package cn.itsource.security.security;

import cn.itsource.utils.utils.R;
import cn.itsource.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/22 15:54
 */
public class TokenLogoutHandle implements LogoutHandler {

    private RedisTemplate  redisTemplate;
    private TokenManager tokenManager;

    public TokenLogoutHandle(RedisTemplate  redisTemplate,TokenManager tokenManager){
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //移除token

        String token = request.getHeader("token");
        if(token != null){
            tokenManager.deleteToken(token);
            //删除redis中用户信息
            String userInfoFromToken = tokenManager.getUserInfoFromToken(token);
            redisTemplate.delete(userInfoFromToken);
        }
        ResponseUtil.out(response, R.ok());
    }
}
