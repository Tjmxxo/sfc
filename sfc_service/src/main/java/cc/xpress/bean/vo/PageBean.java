package cc.xpress.bean.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-04 17:09
 * @modified By:
 */
public class PageBean<T> implements Serializable {
    private int pageNo;
    private int pageCount;
    private int entityCount;
    private List<T> list;

    public PageBean() {
    }

    public PageBean(int pageNo, int pageCount, int entityCount, List<T> list) {
        this.pageNo = pageNo;
        this.pageCount = pageCount;
        this.entityCount = entityCount;
        this.list = list;
    }

    public int getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(int entityCount) {
        this.entityCount = entityCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
