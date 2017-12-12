package cc.xpress.provider.impl.service;

import cc.xpress.annotation.EntityId;
import cc.xpress.bean.vo.Node;
import cc.xpress.bean.vo.PageBean;
import cc.xpress.dao.IBaseDAO;
import cc.xpress.service.IBaseService;
import cc.xpress.utils.CommonUtils;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 23:49
 * @modified By:
 */
@Service("baseService")
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    IBaseDAO baseDAO;

    @Transactional
    @Override
    public T getEntityById(T entity) throws IllegalAccessException, ClassCastException, NullPointerException {
        if (entity == null) {
            //TODO 添加异常描述
            throw new NullPointerException();
        }
        Serializable entityValue = CommonUtils.getEntityValue(entity, EntityId.class);
        if (entityValue == null) {
            //TODO 添加异常描述
            throw new NullPointerException();
        }
        return (T) baseDAO.getEntityById(entity.getClass(), entityValue);
    }

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
    @Transactional
    @Override
    public PageBean<T> getPageBean(String baseHql, Class<T> clazz, String pageNo, int pageSize,
                                   Node<String, Object>... params) throws IllegalArgumentException {
        /*判断是否能转换成数字*/
        if (!CommonUtils.isConvertedToNumber(pageNo)) {
            //TODO 添加异常描述
            throw new IllegalArgumentException();
        }
        int pageNum = Integer.parseInt(pageNo);
        /*判断pageNum页码是否合法*/
        if (pageNum < 1) {
            //TODO 添加异常描述
            throw new IllegalArgumentException();
        }
        Long aLong = (Long) baseDAO.query("select count(*) from", clazz, params).uniqueResult();
        int entityCount = aLong.intValue();
        /*判断pageNum是否超界*/
        if ((pageNum - 1) * pageSize > entityCount) {
            //TODO 添加异常描述
            throw new IllegalArgumentException();
        }
        Query query = baseDAO.query(baseHql, clazz, params);
        List list = baseDAO.pagedQuery(query, pageNum, pageSize);
        int pageCount = entityCount % pageSize == 0 ? entityCount / pageSize : entityCount / pageSize + 1;
        return new PageBean<T>(pageNum, pageCount, entityCount, list);
    }
}
