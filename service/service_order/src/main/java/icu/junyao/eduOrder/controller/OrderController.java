package icu.junyao.eduOrder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.junyao.commonUtils.JwtUtils;
import icu.junyao.commonUtils.R;
import icu.junyao.eduOrder.entity.Order;
import icu.junyao.eduOrder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/eduOrder/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 生成订单的方法
     * @param courseId 课程id
     * @param request 获取token
     * @return 订单id
     */
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId",orderNo);
    }


    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item",order);
    }



    /**
     * 根据课程id和用户id查询订单表中订单状态
     * @param courseId
     * @param memberId
     * @return
     */
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        //支付状态 1代表已经支付
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        return count > 0;
    }
}

