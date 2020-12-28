package cn.itsource.spring_security.common.core.dao.spi;

import ch.qos.logback.classic.Logger;
import cn.itsource.spring_security.common.core.dao.CoreMapper;
import cn.itsource.spring_security.common.core.dao.page.DataTablePageModel;
import cn.itsource.spring_security.common.core.dao.page.PageModel;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:33
 */
@Service
public abstract class BaseService<T extends Serializable, PK extends Serializable, Dao extends CoreMapper<T>> implements IBaseService<T, PK> {
    Logger logger = (Logger) LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected Dao dao;

    @Override
    public T findById(PK id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public void save(T t) {
        this.dao.insertSelective(t);
    }

    @Override
    public void delete(PK id) {
        dao.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(PK[] ids) {
        this.dao.deleteByIds(ids.toString());
    }

    @Override
    public long count() {
        return this.dao.selectCount(null);
    }

    @Override
    public long count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public void update(T t) {
        dao.updateByPrimaryKey(t);

    }

    @Override
    public void updateByPrimaryKeySelective(T t) {
        dao.updateByPrimaryKeySelective(t);

    }
    @Override
    public T get(T t) {
        return dao.selectOne(t);
    }
    @Override
    public Long count(PageModel pageModel){
        return this.dao.count(pageModel);
    }

    @Override
    public List<T> page(PageModel pageModel) {
        return this.dao.page(pageModel);
    }

    @Override
    public DataTablePageModel buildModelBySearch(DataTablePageModel search) {
        List<T> data = this.page(search);
        if(!data.isEmpty()){
            Long count = this.count(search);
            search.setRecordsTotal(count);
            search.setRecordsFiltered( count);
            search.setData(data);
        }
        return search;
    }

//    @Override
//    public long count(PageModel pageModel) {
//        return this.dao.count(pageModel);
//    }

//    @Override
//    public List<T> page(PageModel pageModel) {
//        return this.iBaseDao.page(pageModel);
//    }
//
//    @Override
//    public <T> List<T> search(T var1) {
//        dao.select(var1);
//        return this.iBaseDao.search(var1);
//    }


//    @Override
//    public MyPageModel pageModel(MyPageModel page) {
//        PageModel<T> pageModel = new PageModel<T>();
//        pageModel.setParams(page.getParams());
//        List result = this.page(pageModel);
//        long count = this.count(pageModel);
//        page.setTotalCount((Long) count);
//        page.setData(result);
//        return page;
//    }

}
