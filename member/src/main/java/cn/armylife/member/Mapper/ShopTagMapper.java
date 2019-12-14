package cn.armylife.member.Mapper;

import cn.armylife.common.Domain.Product;
import cn.armylife.common.Domain.ShopTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_tag
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer shopTagId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_tag
     *
     * @mbggenerated
     */
    int insert(ShopTag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_tag
     *
     * @mbggenerated
     */
    ShopTag selectByPrimaryKey(Integer shopTagId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_tag
     *
     * @mbggenerated
     */
    List<ShopTag> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_tag
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ShopTag record);

}