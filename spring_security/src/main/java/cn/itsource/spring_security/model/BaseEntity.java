package cn.itsource.spring_security.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @desciption:用途
 * @author: 杜俊圻
 * @date: 2020/12/28 16:21
 */
public abstract class BaseEntity implements Comparable<Object>, Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
     * –AUTO： JPA自动选择合适的策略，是默认选项；
     * –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
     * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植
     */
    //insert后返回插入的id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @Id
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Object obj) {
        int compare = -1;
        if (obj == null) {
            compare = -1;
        } else if (this == obj) {
            compare = 0;
        } else if (!(obj instanceof BaseEntity)) {
            compare = -1;
        } else if (!this.getClass().equals(obj.getClass())) {
            compare = -1;
        } else {
            BaseEntity castObj = (BaseEntity) obj;
            CompareToBuilder builder = new CompareToBuilder();
            builder.append(this.getId(), castObj.getId());
            compare = builder.toComparison();
        }
        return compare;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj == null) {
            isEqual = false;
        } else if (this == obj) {
            isEqual = true;
        } else if (!(obj instanceof BaseEntity)) {
            isEqual = false;
        } else if (!this.getClass().equals(obj.getClass())) {
            isEqual = false;
        } else {
            BaseEntity castObj = (BaseEntity) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.getId(), castObj.getId());
            isEqual = builder.isEquals();
        }
        return isEqual;
    }
}
