package cc.xpress.service;

import cc.xpress.bean.vo.Node;
import cc.xpress.bean.vo.PageBean;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 23:46
 * @modified By:
 */
public interface IBaseService<T> {
    /**
     * 根据id查找实体
     *
     * @param entity
     * @return
     */
    T getEntityById(T entity) throws IllegalAccessException, ClassCastException, NullPointerException;

    /**
     * 分页查询
     *
     * @param baseHql
     * @param clazz
     * @param pageNo
     * @param pageSize
     * @param params
     * @return
     * @throws IllegalArgumentException
     */
    PageBean<T> getPageBean(String baseHql, Class<T> clazz, String pageNo, int pageSize,
                            Node<String, Object>... params) throws IllegalArgumentException;
}
