package icu.junyao.eduOrder.service;

import icu.junyao.eduOrder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-07-17
 */
public interface OrderService extends IService<Order> {
    /**
     * 生成订单的方法
     * @param courseId
     * @param memberIdByJwtToken
     * @return
     */
    String createOrders(String courseId, String memberIdByJwtToken);
}
