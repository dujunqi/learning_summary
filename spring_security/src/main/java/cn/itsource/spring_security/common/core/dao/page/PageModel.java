package cn.itsource.spring_security.common.core.dao.page;

import java.util.List;
import java.util.Map;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:30
 */
public class PageModel<T> {
    private int currentPage;/*当前页*/
    private int currentIndex;/*当前页数查询mysql的起始索引*/
    private int showCount = 15;/*每页显示记录数,默认15条*/
    private int totalPage;/*总页数*/
    private int totalResult;/*查询的总记录数*/
    private List<T> result;/*分页查询的结果集*/
    private Map<String,Object> params;

    public int getTotalPage() {
        if(totalResult > 0){
            if(totalResult%showCount==0){
                totalPage = totalResult/showCount;
            } else{
                totalPage = totalResult/showCount+1;
            }
        }else{
            totalPage = 0;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if(currentPage<=0){
            currentPage = 1;
        }else if(currentPage>getTotalPage()){
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentIndex() {
        currentIndex = (getCurrentPage()-1)*getShowCount();
        if(currentIndex<0){
            currentIndex = 0;
        }
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
