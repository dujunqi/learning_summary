package cn.itsource.security.security;

import cn.itsource.utils.utils.MD5;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @desciption:默认密码处理
 * @author: 杜俊圻
 * @date: 2021/01/22 11:47
 */
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        /*使用this调用有参构造方法*/
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {
    }

    /**
     * MD5方式加密
     */
    @Override
    @ApiOperation(value = "比较加密后的密码串", response = String.class)
    @ApiImplicitParam(name = "charSequence", dataType = "CharSequence", required = true, value = "源码")
    public String encode(CharSequence charSequence) {
        /*通过pom文件导入service_base项目后，可以直接调用该项目中的方法*/
        return MD5.encrypt(charSequence.toString());
    }

    @Override
    @ApiOperation(value = "比较加密后的密码串", response = Boolean.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "charSequence", dataType = "CharSequence", required = true, value = "源码"),
            @ApiImplicitParam(name = "encodedPassword", dataType = "String", required = true, value = "加码后的密码")})
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
