package cc.xpress.dao.impl;

import cc.xpress.bean.dto.CouponTbDTO;
import cc.xpress.dao.ICouponDAO;
import org.springframework.stereotype.Repository;

@Repository("couponDAO")
public class CouponDAOImpl extends BaseDAOImpl<CouponTbDTO> implements ICouponDAO {
}
