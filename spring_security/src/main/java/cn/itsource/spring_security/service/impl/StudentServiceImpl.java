package cn.itsource.spring_security.service.impl;

import cn.itsource.spring_security.common.core.dao.spi.BaseService;
import cn.itsource.spring_security.dao.StudentMapper.StudentMapper;
import cn.itsource.spring_security.domain.Student;
import cn.itsource.spring_security.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:28
 */
@Service
public class StudentServiceImpl extends BaseService<Student, Long, StudentMapper> implements StudentService {
    @Resource
    private StudentMapper helloMapper;
}
