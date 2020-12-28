package cn.itsource.spring_security.common.core.dao.page;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:31
 */
public class DataTablePageModel extends PageModel {
    /**********
     * 客户端发送的参数
     ************/
    private int draw;/*请求次数计数器每次发送给服务器后又原封返回*/
    private int start;/*分页的起始位置,dataTable自动计算好索引了*/
    private int length;/*分页每页显示的数量*/
    /**********
     * 服务器返回的数据
     ************/
    private List<?> data = new ArrayList<>(); /*返回的分页数据*/
    private long recordsTotal;/*数据库里总共记录数*/
    private long recordsFiltered;/*过滤后的记录数*/
    private String error;/*错误来描述服务器出了问题后的友好提示 */

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
