package cn.armylife.member.mapper;

import cn.armylife.common.domain.ShopOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer ordersId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    int insert(ShopOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    ShopOrder selectByPrimaryKey(Integer ordersId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    List<ShopOrder> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ShopOrder record);

    List<ShopOrder> selectTaskOrder(String time);
}