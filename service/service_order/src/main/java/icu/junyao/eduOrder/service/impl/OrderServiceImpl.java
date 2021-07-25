package icu.junyao.eduOrder.service.impl;

import icu.junyao.commonUtils.ordervo.CourseWebVoOrder;
import icu.junyao.commonUtils.ordervo.UcenterMemberOrder;
import icu.junyao.eduOrder.client.EduClient;
import icu.junyao.eduOrder.client.UcenterClient;
import icu.junyao.eduOrder.entity.Order;
import icu.junyao.eduOrder.mapper.OrderMapper;
import icu.junyao.eduOrder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.eduOrder.utils.OrderNoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-07-17
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final EduClient eduClient;
    private final UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        //通过远程调用根据课程id获取课信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        //订单号
        order.setOrderNo(OrderNoUtil.getOrderNo());
        //课程id
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        //订单状态（0：未支付 1：已支付）
        order.setStatus(0);
        //支付类型 ，微信1
        order.setPayType(1);
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
