package cc.xpress.dao.impl;

import cc.xpress.bean.vo.Node;
import cc.xpress.dao.IBaseDAO;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-05 15:06
 * @modified By:
 */
@Repository("baseDAO")
public class BaseDAOImpl<T> implements IBaseDAO<T> {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    @Transactional
    @Override
    public Serializable saveEntity(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    /**
     * 修改
     *
     * @param entity
     */
    @Override
    public void updateEntity(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    /**
     * 修改或者保存
     *
     * @param entity
     */
    @Override
    public void saveUpdateEntity(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * 删除实体
     *
     * @param entity
     */
    @Transactional
    @Override
    public void deleteEntity(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * 根据id查询实体
     *
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public T getEntityById(Class<T> clazz, Serializable id) {
        logger.info(id);
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    /**
     * hql多条件查询
     *
     * @param baseHql
     * @param clazz
     * @param params
     * @return
     */
    @Override
    public Query query(String baseHql, Class<T> clazz, Node... params) {
        baseHql += " " + clazz.getName();
        /*参数*/
        if (params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                if (i == 0) {
                    baseHql += " " + "where" + " " + params[i].getKey() + "=?";
                } else {
                    baseHql += " " + "and" + " " + params[i].getKey() + "=?";
                }
            }
        }
        logger.info(baseHql);
        Query query = sessionFactory.getCurrentSession().createQuery(baseHql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i].getValue());
        }
        return query;
    }

    /**
     * hql分页查询
     *
     * @param query
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<T> pagedQuery(Query query, int pageNo, int pageSize) {
        logger.info("paging：limit  " + pageSize * (pageNo - 1) + "  " + pageSize);
        if (pageSize != 0) {
            query.setFirstResult(pageSize * (pageNo - 1));
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
}
