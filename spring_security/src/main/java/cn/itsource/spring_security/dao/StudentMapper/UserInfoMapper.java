package cn.itsource.spring_security.dao.StudentMapper;

import cn.itsource.spring_security.common.core.dao.CoreMapper;
import cn.itsource.spring_security.domain.Student;
import cn.itsource.spring_security.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/30 14:44
 */
@Repository
public interface UserInfoMapper  extends BaseMapper<UserInfo> {
}
