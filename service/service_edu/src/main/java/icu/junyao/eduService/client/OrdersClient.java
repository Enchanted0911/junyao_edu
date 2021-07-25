package icu.junyao.eduService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wu
 */
@Component
@FeignClient("service-orders")
public interface OrdersClient {



    /**
     *     根据课程id和用户id查询订单表中订单状态
     * @param courseId
     * @param memberId
     * @return
     */
    @GetMapping("/eduOrder/order/isBuyCourse/{courseId}/{memberId}")
    boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
