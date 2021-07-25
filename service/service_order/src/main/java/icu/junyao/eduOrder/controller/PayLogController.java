package icu.junyao.eduOrder.controller;


import icu.junyao.commonUtils.R;
import icu.junyao.eduOrder.service.PayLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/eduOrder/pay-log")
@CrossOrigin
@RequiredArgsConstructor
public class PayLogController {

    private final PayLogService payLogService;



    /**
     * 生成微信支付二维码接口
     * 参数是订单号
     * @param orderNo
     * @return
     */
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNative(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return R.ok().data(map);
    }

    /**
     * 查询订单支付状态
     * 参数：订单号，根据订单号查询 支付状态
     * @param orderNo
     * @return
     */
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        String success = "SUCCESS";
        String tradeState = "trade_state";
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(success.equals(map.get(tradeState))) {
            //支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");

    }
}

