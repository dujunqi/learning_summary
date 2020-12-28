package cn.itsource.spring_security.common.core.dao;

import cn.itsource.spring_security.common.core.dao.page.PageModel;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:15
 */
public interface CoreMapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T> {

    Long count(PageModel pageModel);

    List<T> page(PageModel pageModel);
}
