package cn.itsource.spring_security.common.core.dao.spi;

import cn.itsource.spring_security.common.core.dao.page.DataTablePageModel;
import cn.itsource.spring_security.common.core.dao.page.PageModel;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:32
 */
@Service
public interface IBaseService<T extends Serializable, PK extends Serializable> {

    T findById(PK id);

    void save(T t);

    void update(T t);

    void updateByPrimaryKeySelective(T t);

    void delete(PK id);

    void deleteByIds(PK[] ids);

    long count();

    long count(T t);

    T get(T t);

//    long count(PageModel pageModel);
//
//    List<T> page(PageModel pageModel);
//
//    <T> List<T> search(T var1);

    //    MyPageModel pageModel(MyPageModel page);
    Long count(PageModel pageModel);

    List<T> page(PageModel pageModel);

    DataTablePageModel buildModelBySearch(DataTablePageModel search);
}
