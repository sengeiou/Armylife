package cn.armylife.common.Domain;

import java.io.Serializable;

public class ProductDetail implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_detail.product_detail_id
     *
     * @mbggenerated
     */
    private Long productDetailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_detail.product_id
     *
     * @mbggenerated
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_detail.product_tag
     *
     * @mbggenerated
     */
    private Long productTag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_detail
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_detail.product_detail_id
     *
     * @return the value of product_detail.product_detail_id
     *
     * @mbggenerated
     */
    public Long getProductDetailId() {
        return productDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_detail.product_detail_id
     *
     * @param productDetailId the value for product_detail.product_detail_id
     *
     * @mbggenerated
     */
    public void setProductDetailId(Long productDetailId) {
        this.productDetailId = productDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_detail.product_id
     *
     * @return the value of product_detail.product_id
     *
     * @mbggenerated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_detail.product_id
     *
     * @param productId the value for product_detail.product_id
     *
     * @mbggenerated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_detail.product_tag
     *
     * @return the value of product_detail.product_tag
     *
     * @mbggenerated
     */
    public Long getProductTag() {
        return productTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_detail.product_tag
     *
     * @param productTag the value for product_detail.product_tag
     *
     * @mbggenerated
     */
    public void setProductTag(Long productTag) {
        this.productTag = productTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_detail
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productDetailId=").append(productDetailId);
        sb.append(", productId=").append(productId);
        sb.append(", productTag=").append(productTag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}