package cn.itsource.spring_security_demo.mapper;

import cn.itsource.spring_security_demo.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2021/01/04 14:26
 */
@Repository
public interface UserMapper extends BaseMapper<Users> {
}
