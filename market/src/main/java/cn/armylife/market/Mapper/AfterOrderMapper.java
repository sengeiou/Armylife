package cn.armylife.market.Mapper;

import cn.armylife.common.Domain.AfterOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AfterOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long afterOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    int insert(AfterOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    AfterOrder selectByPrimaryKey(Long afterOrderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    List<AfterOrder> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AfterOrder record);

    AfterOrder SelectAfterOrder(Long ordersId);
}