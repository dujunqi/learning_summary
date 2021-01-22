package cn.itsource.security.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @desciption:使用JWT生成Token工具
 * @author: 杜俊圻
 * @date: 2021/01/22 15:01
 */
@Component
public class TokenManager {
    //过期时间
    private long TokenExpiration = 24 * 60 * 60 * 1000;
    //设置秘钥
    private String tokenKey = "123456";

    //通过Jwt生成token
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)//设置Subject
                .setExpiration(new Date(System.currentTimeMillis() + TokenExpiration))//设置过期时间
                .signWith(SignatureAlgorithm.HS512, tokenKey)//设置token加密算法方式
                .compressWith(CompressionCodecs.GZIP)//设置压缩类型
                .compact();//执行压缩
    }

    //通过token解析subject
    public String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenKey)//设置解析token所需秘钥
                .parseClaimsJws(token)//分析jws请求中的token
                .getBody()//获取body信息
                .getSubject();//获取subject
    }

    //删除token
    public void deleteToken(String token){}
}
