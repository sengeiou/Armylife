package cn.armylife.member.util;import cn.armylife.common.domain.Payments;import cn.armylife.common.domain.ShopOrder;import cn.armylife.member.feignservice.PayMentsService;import cn.armylife.member.mapper.PaymentsMapper;import cn.armylife.member.mapper.ShopOrderMapper;import com.aliyuncs.exceptions.ClientException;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.scheduling.annotation.Scheduled;import org.springframework.stereotype.Component;import java.text.SimpleDateFormat;import java.util.Calendar;import java.util.Date;import java.util.GregorianCalendar;import java.util.List;import java.util.logging.Logger;@Slf4j@Componentpublic class ProjectTask {    Logger logger=Logger.getLogger("ProjectTask.class");    @Autowired    ShopOrderMapper shopOrderMapper;    @Autowired    PaymentsMapper paymentsMapper;    @Autowired    PayMentsService payMentsService;    @Scheduled(fixedRate = 60000)    public void  Endproject() throws ClientException {        logger.info("定时任务启动");        AliDayunSms aliDayunSms=new AliDayunSms();        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");        String time=date.format(new Date());        logger.info("定时任务执行时间:"+time);        Calendar calendar = new GregorianCalendar();        calendar.setTime(new Date());        calendar.add(calendar.MINUTE,-5);        String time1= date.format(calendar.getTime());        logger.info("定时任务获取订单时间:"+time1);        List<ShopOrder> order=shopOrderMapper.selectTaskOrder(time1);        for (int i=0;i<order.size();i++){            ShopOrder shopOrder=order.get(i);            Long orderId=shopOrder.getOrdersId();            Payments payments=paymentsMapper.selectPaymentsForOrder(orderId);            logger.info(payments.toString());            Long paymentsId=payments.getPaymentsId();            String desc="超时自动退款";            String total=payments.getPayTotal().toString();            String subject="商品退款";            int msg=1;            String message=null;            switch (payments.getPayApp()){                case "1":                    msg=payMentsService.WechatPayTransfer(paymentsId,payments.getPayTotal(),payments.getPayTotal(),desc);                    if (msg==0){                        msg=payMentsService.WechatPayTransfer(paymentsId,payments.getPayTotal(),payments.getPayTotal(),desc);                    }                    message=aliDayunSms.SMS(shopOrder.getUserPhone(),"订单超时未被接单"+shopOrder.getOrdersId(),"SMS_181200119");                    break;                case "2":                   msg= payMentsService.Alipayrefund(String.valueOf(paymentsId),subject,total,desc);                    if (msg==0){                        msg= payMentsService.Alipayrefund(String.valueOf(paymentsId),subject,total,desc);                    }                    message=aliDayunSms.SMS(shopOrder.getUserPhone(),"订单超时未被接单"+shopOrder.getOrdersId(),"SMS_181200119");                    break;            }        }        return;    }}