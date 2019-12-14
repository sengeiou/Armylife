package cn.armylife.payments.ServiceImpl;

import cn.armylife.common.Domain.AfterOrder;
import cn.armylife.payments.Mapper.AfterOrderMapper;
import cn.armylife.payments.Mapper.PaymentsMapper;
import cn.armylife.payments.Service.PayMentsService;
import cn.armylife.common.Domain.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayMemtsServiceImpl implements PayMentsService {

    @Autowired
    PaymentsMapper paymentsMapper;
    @Autowired
    AfterOrderMapper afterOrderMapper;

    /**
     * 查询商铺收入金额
     * @param receivName
     * @return
     */
    public Payments SelectPayForShop(Long receivName){
        return paymentsMapper.SelectPayForShop(receivName);
    };

    /**
     * 查询商铺退款金额
     * @param receivName
     * @return
     */
    public Payments SelectRefundForShop(Long receivName){
        return paymentsMapper.SelectRefundForShop(receivName);
    };

    /**
     * 支付完成,更新支付数据
     * @param payments
     * @return
     */
    public int updatePayMentsForCallback(Payments payments){
        return paymentsMapper.updatePayMentsForCallback(payments);
    };

    /**
     * 查询支付记录(通过订单号)
     * @param orderId
     * @return
     */
    public Payments selectPaments(Long orderId){
        return paymentsMapper.selectPaments(orderId);
    };

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *添加支付记录
     * @mbggenerated
     */
    public int insert(Payments record){
        return paymentsMapper.insert(record);
    };


    public int updateafterOrderForPay(AfterOrder afterOrder){
        return afterOrderMapper.updateafterOrderForPay(afterOrder);
    };
}
