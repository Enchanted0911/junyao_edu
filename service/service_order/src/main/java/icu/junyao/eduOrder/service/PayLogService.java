package icu.junyao.eduOrder.service;

import icu.junyao.eduOrder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-07-17
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 生成微信支付二维码接口
     * @param orderNo
     * @return
     */
    Map createNative(String orderNo);


    /**
     * 根据订单号查询订单支付状态
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);


    /**
     * 向支付表添加记录，更新订单状态
     * @param map
     */
    void updateOrdersStatus(Map<String, String> map);
}
