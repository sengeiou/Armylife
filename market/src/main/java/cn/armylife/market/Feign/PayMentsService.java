package cn.armylife.market.Feign;

import cn.armylife.common.Domain.Payments;
import cn.armylife.market.ServiceHysrix.PayMentsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "paymentservice",fallback = PayMentsServiceHystrix.class)
public interface PayMentsService {

    @RequestMapping(value = "Alipayrefund",method = RequestMethod.GET)
    int Alipayrefund(@RequestParam String WIDout_trade_no, @RequestParam String WIDsubject, @RequestParam String WIDrefund_amount, @RequestParam String WIDbody);

    @RequestMapping(value = "selectPaments",method = RequestMethod.GET)
    Payments selectPaments(@RequestParam Long orderId);

    @RequestMapping(value = "WechatPayTransfer",method = RequestMethod.GET)
    int WechatPayTransfer(@RequestParam Long orderCardId, @RequestParam  BigDecimal refundfree, @RequestParam BigDecimal totalfree, @RequestParam String desc);

}

