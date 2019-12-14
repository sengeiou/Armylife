package cn.armylife.market.Mapper;

import cn.armylife.common.Domain.DeliveryOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer deliveryOrdersId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    int insert(DeliveryOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    DeliveryOrder selectByPrimaryKey(Integer deliveryOrdersId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    List<DeliveryOrder> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DeliveryOrder record);

    /**
     *通过订单查询跑腿订单
     * @param orderId
     * @return
     */
    DeliveryOrder selectDeliveryFororderId(Long orderId);
}