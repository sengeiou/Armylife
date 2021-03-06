package cn.armylife.payments.mapper;

import cn.armylife.common.domain.AfterOrder;
import cn.armylife.common.domain.Member;
import cn.armylife.common.domain.Payments;
import cn.armylife.common.domain.ShopOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PaymentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer paymentsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *
     * @mbggenerated
     */
    int insert(Payments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *
     * @mbggenerated
     */
    Payments selectByPrimaryKey(Integer paymentsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *
     * @mbggenerated
     */
    List<Payments> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payments
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Payments record);

    /**
     * 查询商铺收入金额
     * @param receivName
     * @return
     */
    Payments SelectPayForShop(Long receivName);

    /**
     * 查询商铺退款金额
     * @param receivName
     * @return
     */
    Payments SelectRefundForShop(Long receivName);

    /**
     * 支付完成,更新支付数据
     * @param payments
     * @return
     */
    int updatePayMentsForCallback(Payments payments);

    /**
     * 查询支付记录(通过订单号)
     * @param orderId
     * @return
     */
    Payments selectPaments(Long orderId);

    ShopOrder selectOrder(Long ordersId);
    AfterOrder selectAfterOrder(Long ordersId);

    int updateShopOrder(ShopOrder shopOrder);

    Payments selectOrderForPayments(Long paymentsId);

    int updateShop(Member member);

    Member selectMember(Long memberId);


    Member selectMemberForOpenid(String memberWechat);
}