package cn.armylife.integralmall.Mapper;

import cn.armylife.common.Domain.MallProductsDetail;
import cn.armylife.common.Domain.PointsMallProducts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MallProductsDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_products_detail
     *
     * @mbggenerated
     */
    int insert(MallProductsDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_products_detail
     *
     * @mbggenerated
     */
    List<MallProductsDetail> selectAll();

    /**
     * 通过Id查询商品规格信息
     * @param productDetailId
     * @return
     */
    MallProductsDetail findProductForDetail(Long productDetailId);

    /**
     * 通过商品Id查询信息
     * @param productId
     * @return
     */
    PointsMallProducts findProductForProductId(Long productId);
}