package cc.xpress.dao;

import cc.xpress.bean.vo.Node;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-05 15:14
 * @modified By:
 */
public interface IBaseDAO<T> {
    /**
     * 创建
     *
     * @param entity
     * @return
     */
    Serializable saveEntity(T entity);

    /**
     * 保存修改
     *
     * @param entity
     * @return
     */
    void updateEntity(T entity);

    /**
     * 保存或者修改
     *
     * @param entity
     * @return
     */
    void saveUpdateEntity(T entity);

    /**
     * 删除实体
     *
     * @param entity
     */
    void deleteEntity(T entity);

    /**
     * 根据id查询实体对象
     *
     * @param id
     * @param clazz
     * @return
     */
    T getEntityById(Class<T> clazz, Serializable id);

    /**
     * 分页查询
     *
     * @param query
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<T> pagedQuery(Query query, int pageNo, int pageSize);

    /**
     * 条件查询
     *
     * @param baseHql
     * @param clazz
     * @param params
     * @return
     */
    Query query(String baseHql, Class<T> clazz, Node... params);
}
